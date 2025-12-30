package base;

import com.microsoft.playwright.*;
import com.aventstack.extentreports.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentManager;
import config.Config;
import utils.TimeUtil;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected ExtentReports extent;
    protected ExtentTest test;

    Path videoPath;

    @BeforeClass
    public void globalSetup() {
        extent = ExtentManager.getExtent();

        playwright = Playwright.create();
        browser = playwright.webkit().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
    }

    @BeforeMethod
    public void setupTest(Method method) {
        Browser.NewContextOptions options =
                new Browser.NewContextOptions()
                        .setRecordVideoDir(Paths.get("videos/"))
                        .setRecordVideoSize(1280, 720);

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

        // 🔹 Stop tracing
        context.tracing().stop(
                new Tracing.StopOptions()
                        .setPath(Paths.get("traces/" + result.getName() + ".zip"))
        );

        // 🔹 Screenshot on failure
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                Path screenshotDir = Paths.get("screenshots");
                Files.createDirectories(screenshotDir);

                String screenshotPath = "screenshots/" + result.getName() + ".png";

                page.screenshot(
                        new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath))
                );

                test.fail(result.getThrowable());
                test.addScreenCaptureFromPath(screenshotPath);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 🔹 Close context FIRST (video is finalized here)
        context.close();

        // 🔹 Handle VIDEO rename / retention
        try {
            Path originalVideo = page.video().path();

            String timestamp = TimeUtil.getTimestamp();
            String newVideoName = "WEB_" + timestamp + ".webm";

            Path targetDir = Paths.get("videos");
            Files.createDirectories(targetDir);

            Path renamedVideo = targetDir.resolve(newVideoName);

            // Option 1: Record ALL videos
            if (Config.VIDEO_RECORDING.equalsIgnoreCase("true")) {
                Files.move(originalVideo, renamedVideo);
            }

            // Option 2: Record ONLY failed videos
            else if (Config.VIDEO_RECORDING.equalsIgnoreCase("false")) {
                if (result.getStatus() == ITestResult.FAILURE) {
                    Files.move(originalVideo, renamedVideo);
                } else {
                    Files.deleteIfExists(originalVideo);
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
