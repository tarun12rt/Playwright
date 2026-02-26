package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class HollywoodEntertainmentPage extends BasePage {

    /* ================= LOCATORS ================= */

    // Navigation
    private final Locator entertainmentMenu;
    private final Locator hollywoodSubMenu;

    // Page Heading
    private final Locator hollywoodHeading;

    /* ===== Hero Section ===== */
    private final Locator heroSection;
    private final Locator heroTitle;
    private final Locator heroReadNowButton;

    /* ===== Articles Section ===== */
    private final Locator articleList;
    private final Locator articles;
    private final Locator firstArticle;

    /* ===== Load More ===== */
    private final Locator loadMoreButton;

    /* ===== Article Page ===== */
    private final Locator articleBreadcrumb;

    public HollywoodEntertainmentPage(Page page) {
        super(page);

        /* ===== Navigation ===== */
        entertainmentMenu = page.locator("//nav[@id='main-nav']//a[@href=\"/entertainment\"]");
        hollywoodSubMenu = page.locator("//ul[contains(@class,'sub')]//a[text()='Hollywood']");

        /* ===== Page Heading ===== */
        hollywoodHeading = page.locator("//h1[normalize-space()='Hollywood']");

        /* ===== Hero Section ===== */
        heroSection = page.locator("//section[@data-title=\"Dynamic Listing 1\"]");
        heroTitle = heroSection.locator("//h2/a");
        heroReadNowButton = heroSection.locator("//a[contains(text(),'Read')]");

        /* ===== Articles Section ===== */
        articleList = page.locator("//div[contains(@class,'scroll-listing')]");
        articles = page.locator("//img");
        firstArticle = articles.first();

        /* ===== Load More ===== */
        loadMoreButton = articleList.locator("//button[normalize-space()='LOAD MORE']");

        /* ===== Article Page ===== */
        articleBreadcrumb = page.locator("//a[contains(@class,'breadcrumb__link') and normalize-space()='Hollywood']");
    }

    /* ================= ACTIONS ================= */

    public void open() {
        openBaseUrl();

        // Hover on Entertainment
        entertainmentMenu.hover();
        safeClick(hollywoodSubMenu);
    }

    public void clickHeroReadNow() {
        clickAndWaitForPageLoad(heroReadNowButton);
    }

    public void clickFirstArticle() {
        firstArticle.scrollIntoViewIfNeeded();
        clickAndWaitForPageLoad(firstArticle);
    }

    public void clickLoadMore() {
        scrollTo(loadMoreButton);
        safeClick(loadMoreButton);
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

    /* ===== Articles ===== */

    public boolean isArticleSectionVisible() {
        articleList.scrollIntoViewIfNeeded();
        return waitUntilVisible(articleList);
    }

    public boolean hasAtLeastOneArticle() {
        return articles.count() > 0;
    }

    public int getArticleCount() {
        return articles.count();
    }

    public boolean isLoadMoreButtonVisible() {
        loadMoreButton.scrollIntoViewIfNeeded();
        return waitUntilVisible(loadMoreButton);
    }

    /* ===== Article Navigation ===== */

    public boolean isArticlePageOpened() {
        return waitUntilVisible(articleBreadcrumb);
    }

    public boolean isHollywoodPageTitleVisible() {
        return waitUntilVisible(hollywoodHeading);
    }

    public boolean isArticleListVisible() {
        articleList.scrollIntoViewIfNeeded();
        return waitUntilVisible(articleList);
    }
}