package base;

import com.microsoft.playwright.*;
import com.aventstack.extentreports.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.BrowserFactory;
import utils.ExtentManager;
import config.Config;
import utils.TimeUtil;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTestParallelExecution {

    // ===== ThreadLocal objects =====
    private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlContext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();

    protected static ExtentReports extent;

    // ===== Accessors (IMPORTANT) =====
    protected Page page() {
        return tlPage.get();
    }

    protected ExtentTest test() {
        return tlTest.get();
    }

    /* ================= SETUP ================= */

    @BeforeSuite(alwaysRun = true)
    public void startReport() {
        extent = ExtentManager.getExtent();
    }

    @BeforeMethod(alwaysRun = true)
    public void setupTest(Method method) {

        tlPlaywright.set(Playwright.create());
        tlBrowser.set(BrowserFactory.getBrowser(tlPlaywright.get()));

        Browser.NewContextOptions options = new Browser.NewContextOptions();

        if (Config.getBoolean("video.recording")) {
            options.setRecordVideoDir(Paths.get("videos"))
                   .setRecordVideoSize(1280, 720);
        }

        tlContext.set(tlBrowser.get().newContext(options));

        tlContext.get().tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );

        tlPage.set(tlContext.get().newPage());
        tlTest.set(extent.createTest(method.getName()));
    }

    /* ================= TEARDOWN ================= */

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult result) {

        try {
            tlContext.get().tracing().stop(
                    new Tracing.StopOptions()
                            .setPath(Paths.get("traces/" + result.getName() + ".zip"))
            );
        } catch (Exception ignored) {}

        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                Path screenshotPath =
                        Paths.get("screenshots", result.getName() + ".png");
                Files.createDirectories(screenshotPath.getParent());

                page().screenshot(
                        new Page.ScreenshotOptions().setPath(screenshotPath)
                );

                test().fail(result.getThrowable());
                test().addScreenCaptureFromPath(screenshotPath.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Close context FIRST (video finalizes here)
        tlContext.get().close();

        // Handle video
        try {
            if (Config.getBoolean("video.recording")) {

                Path originalVideo = page().video().path();
                Path targetDir = Paths.get("videos");
                Files.createDirectories(targetDir);

                Path renamedVideo =
                        targetDir.resolve(
                                result.getName() + "_" + TimeUtil.getTimestamp() + ".webm"
                        );

                if (Config.getBoolean("video.onFailureOnly")) {
                    if (result.getStatus() == ITestResult.FAILURE) {
                        Files.move(originalVideo, renamedVideo);
                    } else {
                        Files.deleteIfExists(originalVideo);
                    }
                } else {
                    Files.move(originalVideo, renamedVideo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Cleanup ThreadLocal
        tlBrowser.get().close();
        tlPlaywright.get().close();

        tlPage.remove();
        tlContext.remove();
        tlBrowser.remove();
        tlPlaywright.remove();
        tlTest.remove();
    }

    @AfterSuite(alwaysRun = true)
    public void flushReport() {
        extent.flush();
    }
}

