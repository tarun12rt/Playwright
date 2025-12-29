package base;

import com.microsoft.playwright.*;
import com.aventstack.extentreports.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentManager;

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

    @BeforeClass
    public void globalSetup() {
        extent = ExtentManager.getExtent();

        playwright = Playwright.create();
        browser = playwright.webkit().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setRecordVideoDir(Paths.get("videos/"))
                        .setRecordVideoSize(1280, 720)
        );

        context.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );

        page = context.newPage();
    }

    @AfterMethod
    public void captureFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && test != null) {
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
    }

    @AfterClass
    public void globalTearDown() {
        context.tracing().stop(
                new Tracing.StopOptions().setPath(Paths.get("traces/trace.zip"))
        );

        browser.close();
        playwright.close();
        extent.flush();
    }
}
