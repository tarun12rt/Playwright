package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import config.Config;
import enums.FooterLinkNavigation;

import java.util.ArrayList;
import java.util.List;

public class FooterPage extends BasePage {

    private final Locator onlineLinksPolicy;
    private final Locator about8Days;
    private final Locator contactUs;
    private final Locator advertise;
    private final Locator mediacorpDigitalNetwork;
    private final Locator termsOfUse;
    private final Locator privacyPolicy;
    private final Locator vulnerabilityDisclosure;
    private final Locator entertainment;
    private final Locator eatAndDrink;
    private final Locator seeAndDo;
    private final Locator liveAndLearn;

    public FooterPage(Page page) {
        super(page);
        this.onlineLinksPolicy = page.getByText("Online Links Policy");
        this.about8Days = page.getByText("About 8days");
        this.contactUs = page.locator("//a[normalize-space()='Contact Us']");
        this.advertise = page.locator("//a[normalize-space()='Advertise']");
        this.mediacorpDigitalNetwork = page.getByText("Mediacorp Digital Network");
        this.termsOfUse = page.getByText("Terms Of Use");
        this.privacyPolicy = page.getByText("Privacy Policy");
        this.vulnerabilityDisclosure = page.getByText("Vulnerability Disclosure");
        this.entertainment = page.locator("//a[normalize-space()='ENTERTAINMENT']");
        this.eatAndDrink = page.locator("//a[normalize-space()='EAT & DRINK']");
        this.seeAndDo = page.locator("//a[normalize-space()='SEE & DO']");
        this.liveAndLearn = page.locator("//a[normalize-space()='LIVE & LEARN']");


    }


    public boolean isFooterVisible() {
        return safeIsVisible(onlineLinksPolicy);
    }

    public void open() {
        openBaseUrl();
    }

    public boolean verifyFooterIsVisible() {
        scrollTo(onlineLinksPolicy);
        return isFooterVisible();
    }

    public boolean verifyFooterLinksAreVisible() {
        scrollTo(onlineLinksPolicy);
        return safeIsVisible(about8Days) && safeIsVisible(onlineLinksPolicy) && safeIsVisible(contactUs) && safeIsVisible(advertise) && safeIsVisible(mediacorpDigitalNetwork) && safeIsVisible(termsOfUse) && safeIsVisible(privacyPolicy) && safeIsVisible(vulnerabilityDisclosure);
    }

    public boolean verifyFooterLinks() {
        scrollTo(onlineLinksPolicy);
        boolean b1 = safeIsVisible(about8Days);
        boolean b2 = safeIsVisible(onlineLinksPolicy);
        boolean b3 = safeIsVisible(contactUs);
        boolean b4 = safeIsVisible(advertise);
        boolean b5 = safeIsVisible(mediacorpDigitalNetwork);
        boolean b6 = safeIsVisible(termsOfUse);
        boolean b7 = safeIsVisible(privacyPolicy);
        boolean b8 = safeIsVisible(vulnerabilityDisclosure);

        return b1 && b2 && b3 && b4 && b5 && b6 && b7 && b8;
    }

    public boolean verifyCategoryLinksInFooterAreVisible() {
        boolean b1 = safeIsVisible(entertainment);
        boolean b2 = safeIsVisible(eatAndDrink);
        boolean b3 = safeIsVisible(seeAndDo);
        boolean b4 = safeIsVisible(liveAndLearn);

        return b1 && b2 && b3 && b4;
    }

    public boolean verifyNavigation(Page navigatedPage, FooterLinkNavigation link) {
        return navigatedPage.url().contains(link.getExpectedUrl());
    }
    public Page clickFooterLink(FooterLinkNavigation link) {

        Locator footerLink = byText(link.getLinkText());
        scrollTo(footerLink);

        System.out.println("Clicking footer link UI text: " + link.getLinkText());

        try {
            Page newPage = page.context().waitForPage(() -> {
                footerLink.click();
            });
            newPage.waitForLoadState();
            return newPage;

        } catch (Exception e) {
            footerLink.click();
            page.waitForLoadState();
            return page;
        }
    }


