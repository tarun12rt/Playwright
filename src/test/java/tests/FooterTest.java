package tests;

import base.BaseTestParallelExecution;
import com.microsoft.playwright.Page;
import enums.FooterLinkNavigation;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.FooterPage;

import java.util.List;

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
    public void verifyPageNavigationOfFooterLinks() {

        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());

        FooterPage footerPage = new FooterPage(page());
        footerPage.open();

        /*int index = 1;

        for (FooterLinkNavigation link : FooterLinkNavigation.values()) {

            test().info("[" + index + "] Clicking footer link: " + link.getLinkText());

            Page navigatedPage =
                    footerPage.clickFooterLinkAndReturn(link);

            String actualUrl = navigatedPage.url();
            String expectedUrl = footerPage.resolveExpectedUrl(link);

            test().info("[" + index + "] Navigated URL: " + actualUrl);

            Assert.assertTrue(
                    actualUrl.contains(expectedUrl),
                    "Navigation failed for " + link.getLinkText()
                            + " | Expected: " + expectedUrl
                            + " | Actual: " + actualUrl
            );

            test().info("[" + index + "] Navigation verified for: " + link.getLinkText());

            footerPage.returnToHome(navigatedPage);

            index++;
        }

        test().pass("All footer links navigated correctly and returned to home");*/
    }


    @Test
    public void verifyPageNavigationOfAbout8DaysFooterLink() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        FooterPage footerPage = new FooterPage(page());
        footerPage.open();
        String aboutDaysPageUrl = "https://www.8days.sg/about";
        Assert.assertTrue(footerPage.verifyPageNavigationOfAbout8DaysFooterLink(aboutDaysPageUrl));
    }

    @Test
    public void verifyPageNavigationOfContactUsFooterLink() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        FooterPage footerPage = new FooterPage(page());
        footerPage.open();
        String contactUsPageUrl = "https://www.8days.sg/contactus";
        Assert.assertTrue(footerPage.verifyPageNavigationOfContactUsFooterLink(contactUsPageUrl));
    }

    @Test
    public void verifyPageNavigationOfAdvertiseFooterLink() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        FooterPage footerPage = new FooterPage(page());
        footerPage.open();
        String advertisePageUrl = "https://www.mediacorp.sg/business/advertising";
        Assert.assertTrue(footerPage.verifyPageNavigationOfAdvertiseFooterLink(advertisePageUrl));
    }

    @Test
    public void verifyPageNavigationOfMediacorpDigitalNetworkFooterLink() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        FooterPage footerPage = new FooterPage(page());
        footerPage.open();
        String mediacorpDigitalNetworkPageUrl = "https://www.8days.sg/mediacorp-digital-network";
        Assert.assertTrue(footerPage.verifyPageNavigationOfMediacorpDigitalNetworkPageUrlFooterLink(mediacorpDigitalNetworkPageUrl));
    }

    @Test
    public void verifyPageNavigationOfTermsOfUseFooterLink() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        FooterPage footerPage = new FooterPage(page());
        footerPage.open();
        String termsAndConditionsPageUrl = "https://www.mediacorp.sg/terms-conditions";
        Assert.assertTrue(footerPage.verifyPageNavigationOfTermsOfUseFooterLink(termsAndConditionsPageUrl));
    }


    @Test
    public void verifyFooterLinksNavigation() {

        FooterPage footerPage = new FooterPage(page());
        footerPage.open();

        for (FooterLinkNavigation link : FooterLinkNavigation.values()) {
            test().info("Verifying navigation for: " + link.getLinkText());

            Assert.assertTrue(
                    footerPage.verifyFooterLinkNavigation(link),
                    "Navigation failed for footer link: " + link.getLinkText()
            );
        }

        test().pass("All footer link navigations verified");
    }




}
