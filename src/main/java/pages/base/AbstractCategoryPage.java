package pages.base;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public abstract class AbstractCategoryPage extends BasePage {

    protected final String headingText;
    protected final String menuHref;
    protected final String breadcrumbText;

    protected final Locator heading;
    protected final Locator heroSection;
    protected final Locator heroTitle;
    protected final Locator heroReadNowButton;
    protected final Locator latestSection;
    protected final Locator popularSection;
    protected final Locator popularArticles;
    protected final Locator loadMoreButton;
    protected final Locator articleBreadcrumb;
    protected final Locator menu;

    public AbstractCategoryPage(Page page,
                                String headingText,
                                String menuHref,
                                String breadcrumbText) {

        super(page);

        this.headingText = headingText;
        this.menuHref = menuHref;
        this.breadcrumbText = breadcrumbText;

        heading = page.locator("//h1[normalize-space()='" + headingText + "']");
        menu = page.locator("//nav[@id='main-nav']//a[@href='" + menuHref + "']");

        heroSection = page.locator("//section[@data-title='Dynamic Listing 1']");
        heroTitle = heroSection.locator("//h2/a");
        heroReadNowButton = heroSection.locator("//a[contains(text(),'Read')]");

        latestSection = page.locator("//section[@data-title='Latest']");
        popularSection = page.locator("//section[@data-title='Popular']");
        popularArticles = popularSection.locator("//img");

        loadMoreButton = page.locator("//button[normalize-space()='LOAD MORE']");
        articleBreadcrumb = page.locator("//a[contains(@class,'breadcrumb__link') and normalize-space()='" + breadcrumbText + "']");
    }

    /* ===== ACTIONS ===== */

    public void open() {
        openBaseUrl();
        clickAndWaitForPageLoad(menu);
    }

    public void clickHeroReadNow() {
        clickAndWaitForPageLoad(heroReadNowButton);
    }

    public void clickLoadMore() {
        scrollTo(loadMoreButton);
        safeClick(loadMoreButton);
    }

    /* ===== VERIFICATIONS ===== */

    public boolean isPageVisible() {
        return waitUntilVisible(heading);
    }

    public boolean isHeroVisible() {
        heroSection.scrollIntoViewIfNeeded();
        return waitUntilVisible(heroSection);
    }

    public boolean hasPopularArticles() {
        return popularArticles.count() > 0;
    }

    public int getPopularArticleCount() {
        return popularArticles.count();
    }

    public boolean isArticleOpened() {
        return waitUntilVisible(articleBreadcrumb);
    }
}
