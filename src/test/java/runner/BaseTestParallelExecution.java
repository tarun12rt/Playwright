package runner;

import com.microsoft.playwright.*;
import com.aventstack.extentreports.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import utils.BrowserFactory;
import utils.BrowserStackCapabilityManager;
import utils.ExtentManager;
import config.Config;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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

        Browser br;
        String platform = Config.get("platform");

        /* LOCAL EXECUTION */

        if ("local".equalsIgnoreCase(platform)) {

            br = BrowserFactory.getBrowser(pw);

        }

        /* BROWSERSTACK EXECUTION */

        else {

            JSONObject caps = BrowserStackCapabilityManager.getCapabilities();

            caps.put("browserstack.username", Config.get("BS_Username"));
            caps.put("browserstack.accessKey", Config.get("BS_Password"));

            caps.put("build", "Playwright Automation Framework");
            caps.put("name", method.getName());

            String wsEndpoint = "wss://cdp.browserstack.com/playwright?caps="
                    + URLEncoder.encode(caps.toString(), StandardCharsets.UTF_8);

            br = pw.chromium().connectOverCDP(wsEndpoint);
        }

        browser.set(br);

        Browser.NewContextOptions options = new Browser.NewContextOptions();

        if (Config.getBoolean("video.recording")) {

            options.setRecordVideoDir(Paths.get("test-output/latest-report/videos"))
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

            /* ================= PASS ================= */

            if (result.getStatus() == ITestResult.SUCCESS) {

                test.get().pass("Test Passed");

            }

            /* ================= FAILURE ================= */

            if (result.getStatus() == ITestResult.FAILURE && currentPage != null) {

                Path screenshotDir = Paths.get("test-output/latest-report/screenshots");
                Files.createDirectories(screenshotDir);

                Path screenshotPath =
                        screenshotDir.resolve(result.getName() + ".png");

                currentPage.screenshot(
                        new Page.ScreenshotOptions().setPath(screenshotPath).setFullPage(true)
                );

                test.get().fail(result.getThrowable());

                test.get().addScreenCaptureFromPath(
                        "screenshots/" + screenshotPath.getFileName()
                );
            }

            /* ================= SKIP ================= */

            if (result.getStatus() == ITestResult.SKIP) {

                test.get().skip(result.getThrowable());

            }

            /* ================= CLOSE CONTEXT ================= */

            if (currentContext != null) {
                currentContext.close();
            }

            /* ================= VIDEO ATTACHMENT ================= */

            if (Config.getBoolean("video.recording")
                    && result.getStatus() == ITestResult.FAILURE
                    && currentPage != null
                    && currentPage.video() != null) {

                Path videoDir = Paths.get("test-output/latest-report/videos");
                Files.createDirectories(videoDir);

                Path videoPath = currentPage.video().path();

                Path finalVideo =
                        videoDir.resolve(result.getName() + ".webm");

                Files.move(videoPath, finalVideo);

                String relativeVideoPath =
                        "videos/" + finalVideo.getFileName();

                test.get().info(
                        "<video width='720' height='405' controls>" +
                                "<source src='" + relativeVideoPath + "' type='video/webm'>" +
                                "</video>"
                );
            }

        }

        catch (Exception e) {
            e.printStackTrace();
        }

        finally {

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