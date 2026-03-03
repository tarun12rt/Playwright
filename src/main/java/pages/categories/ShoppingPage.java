package pages.categories;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.base.BasePage;

public class ShoppingPage extends BasePage {

    /* ================= LOCATORS ================= */

    // Page Heading
    private final Locator heading;

    /* ===== Hero Section ===== */
    private final Locator heroSection;
    private final Locator heroTitle;

    /* ===== 8days Tested ===== */
    private final Locator testedSection;
    private final Locator testedArticles;

    /* ===== Latest ===== */
    private final Locator latestSection;
    private final Locator latestArticles;

    /* ===== Deals ===== */
    private final Locator dealsSection;
    private final Locator dealsArticles;

    /* ===== Check These Out ===== */
    private final Locator checkTheseOutSection;
    private final Locator checkTheseOutArticles;

    /* ===== Load More ===== */
    private final Locator loadMoreButton;

    /* ===== Article Page ===== */
    private final Locator articleBreadcrumb;

    private final Locator menu;

    public ShoppingPage(Page page) {
        super(page);

        /* ===== Heading ===== */
        heading = page.locator("//h1[normalize-space()='Shopping']");

        /* ===== Hero ===== */
        heroSection = page.locator("//h1[normalize-space()='Shopping']/following::div[contains(@class,'article')][1]");
        heroTitle = heroSection.locator("h2 a");

        /* ===== 8days Tested ===== */
        testedSection = page.locator("//h2[normalize-space()='8days Tested']");
        testedArticles = testedSection.locator("xpath=following::div[contains(@class,'article')]");

        /* ===== Latest ===== */
        latestSection = page.locator("//h2[normalize-space()='Latest']");
        latestArticles = latestSection.locator("xpath=following::div[contains(@class,'article')]");

        /* ===== Deals ===== */
        dealsSection = page.locator("//h2[normalize-space()='Deals']");
        dealsArticles = dealsSection.locator("xpath=following::div[contains(@class,'article')]");

        /* ===== Check These Out ===== */
        checkTheseOutSection = page.locator("//h2[normalize-space()='Check These Out']");
        checkTheseOutArticles = checkTheseOutSection.locator("xpath=following::div[contains(@class,'article')]");

        /* ===== Load More ===== */
        loadMoreButton = page.locator("//button[normalize-space()='LOAD MORE']");

        /* ===== Breadcrumb ===== */
        articleBreadcrumb = page.locator(
                "//a[contains(@class,'breadcrumb__link') and normalize-space()='Shopping']");

        /* ===== Menu ===== */
        menu = page.locator("//nav[@id='main-nav']//a[@href='/shopping']");
    }

    /* ================= ACTIONS ================= */

    public void open() {
        openBaseUrl();
        clickAndWaitForPageLoad(menu);
    }

    public void clickHeroArticle() {
        clickAndWaitForPageLoad(heroTitle);
    }

    public void clickLoadMore() {
        scrollTo(loadMoreButton);
        safeClick(loadMoreButton);
    }

    /* ================= VERIFICATIONS ================= */

    public boolean isPageVisible() {
        return waitUntilVisible(heading);
    }

    /* ===== Hero ===== */

    public boolean isHeroVisible() {
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

    public int getLatestArticleCount() {
        return latestArticles.count();
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

    public boolean isArticleOpened() {
        return waitUntilVisible(articleBreadcrumb);
    }
}