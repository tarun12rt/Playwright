package tests;

import base.BaseTestParallelExecution;
import enums.FooterCategoryLink;
import enums.FooterLinkNavigation;
import enums.FooterSocialLink;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FooterPage;

public class FooterTest extends BaseTestParallelExecution {

    private FooterPage getFooterPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        FooterPage footerPage = new FooterPage(page());
        footerPage.open();
        return footerPage;
    }

    @Test
    public void verify8DaysFooter() {
        FooterPage footerPage = getFooterPage();
        Assert.assertTrue(
                footerPage.verifyFooterIsVisible(),
                "Footer is not visible"
        );
    }

    @Test
    public void verifyFooterHighlightedTextContent() {
        FooterPage footerPage = getFooterPage();
        Assert.assertTrue(footerPage.verifyFooterHighlightedTexts());
    }

    @Test
    public void verifyFooterNavigationLinks() {
        FooterPage footerPage = getFooterPage();
        for (FooterLinkNavigation link : FooterLinkNavigation.values()) {
            Assert.assertTrue(
                    footerPage.verifyFooterLinkNavigation(link),
                    "Footer navigation failed for: " + link
            );
        }
    }

    @Test
    public void verifyFooterCategoryLinks() {
        FooterPage footerPage = getFooterPage();
        for (FooterCategoryLink link : FooterCategoryLink.values()) {
            Assert.assertTrue(
                    footerPage.verifyFooterCategoryNavigation(link),
                    "Footer category failed for: " + link
            );
        }
    }

    @Test
    public void verifyFooterSocialMediaLinks() {
        FooterPage footerPage = getFooterPage();
        for (FooterSocialLink link : FooterSocialLink.values()) {
            Assert.assertTrue(
                    footerPage.verifyFooterSocialMediaNavigation(link),
                    "Footer social link failed for: " + link
            );
        }
    }
}
