package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LocalEntertainmentPage extends BasePage {

    /* ================= LOCATORS ================= */

    // Navigation
    private final Locator entertainmentMenu;
    private final Locator localSubMenu;

    // Page Heading
    private final Locator localHeading;

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

    public LocalEntertainmentPage(Page page) {
        super(page);

        /* ===== Navigation ===== */
        entertainmentMenu = page.locator("//nav[@id='main-nav']//a[@href=\"/entertainment\"]");
        localSubMenu = page.locator("//ul[contains(@class,'sub')]//a[text()='Local']");

        /* ===== Page Heading ===== */
        localHeading = page.locator("//h1[normalize-space()='Local']");

        /* ===== Hero Section ===== */
        heroSection = page.locator("//section[contains(@class,'top_stories')]");
        heroTitle = heroSection.locator("//h2/a");
        heroReadNowButton = heroSection.locator("//a[contains(text(),'Read')]");

        /* ===== Articles Section ===== */
        articleList = page.locator("//div[contains(@class,'scroll-listing')]");
        articles = articleList.locator("//img");
        firstArticle = articles.first();

        /* ===== Load More ===== */
        loadMoreButton = articleList.locator("//button[normalize-space()='LOAD MORE']");

        /* ===== Article Page ===== */
        articleBreadcrumb = page.locator("//a[contains(@class,'breadcrumb__link') and normalize-space()='Entertainment']");
    }

    /* ================= ACTIONS ================= */

    public void open() {
        openBaseUrl();
        waitUntilVisible(entertainmentMenu);
        entertainmentMenu.hover();
        clickAndWaitForPageLoad(localSubMenu);
    }

    public void clickHeroReadNow() {
        clickAndWaitForPageLoad(heroReadNowButton);
    }

    public void clickFirstArticle() {
        firstArticle.scrollIntoViewIfNeeded();
        clickAndWaitForPageLoad(firstArticle);
    }

    public void clickLoadMore() {
        waitUntilVisible(loadMoreButton);
        scrollTo(loadMoreButton);
        clickAndWaitForPageLoad(loadMoreButton);
    }

    /* ================= VERIFICATIONS ================= */

    public boolean isLocalPageTitleVisible() {
        return waitUntilVisible(localHeading);
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

    public boolean isArticleListVisible() {
        articleList.scrollIntoViewIfNeeded();
        return waitUntilVisible(articleList);
    }

    public boolean hasAtLeastOneArticle() {
        return articles.count() > 0;
    }

    public int getArticleCount() {
        return articles.count();
    }

    /* ===== Article Navigation ===== */

    public boolean isArticlePageOpened() {
        return waitUntilVisible(articleBreadcrumb);
    }

    public boolean isLoadMoreButtonVisible() {
        loadMoreButton.scrollIntoViewIfNeeded();
        return waitUntilVisible(loadMoreButton);
    }
}