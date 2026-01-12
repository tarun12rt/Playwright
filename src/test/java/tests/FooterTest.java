package tests;

import base.BaseTestParallelExecution;
import enums.FooterCategoryLink;
import enums.FooterLinkNavigation;
import enums.FooterSocialLink;
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
    public void verifyFooterHighlightedTextContent() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());

        FooterPage footerPage = new FooterPage(page());
        footerPage.open();
        Assert.assertTrue(footerPage.verifyFooterHighlightedTexts());
    }

    @Test
    public void verifyFooterNavigationLinks() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        FooterPage footerPage = new FooterPage(page());

        footerPage.open();
        for (FooterLinkNavigation link : FooterLinkNavigation.values()) {
            Assert.assertTrue(footerPage.verifyFooterLinkNavigation(link));
        }
    }

    @Test
    public void verifyFooterCategoryLinks() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());

        FooterPage footerPage = new FooterPage(page());
        footerPage.open();
        for (FooterCategoryLink link : FooterCategoryLink.values()) {
            Assert.assertTrue(footerPage.verifyFooterCategoryNavigation(link));
        }
    }

    @Test
    public void verifyFooterSocialMediaLinks() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());

        FooterPage footerPage = new FooterPage(page());
        footerPage.open();
        for (FooterSocialLink link : FooterSocialLink.values()) {
            Assert.assertTrue(footerPage.verifyFooterSocialMediaNavigation(link));
        }
    }






}
