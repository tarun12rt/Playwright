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

public class BaseTestSingleExecution {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeClass
    public void globalSetup() {
        extent = ExtentManager.getExtent();
        playwright = Playwright.create();
        browser = BrowserFactory.getBrowser(playwright);
    }

    @BeforeMethod
    public void setupTest(Method method) {

        Browser.NewContextOptions options = new Browser.NewContextOptions();

        if (Config.getBoolean("video.recording")) {
            options.setRecordVideoDir(Paths.get("videos"))
                   .setRecordVideoSize(1280, 720);
        }

        context = browser.newContext(options);

        context.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );

        page = context.newPage();
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void tearDownTest(ITestResult result) {

        try {
            context.tracing().stop(
                    new Tracing.StopOptions()
                            .setPath(Paths.get("traces/" + result.getName() + ".zip"))
            );
        } catch (Exception ignored) {}

        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                Path screenshotPath =
                        Paths.get("screenshots", result.getName() + ".png");
                Files.createDirectories(screenshotPath.getParent());

                page.screenshot(
                        new Page.ScreenshotOptions().setPath(screenshotPath)
                );

                test.fail(result.getThrowable());
                test.addScreenCaptureFromPath(screenshotPath.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 🔹 CLOSE CONTEXT FIRST (finalizes video)
        context.close();

        // 🔹 VIDEO HANDLING (AFTER context close)
        try {
            if (Config.getBoolean("video.recording")) {

                Path originalVideo = page.video().path();
                Path targetDir = Paths.get("videos");
                Files.createDirectories(targetDir);

                String timestamp = TimeUtil.getTimestamp();
                Path renamedVideo =
                        targetDir.resolve(result.getName() + "_" + timestamp + ".webm");

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
    }

    @AfterClass
    public void globalTearDown() {
        browser.close();
        playwright.close();
        extent.flush();
    }
}