    public String resolveExpectedUrl(FooterLinkNavigation link) {
        String expected = link.getExpectedUrl();

        // External URLs (absolute)
        if (expected.startsWith("https")) {
            return expected;
        }

        // Internal URLs (relative)
        return Config.get("baseUrl") + expected;
    }

    public Page clickFooterLinkAndReturn(FooterLinkNavigation link) {

        Locator footerLink = byText(link.getLinkText());
        scrollTo(footerLink);

        Page navigatedPage;

        try {
            // NEW TAB case
            navigatedPage = page.context().waitForPage(() -> {
                footerLink.click();
            });
            navigatedPage.waitForLoadState();
            return navigatedPage;

        } catch (Exception e) {
            // SAME TAB case
            footerLink.click();
            page.waitForLoadState();
            return page;
        }
    }
    public void returnToHome(Page navigatedPage) {

        // New tab → close it
        if (navigatedPage != page) {
            navigatedPage.close();
        }
        // Same tab → go back
        else {
            page.goBack();
            page.waitForLoadState();
        }

        // Scroll footer again for next link
        scrollTo(byText("Online Links Policy"));
    }

    public boolean verifyPageNavigationOfAbout8DaysFooterLink(String aboutDaysPageUrl) {
        scrollTo(about8Days);
        safeClick(about8Days);
        String currentPageUrl = page.url();
        return aboutDaysPageUrl.equals(currentPageUrl);

    }


    public boolean verifyPageNavigationOfContactUsFooterLink(String contactUsPageUrl) {
        scrollTo(about8Days);
        safeClick(contactUs);
        String currentPageUrl = page.url();
        return contactUsPageUrl.equals(currentPageUrl);
    }

    public boolean verifyPageNavigationOfAdvertiseFooterLink(String advertisePageUrl) {
        scrollTo(about8Days);
        Page navigatedPage = clickAndSwitchToNewPage(advertise);
        String currentPageUrl = navigatedPage.url();
        return currentPageUrl.contains(advertisePageUrl);
    }


    public boolean verifyPageNavigationOfMediacorpDigitalNetworkPageUrlFooterLink(String mediacorpDigitalNetworkPageUrl) {
        scrollTo(about8Days);
        safeClick(mediacorpDigitalNetwork);
        String currentPageUrl = page.url();
        return mediacorpDigitalNetworkPageUrl.equals(currentPageUrl);
    }

    public boolean verifyFooterLinkNavigation(FooterLinkNavigation link) {

        // 1️⃣ Scroll to footer
        scrollTo(about8Days);

        Locator locator = byText(link.getLinkText());

        // 2️⃣ Click & get navigated page (same tab or new tab)
        Page navigatedPage = clickAndSwitchToNewPage(locator);

        // 3️⃣ Wait until URL is available
        navigatedPage.waitForURL(url -> url != null && !url.isEmpty());

        String actualUrl = navigatedPage.url();
        String expectedUrl = link.getExpectedUrl();

        boolean isNavigationCorrect = actualUrl.contains(expectedUrl);

        // 4️⃣ Restore state (VERY IMPORTANT)
        restoreToOriginalPage(navigatedPage);

        // 5️⃣ Scroll back to footer for next iteration
        scrollTo(about8Days);

        return isNavigationCorrect;
    }


    public boolean verifyPageNavigationOfTermsOfUseFooterLink(String termsAndConditionsPageUrl) {
        scrollTo(about8Days);
        Page navigatedPage =clickAndSwitchToNewPage(termsOfUse);
        String actualUrl = navigatedPage.url();

        if (navigatedPage != page) {
            navigatedPage.close();
            page.bringToFront();
        } else {
            page.goBack();
            page.waitForLoadState();
        }

        return termsAndConditionsPageUrl.equals(actualUrl);

    }
}
