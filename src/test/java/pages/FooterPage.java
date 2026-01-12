package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import config.Config;
import enums.FooterCategoryLink;
import enums.FooterLinkNavigation;
import enums.FooterSocialLink;

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
    private final Locator footerLogo;
    private final Locator categorySection;
    private final Locator spamTitle;
    private final Locator spamDescription;
    private final Locator consentText;
    private final Locator facebook;
    private final Locator instagram;
    private final Locator rss;
    private final Locator telegram;


    public FooterPage(Page page) {
        super(page);
        onlineLinksPolicy = page.getByText("Online Links Policy");
        about8Days = page.getByText("About 8days");
        contactUs = page.locator("//a[normalize-space()='Contact Us']");
        advertise = page.locator("//a[normalize-space()='Advertise']");
        mediacorpDigitalNetwork = page.getByText("Mediacorp Digital Network");
        termsOfUse = page.getByText("Terms Of Use");
        privacyPolicy = page.getByText("Privacy Policy");
        vulnerabilityDisclosure = page.getByText("Vulnerability Disclosure");
        entertainment = page.locator("//a[normalize-space()='ENTERTAINMENT']");
        eatAndDrink = page.locator("//a[normalize-space()='EAT & DRINK']");
        seeAndDo = page.locator("//a[normalize-space()='SEE & DO']");
        liveAndLearn = page.locator("//a[normalize-space()='LIVE & LEARN']");
        footerLogo = page.locator("//footer//img[@alt='Logo']");
        categorySection = page.locator("text=ENTERTAINMENT");
        spamTitle = page.getByText("We Hate Spam");
        spamDescription = page.locator("//p[@class='subscription__sub-heading']");
        consentText = page.locator(".subscription__term-condition");
        facebook = page.locator("//footer//a[contains(@href,'facebook')]");
        instagram = page.locator("//footer//a[contains(@href,'instagram')]");
        telegram = page.locator("//footer//a[contains(@href,'telegram')]");
        rss = page.locator("//footer//a[contains(@href,'rss')]");
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


    public boolean verifyFooterLinkNavigation(FooterLinkNavigation link) {
        scrollTo(about8Days);

        Locator locator = page.locator("footer").getByText(link.getLinkText());

        Page navigatedPage = clickAndSwitchToNewPage(locator);

        String actualUrl = navigatedPage.url();
        boolean isNavigationCorrect = actualUrl.contains(link.getExpectedUrl());

        restoreToOriginalPage(navigatedPage);

        scrollTo(about8Days);

        return isNavigationCorrect;
    }

    public boolean verifyFooterHighlightedTexts() {

        scrollTo(about8Days);

        return safeIsVisible(footerLogo)
                && safeIsVisible(spamTitle)
                && safeIsVisible(spamDescription)
                && safeIsVisible(consentText);
    }


    public boolean verifyFooterCategoryNavigation(FooterCategoryLink link) {

        scrollTo(about8Days);

        Locator locator = page.locator("footer").getByText(link.getLinkText());

        Page navigatedPage = clickAndSwitchToNewPage(locator);

        String actualUrl = navigatedPage.url();
        boolean isNavigationCorrect = actualUrl.contains(link.getExpectedPath());

        restoreToOriginalPage(navigatedPage);
        scrollTo(about8Days);

        return isNavigationCorrect;
    }

    public boolean verifyFooterSocialMediaNavigation(FooterSocialLink link) {

        scrollTo(about8Days);

        Locator locator = getSocialLocator(link);

        Page navigatedPage = clickAndSwitchToNewPage(locator);

        String actualUrl = navigatedPage.url();
        boolean isNavigationCorrect = actualUrl.contains(link.getExpectedDomain());

        restoreToOriginalPage(navigatedPage);
        scrollTo(about8Days);

        return isNavigationCorrect;
    }

    private Locator getSocialLocator(FooterSocialLink link) {
        switch (link) {
            case FACEBOOK:
                return facebook;
            case INSTAGRAM:
                return instagram;
            case TELEGRAM:
                return telegram;
            case RSS:
                return rss;
            default:
                throw new IllegalArgumentException("Unknown social link: " + link);
        }
    }




}
