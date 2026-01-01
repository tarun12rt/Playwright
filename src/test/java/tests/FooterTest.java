package tests;

import base.BaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FooterPage;

public class FooterTest extends BaseTest {

    @Test
    public void verify8DaysFooter() {

        FooterPage footerPage = new FooterPage(page);
        footerPage.open();
        Assert.assertTrue(footerPage.verifyFooterIsVisible(),"Footer is not visible");
        test.pass("Footer verified successfully");
    }


}
