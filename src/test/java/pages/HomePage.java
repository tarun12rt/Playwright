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

    /* ===== Recommended For You ===== */
    private final Locator recommendedSection;
    private final Locator recommendedCards;
    private final Locator carouselNextButton;
    private final Locator carouselPrevButton;


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
        latestArticleImages = page.locator("//h2[normalize-space()='Latest']/parent::section//img");
        latestArticleTitles = page.locator("section:has-text('Latest') h3, section:has-text('Latest') h2");

        /* Article */
        articleBody = page.locator("article");

        /* Recommended For You */
        recommendedSection = page.locator("(//h2[normalize-space()='Recommended For You'])[1]/parent::section");
        recommendedCards = recommendedSection.locator("//h5/a[@tabindex='0']");

        carouselNextButton = recommendedSection.locator("//button[@aria-label=\"Next\"] ");
        carouselPrevButton = recommendedSection.locator("//button[@aria-label=\"Previous\"]");

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

    public boolean verifyRecommendedCarouselNext() {
        scrollTo(recommendedSection);
        String before = getFirstRecommendedCardTitle();

        safeClick(carouselNextButton);
        page.waitForTimeout(5000); // small wait for slide

        String after = getFirstRecommendedCardTitle();
        return !before.equals(after);
    }

    public boolean verifyRecommendedCarouselPrevious() {
        // Move once forward first
        scrollTo(recommendedSection);
        safeClick(carouselNextButton);
        page.waitForTimeout(500);

        String before = getFirstRecommendedCardTitle();

        safeClick(carouselPrevButton);
        page.waitForTimeout(5000);

        String after = getFirstRecommendedCardTitle();
        return !before.equals(after);
    }

    private String getFirstRecommendedCardTitle() {
        return recommendedCards.first().innerText().trim();
    }

    public boolean recommendedItemsHaveCategory() {
        scrollTo(recommendedSection);
        Locator categories = page.locator(
                "//h2[normalize-space()='Recommended For You']/parent::section//p//a"
        );

        for (Locator category : categories.all()) {
            String text = category.textContent();
            if (text == null || text.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean isRecommendedItemClickable() {
        scrollTo(recommendedSection);
        Locator links = page.locator("//h2[normalize-space()='Recommended For You']/parent::section//h5/a[@tabindex=\"0\"]");
        return links.count() > 0
                && links.first().isVisible()
                && links.first().isEnabled();
    }

    public boolean recommendedItemsHaveImage() {
        scrollTo(recommendedSection);
        Locator images = page.locator("//h2[normalize-space()='Recommended For You']/parent::section//a[@tabindex=\"0\"]/img");
        for (Locator img : images.all()) {
            if (!(Boolean) img.evaluate("i => i.complete && i.naturalWidth > 0")) {
                return false;
            }
        }
        return true;
    }

    public boolean recommendedItemsHaveTitle() {
        Locator titles = page.locator(
                "//h2[normalize-space()='Recommended For You']/parent::section//h3"
        );

        for (Locator title : titles.all()) {
            String text = title.textContent();
            if (text == null || text.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }





    public boolean allLatestArticlesAreClickable() {
        Locator links = page.locator("//h2[normalize-space()='Latest']/parent::section//h3//a");

        for (Locator link : links.all()) {
            if (!link.isVisible() || !link.isEnabled()) {
                return false;
            }
        }
        return true;
    }


    public boolean allLatestArticleImagesAreLoaded() {
        for (Locator img : latestArticleImages.all()) {
            Boolean loaded = (Boolean) img.evaluate(
                    "i => i.complete && i.naturalWidth > 0"
            );

            if (!loaded) {
                return false;
            }
        }
        return true;
    }



    public boolean allLatestArticlesHaveCategory() {
        Locator categories = page.locator(
                "//h2[normalize-space()='Latest']/parent::section//p//a"
        );

        for (Locator category : categories.all()) {
            String text = category.textContent();
            if (text == null || text.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }


}
