package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class EntertainmentPage extends BasePage {

    /* ================= LOCATORS ================= */

    // Page Heading
    private final Locator entertainmentHeading;

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


    public EntertainmentPage(Page page) {
        super(page);

        /* Page Heading */
        entertainmentHeading = page.locator("h1:has-text('Entertainment')");

        /* Hero Section */
        heroSection = page.locator("section").filter(new Locator.FilterOptions()
                .setHas(page.locator("text=Star Awards")));
        heroTitle = heroSection.locator("h2, h3").first();
        heroReadNowButton = heroSection.locator("text=Read Now");

        /* Latest */
        latestSection = page.locator("h2:has-text('Latest')");
        latestArticles = page.locator("section:has-text('Latest') article");
        latestFirstArticle = latestArticles.first();

        /* Popular */
        popularSection = page.locator("h2:has-text('Popular')");
        popularArticles = page.locator("section:has-text('Popular') article");
        loadMoreButton = page.locator("text=Load More");

        /* Article Page */
        articleBody = page.locator("article");
    }

    /* ================= ACTIONS ================= */

    public void open() {
        openBaseUrl();
    }

    public void clickFirstLatestArticle() {
        scrollTo(latestFirstArticle);
        safeClick(latestFirstArticle);
    }

    public void clickLoadMore() {
        scrollTo(loadMoreButton);
        safeClick(loadMoreButton);
    }

    /* ================= VERIFICATIONS ================= */

    public boolean isEntertainmentPageVisible() {
        return safeIsVisible(entertainmentHeading);
    }

    /* ===== Hero ===== */

    public boolean isHeroSectionVisible() {
        scrollTo(heroSection);
        return safeIsVisible(heroSection);
    }

    public boolean isHeroTitleVisible() {
        return safeIsVisible(heroTitle);
    }

    public boolean isHeroReadNowButtonVisible() {
        return safeIsVisible(heroReadNowButton);
    }

    /* ===== Latest ===== */

    public boolean isLatestSectionVisible() {
        scrollTo(latestSection);
        return safeIsVisible(latestSection);
    }

    public boolean hasAtLeastOneLatestArticle() {
        return latestArticles.count() > 0;
    }

    /* ===== Popular ===== */

    public boolean isPopularSectionVisible() {
        scrollTo(popularSection);
        return safeIsVisible(popularSection);
    }

    public boolean hasAtLeastOnePopularArticle() {
        return popularArticles.count() > 0;
    }

    public int getPopularArticleCount() {
        return popularArticles.count();
    }

    /* ===== Article Navigation ===== */

    public boolean isArticlePageOpened() {
        return safeIsVisible(articleBody);
    }

    public void clickHeroReadNow() {
    }
}
