package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import config.Config;

import java.util.List;

public class HomePage extends BasePage {

    // --- Locators ---

    private final Locator homepageContainer;

    /* ===== Read This ===== */
    private final Locator readThisSection;
    private final Locator readThisImage;
    private final Locator readThisTitle;
    private final Locator readNowButton;

    /* ===== Latest ===== */
    private final Locator latestSection;
    private final Locator latestArticles;
    private final Locator latestArticleImages;
    private final Locator latestArticleTitles;

    /* ===== Article Page ===== */
    private final Locator articleBody;

    public HomePage(Page page) {
        super(page);

        homepageContainer = page.getByText("Read This");
        /* Read This */
        readThisSection = page.getByText("Read This");
        readThisImage = page.locator("section:has-text('Read This') img").first();
        readThisTitle = page.locator("section:has-text('Read This') h2, section:has-text('Read This') h3").first();
        readNowButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Read now"));
        /* Latest */
        latestSection = page.locator("//h2[normalize-space()='Latest']");
        latestArticles = page.locator("//h2[normalize-space()='Latest']/parent::section//img");
        latestArticleImages = page.locator("section:has-text('Latest') img");
        latestArticleTitles = page.locator("section:has-text('Latest') h3, section:has-text('Latest') h2");

        /* Article */
        articleBody = page.locator("article");
    }

    // --- Actions ---

    public void open() {
        openBaseUrl();
    }

    public void clickReadNowButton() {
        safeClick(readNowButton);
    }

    public void clickFirstLatestArticle() {
        safeClick(latestArticles.first());
    }

    // --- Verifications ---

    public boolean isHomepageVisible() {
        return safeIsVisible(homepageContainer);
    }

    /* ===== Read This ===== */

    public boolean isReadThisSectionVisible() {
        return safeIsVisible(readThisSection);
    }

    public boolean isReadThisImageVisible() {
        return safeIsVisible(readThisImage);
    }

    public boolean isReadThisTitleVisible() {
        return safeIsVisible(readThisTitle);
    }

    public boolean isReadNowButtonVisible() {
        return safeIsVisible(readNowButton);
    }

    /* ===== Latest ===== */

    public boolean isLatestSectionVisible() {
        return safeIsVisible(latestSection);
    }

    public int getLatestArticlesCount() {
        return latestArticles.count();
    }

    public boolean allLatestArticlesHaveImage() {
        return latestArticleImages.count() >= latestArticles.count();
    }

    public boolean allLatestArticlesHaveTitle() {
        return latestArticleTitles.count() >= latestArticles.count();
    }

    /* ===== Generic Section Handling ===== */

    public boolean isSectionVisible(String sectionName) {
        Locator sectionHeader = page.locator("h2", new Page.LocatorOptions()
                                            .setHasText(sectionName))
                                    .first();

        scrollTo(sectionHeader);
        return safeIsVisible(sectionHeader);
    }


    public boolean hasAtLeastOneItem(String sectionName) {
        Locator sectionArticles =
                page.locator("section:has-text('" + sectionName + "') article");
        scrollTo(sectionArticles.first());
        return sectionArticles.count() > 0;
    }

    /* ===== Article ===== */

    public boolean isArticlePageOpened() {
        return safeIsVisible(articleBody);
    }

    public boolean isNavigatedAwayFromHome() {
        return !page.url().equals(Config.get("baseUrl"));
    }

}
