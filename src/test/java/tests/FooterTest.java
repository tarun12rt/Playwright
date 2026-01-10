package tests;

import base.BaseTestParallelExecution;
import com.microsoft.playwright.Page;
import enums.FooterLinkNavigation;
import org.testng.Assert;
import org.testng.annotations.Test;
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
    public void verify8DaysFooterLinks() {

        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        FooterPage footerPage = new FooterPage(page());
        footerPage.open();
        Assert.assertTrue(footerPage.verifyFooterLinks());
        Assert.assertTrue(footerPage.verifyCategoryLinksInFooterAreVisible());
    }

    @Test
    public void verifyFooterLinksNavigation() {

        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        FooterPage footerPage = new FooterPage(page());
        footerPage.open();
        for (FooterLinkNavigation link : FooterLinkNavigation.values()) {
            Assert.assertTrue(footerPage.verifyFooterLinkNavigation(link));
        }
    }

}
