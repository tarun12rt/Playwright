package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AsianEntertainmentPage extends BasePage {

    /* ================= LOCATORS ================= */

    // Navigation
    private final Locator entertainmentMenu;
    private final Locator asianSubMenu;

    // Page Heading
    private final Locator asianHeading;

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

    public AsianEntertainmentPage(Page page) {
        super(page);

        /* ===== Navigation ===== */
        entertainmentMenu = page.locator("//nav[@id='main-nav']//a[@href=\"/entertainment\"]");
        asianSubMenu = page.locator("//ul[contains(@class,'sub')]//a[text()='Asian']");

        /* ===== Page Heading ===== */
        asianHeading = page.locator("//h1[normalize-space()='Asian']");

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
        articleBreadcrumb = page.locator("//a[contains(@class,'breadcrumb__link') and normalize-space()='Asian']");
    }

    /* ================= ACTIONS ================= */

    public void open() {
        openBaseUrl();

        // Hover on Entertainment
        entertainmentMenu.hover();

        asianSubMenu.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
        );

        safeClick(asianSubMenu);
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

    /* ================= VERIFICATIONS ================= */

    public boolean isAsianPageVisible() {
        return waitUntilVisible(asianHeading);
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
}