package pages.subcategories;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.base.BasePage;

public class EatAndDrinkNewsReviewsPage extends BasePage {

    /* ================= LOCATORS ================= */

    // Navigation
    private final Locator eatAndDrinkMenu;
    private final Locator newsAndReviewsSubMenu;

    // Page Heading
    private final Locator newsReviewsHeading;

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

    public EatAndDrinkNewsReviewsPage(Page page) {
        super(page);

        /* ===== Navigation ===== */
        eatAndDrinkMenu =
                page.locator("//nav[@id='main-nav']//a[@href=\"/eatanddrink\"]");

        newsAndReviewsSubMenu =
                page.locator("//a[contains(@href,'/eatanddrink/newsandreviews')]");

        /* ===== Page Heading ===== */
        newsReviewsHeading =
                page.locator("//h1[normalize-space()='NEWS & REVIEWS']");

        /* ===== Hero Section ===== */
        heroSection =
                page.locator("//section[contains(@class,'top_stories') or contains(@class,'listing')]");

        heroTitle =
                heroSection.locator("//h2/a");

        heroReadNowButton =
                heroSection.locator("//a[contains(text(),'Read')]");

        /* ===== Articles Section ===== */
        articleList =
                page.locator("//div[contains(@class,'scroll') or contains(@class,'listing')]");

        articles =
                articleList.locator("//img");

        firstArticle =
                articles.first();

        /* ===== Load More ===== */
        loadMoreButton =
                page.locator("//button[normalize-space()='LOAD MORE']");

        /* ===== Article Page ===== */
        articleBreadcrumb =
                page.locator("#block-breadcrumbs");
    }

    /* ================= ACTIONS ================= */

    public void open() {
        openBaseUrl();
        waitUntilVisible(eatAndDrinkMenu);
        eatAndDrinkMenu.hover();
        clickAndWaitForPageLoad(newsAndReviewsSubMenu);
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

    public boolean isNewsReviewsPageVisible() {
        return waitUntilVisible(newsReviewsHeading);
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