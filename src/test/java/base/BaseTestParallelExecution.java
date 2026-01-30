package base;

import com.microsoft.playwright.*;
import com.aventstack.extentreports.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.BrowserFactory;
import utils.ExtentManager;
import config.Config;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTestParallelExecution {

    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static ExtentReports extent;

    protected Page page() {
        return page.get();
    }

    protected ExtentTest test() {
        return test.get();
    }

    /* ================= SUITE SETUP ================= */

    @BeforeSuite(alwaysRun = true)
    public void startReport() {
        extent = ExtentManager.getExtent();
    }

    /* ================= TEST SETUP ================= */

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {

        Playwright pw = Playwright.create();
        playwright.set(pw);

        Browser br = BrowserFactory.getBrowser(pw);
        browser.set(br);

        Browser.NewContextOptions options = new Browser.NewContextOptions();

        if (Config.getBoolean("video.recording")) {
            options.setRecordVideoDir(Paths.get("test-output/videos"))
                   .setRecordVideoSize(1280, 720);
        }

        BrowserContext ctx = br.newContext(options);
        context.set(ctx);

        Page pg = ctx.newPage();
        page.set(pg);

        test.set(extent.createTest(method.getName()));
    }

    /* ================= TEST TEARDOWN ================= */

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        Page currentPage = page.get();
        BrowserContext currentContext = context.get();

        try {

            /* ---------- Screenshot on failure ---------- */
            if (result.getStatus() == ITestResult.FAILURE && currentPage != null) {

                Path screenshotDir = Paths.get("test-output/screenshots");
                Files.createDirectories(screenshotDir);

                Path screenshotPath =
                        screenshotDir.resolve(result.getName() + ".png");

                currentPage.screenshot(
                        new Page.ScreenshotOptions().setPath(screenshotPath)
                );

                test.get().fail(result.getThrowable());
                test.get().addScreenCaptureFromPath(
                        "../screenshots/" + screenshotPath.getFileName()
                );
            }

            /* ---------- CLOSE CONTEXT FIRST (CRITICAL) ---------- */
            if (currentContext != null) {
                currentContext.close();
            }

            /* ---------- Attach VIDEO ONLY for FAILED tests ---------- */
            if (result.getStatus() == ITestResult.FAILURE
                    && currentPage != null
                    && currentPage.video() != null) {

                Path videoDir = Paths.get("test-output/videos");
                Files.createDirectories(videoDir);

                Path videoPath = currentPage.video().path();
                Path finalVideo =
                        videoDir.resolve(result.getName() + ".webm");

                Files.move(videoPath, finalVideo);

                String relativeVideoPath =
                        "../videos/" + finalVideo.getFileName();

                // ✅ ONLY reliable way to show video in Extent
                test.get().info(
                        "<video width='720' height='405' controls>" +
                                "<source src='" + relativeVideoPath + "' type='video/webm'>" +
                                "Your browser does not support the video tag." +
                                "</video>"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            /* ---------- HARD CLEANUP (Jenkins safe) ---------- */
            try {
                if (browser.get() != null) browser.get().close();
            } catch (Exception ignored) {}

            try {
                if (playwright.get() != null) playwright.get().close();
            } catch (Exception ignored) {}

            browser.remove();
            playwright.remove();
            context.remove();
            page.remove();
            test.remove();
        }
    }

    /* ================= SUITE TEARDOWN ================= */

    @AfterSuite(alwaysRun = true)
    public void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
