package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FooterTest extends BaseTest {

    @Test
    public void verify8DaysFooter() {

        test = extent.createTest("Verify 8Days Footer");

        test.info("Navigating to 8days");
        page.navigate("https://www.8days.sg");

        test.info("Verifying title");
        Assert.assertTrue(page.title().toLowerCase().contains("8days"));
        test.pass("Title verified");

        test.info("Verifying footer links");
        test.info("Scrolling to footer");

        page.getByText("Online Links Policy").scrollIntoViewIfNeeded();
        test.pass("Scrolled to footer");


    }
}
