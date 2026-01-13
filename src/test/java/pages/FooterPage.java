package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import enums.FooterCategoryLink;
import enums.FooterLinkNavigation;
import enums.FooterSocialLink;


public class FooterPage extends BasePage {

    private final Locator onlineLinksPolicy;
    private final Locator about8Days;
    private final Locator footerLogo;
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
        footerLogo = page.locator("//footer//img[@alt='Logo']");
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
