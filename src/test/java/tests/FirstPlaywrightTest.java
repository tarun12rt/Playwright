package tests;

import base.BaseTestSingleExecution;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstPlaywrightTest extends BaseTestSingleExecution {

    @Test
    public void verify8DaysHomePage() {

        test = extent.createTest("Verify 8Days Home Page");

        test.info("Navigating to 8days");
        page.navigate("https://www.8days.sg");

        test.info("Verifying title");
        Assert.assertTrue(page.title().toLowerCase().contains("8days"));
        test.pass("Title verified");

        test.info("Verifying header menus");

        Assert.assertTrue(page.locator("a.main-menu__link:has-text('Entertainment')").isVisible());
        test.pass("Entertainment menu visible");

        Assert.assertTrue(page.locator("//a[normalize-space()='Eat & drink']").isVisible());
        test.pass("Eat & drink menu visible");

        Assert.assertTrue(page.locator("//a[normalize-space()='See & Do']").isVisible());
        test.pass("See & Do menu visible");

        Assert.assertTrue(page.locator("//a[normalize-space()='Live & Learn']").isVisible());
        test.pass("Live & Learn menu visible");

        Assert.assertTrue(page.locator("//a[normalize-space()='Shopping']").isVisible());
        test.pass("Shopping menu visible");

    }

}
