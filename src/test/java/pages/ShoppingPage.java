package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ShoppingPage extends BasePage {

    /* ================= LOCATORS ================= */

    // Heading
    private final Locator shoppingHeading;

    /* ===== Hero Section ===== */
    private final Locator heroSection;
    private final Locator heroTitle;

    /* ===== 8days Tested Section ===== */
    private final Locator testedSection;
    private final Locator testedArticles;

    /* ===== Latest Section ===== */
    private final Locator latestSection;
    private final Locator latestArticles;

    /* ===== Deals Section ===== */
    private final Locator dealsSection;
    private final Locator dealsArticles;

    /* ===== Check These Out Section ===== */
    private final Locator checkTheseOutSection;
    private final Locator checkTheseOutArticles;

    /* ===== Load More ===== */
    private final Locator loadMoreButton;

    /* ===== Article Page ===== */
    private final Locator articleBody;

    private final Locator shoppingMenu;

    public ShoppingPage(Page page) {
        super(page);

        /* Heading */
        shoppingHeading =
                page.locator("//h1[normalize-space()='Shopping']");

        /* Hero */
        heroSection =
                page.locator("//h1[normalize-space()='Shopping']/following::div[contains(@class,'article')][1]");
        heroTitle =
                heroSection.locator("//h2/a");

        /* 8days Tested */
        testedSection =
                page.locator("//h2[normalize-space()='8days Tested']");
        testedArticles =
                page.locator("//h2[normalize-space()='8days Tested']/following::div[contains(@class,'article')]");

        /* Latest */
        latestSection =
                page.locator("//h2[normalize-space()='Latest']");
        latestArticles =
                page.locator("//h2[normalize-space()='Latest']/following::div[contains(@class,'article')]");

        /* Deals */
        dealsSection =
                page.locator("//h2[normalize-space()='Deals']");
        dealsArticles =
                page.locator("//h2[normalize-space()='Deals']/following::div[contains(@class,'article')]");

        /* Check These Out */
        checkTheseOutSection =
                page.locator("//h2[normalize-space()='Check These Out']");
        checkTheseOutArticles =
                page.locator("//h2[normalize-space()='Check These Out']/following::div[contains(@class,'article')]");

        /* Load More */
        loadMoreButton =
                page.locator("//button[normalize-space()='LOAD MORE']");

        /* Article Page (breadcrumb) */
        articleBody =
                page.locator("//a[contains(@class,'breadcrumb__link') and normalize-space()='Shopping']");

        /* Menu */
        shoppingMenu =
                page.locator("//nav[@id='main-nav']//a[@href='/shopping']");
    }

    /* ================= ACTIONS ================= */

    public void open() {
        openBaseUrl();
        clickAndWaitForPageLoad(shoppingMenu);
    }

    public void clickHeroArticle() {
        clickAndWaitForPageLoad(heroTitle);
    }

    public void clickLoadMore() {
        scrollTo(loadMoreButton);
        safeClick(loadMoreButton);
    }

    /* ================= VERIFICATIONS ================= */

    public boolean isShoppingPageVisible() {
        return waitUntilVisible(shoppingHeading);
    }

    /* ===== Hero ===== */

    public boolean isHeroSectionVisible() {
        heroSection.scrollIntoViewIfNeeded();
        return waitUntilVisible(heroSection);
    }

    /* ===== 8days Tested ===== */

    public boolean isTestedSectionVisible() {
        testedSection.scrollIntoViewIfNeeded();
        return waitUntilVisible(testedSection);
    }

    public boolean hasTestedArticles() {
        return testedArticles.count() > 0;
    }

    /* ===== Latest ===== */

    public boolean isLatestSectionVisible() {
        latestSection.scrollIntoViewIfNeeded();
        return waitUntilVisible(latestSection);
    }

    public boolean hasLatestArticles() {
        return latestArticles.count() > 0;
    }

    /* ===== Deals ===== */

    public boolean isDealsSectionVisible() {
        dealsSection.scrollIntoViewIfNeeded();
        return waitUntilVisible(dealsSection);
    }

    public boolean hasDealsArticles() {
        return dealsArticles.count() > 0;
    }

    /* ===== Check These Out ===== */

    public boolean isCheckTheseOutVisible() {
        checkTheseOutSection.scrollIntoViewIfNeeded();
        return waitUntilVisible(checkTheseOutSection);
    }

    public boolean hasCheckTheseOutArticles() {
        return checkTheseOutArticles.count() > 0;
    }

    /* ===== Article Navigation ===== */

    public boolean isArticlePageOpened() {
        return waitUntilVisible(articleBody);
    }
}