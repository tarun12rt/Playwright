package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class EatAndDrinkPage extends BasePage {

    /* ================= LOCATORS ================= */

    // Page Heading
    private final Locator eatAndDrinkHeading;

    /* ===== Hero Section ===== */
    private final Locator heroSection;
    private final Locator heroTitle;
    private final Locator heroReadNowButton;

    /* ===== Latest Section ===== */
    private final Locator latestSection;
    private final Locator latestArticles;
    private final Locator latestFirstArticle;

    /* ===== Popular Section ===== */
    private final Locator popularSection;
    private final Locator popularArticles;
    private final Locator loadMoreButton;

    /* ===== Article Page ===== */
    private final Locator articleBody;

    private final Locator eatAndDrinkMenu;

    public EatAndDrinkPage(Page page) {
        super(page);

        /* Page Heading */
        eatAndDrinkHeading = page.locator("//h1[normalize-space()='Eat & Drink']");

        /* Hero Section */
        heroSection = page.locator("//section[@data-title='Dynamic Listing 1']");
        heroTitle = heroSection.locator("//h2/a");
        heroReadNowButton = heroSection.locator("//a[normalize-space()='Read now']");

        /* Latest */
        latestSection = page.locator("//section[@data-title='Latest']");
        latestArticles = page.locator("//section[@data-title='Latest']//div//img");
        latestFirstArticle = latestArticles.first();

        /* Popular */
        popularSection = page.locator("//section[@data-title='Popular']");
        popularArticles = page.locator("//section[@data-title='Popular']//div//img");
        loadMoreButton = page.locator("//button[normalize-space()='LOAD MORE']");

        /* Article Page */
        articleBody = page.locator("//a[contains(@class,'breadcrumb__link') and normalize-space()='Eat & Drink']");

        /* Menu */
        eatAndDrinkMenu = page.locator("//nav[@id='main-nav']//a[@href='/eatanddrink']");
    }

    /* ================= ACTIONS ================= */

    public void open() {
        openBaseUrl();
        clickAndWaitForPageLoad(eatAndDrinkMenu);
    }

    public void clickHeroReadNow() {
        clickAndWaitForPageLoad(heroReadNowButton);
    }

    public void clickFirstLatestArticle() {
        latestFirstArticle.scrollIntoViewIfNeeded();
        clickAndWaitForPageLoad(latestFirstArticle);
    }

    public void clickLoadMore() {
        scrollTo(loadMoreButton);
        safeClick(loadMoreButton);
    }

    /* ================= VERIFICATIONS ================= */

    public boolean isEatAndDrinkPageVisible() {
        return waitUntilVisible(eatAndDrinkHeading);
    }

    /* ===== Hero ===== */

    public boolean isHeroSectionVisible() {
        heroSection.scrollIntoViewIfNeeded();
        return waitUntilVisible(heroSection);
    }

    public boolean isHeroTitleVisible() {
        return waitUntilVisible(heroTitle);
    }

    public boolean isHeroReadNowButtonVisible() {
        return waitUntilVisible(heroReadNowButton);
    }

    /* ===== Latest ===== */

    public boolean isLatestSectionVisible() {
        latestSection.scrollIntoViewIfNeeded();
        return waitUntilVisible(latestSection);
    }

    public boolean hasAtLeastOneLatestArticle() {
        return latestArticles.count() > 0;
    }

    /* ===== Popular ===== */

    public boolean isPopularSectionVisible() {
        popularSection.scrollIntoViewIfNeeded();
        return waitUntilVisible(popularSection);
    }

    public boolean hasAtLeastOnePopularArticle() {
        return popularArticles.count() > 0;
    }

    public int getPopularArticleCount() {
        return popularArticles.count();
    }

    /* ===== Article Navigation ===== */

    public boolean isArticlePageOpened() {
        return waitUntilVisible(articleBody);
    }
}