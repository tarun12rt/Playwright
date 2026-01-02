package tests;

import base.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.FooterPage;

public class FooterTest extends BaseTestParallelExecution {

    @Test
    public void verify8DaysFooter() {

        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());

        FooterPage footerPage = new FooterPage(page());
        footerPage.open();

        Assert.assertTrue(footerPage.verifyFooterIsVisible(),"Footer is not visible");

        test().pass("Footer verified successfully");
    }

    @Test
    public void verify8DaysFooter_SecondTest() {

        System.out.println(
                "Running on Thread ID: " + Thread.currentThread().getId()
        );

        FooterPage footerPage = new FooterPage(page());
        footerPage.open();

        Assert.assertTrue(footerPage.verifyFooterIsVisible(), "Footer is not visible");

        test().pass("Footer verified successfully - Second test");
    }



}
