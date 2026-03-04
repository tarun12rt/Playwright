package pages.base;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public abstract class AbstractSubCategoryPage extends BasePage {

    protected final String mainMenuHref;
    protected final String subMenuText;
    protected final String headingText;
    protected final String breadcrumbText;

    protected final Locator mainMenu;
    protected final Locator subMenu;
    protected final Locator heading;

    protected final Locator heroSection;
    protected final Locator heroTitle;
    protected final Locator heroReadNowButton;

    protected final Locator articleList;
    protected final Locator articles;
    protected final Locator firstArticle;

    protected final Locator loadMoreButton;
    protected final Locator articleBreadcrumb;

    public AbstractSubCategoryPage(Page page,
                                   String mainMenuHref,
                                   String subMenuText,
                                   String headingText,
                                   String breadcrumbText) {

        super(page);

        this.mainMenuHref = mainMenuHref;
        this.subMenuText = subMenuText;
        this.headingText = headingText;
        this.breadcrumbText = breadcrumbText;

        /* ===== Navigation ===== */
        mainMenu = page.locator("//nav[@id='main-nav']//a[@href='" + mainMenuHref + "']");
        subMenu = page.locator("//ul[contains(@class,'sub')]//a[text()='" + subMenuText + "']");

        /* ===== Heading ===== */
        heading = page.locator("//h1[normalize-space()='" + headingText + "']");

        /* ===== Hero ===== */
        heroSection = page.locator("//section[@data-title='Dynamic Listing 1']");
        heroTitle = heroSection.locator("h2 a");
        heroReadNowButton = heroSection.locator("a:has-text('Read')");

        /* ===== Articles ===== */
        articleList = page.locator("//div[contains(@class,'scroll-listing')]");
        articles = articleList.locator("img");
        firstArticle = articles.first();

        /* ===== Load More ===== */
        loadMoreButton = articleList.locator("button:has-text('LOAD MORE')");

        /* ===== Breadcrumb ===== */
        articleBreadcrumb = page.locator(
                "//a[contains(@class,'breadcrumb__link') and normalize-space()='" + breadcrumbText + "']");
    }

    /* ================= ACTIONS ================= */

    public void open() {
        openBaseUrl();

        mainMenu.hover();

        subMenu.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
        );

        safeClick(subMenu);
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

    public boolean isPageVisible() {
        return waitUntilVisible(heading);
    }

    /* ===== Hero ===== */

    public boolean isHeroVisible() {
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

    public boolean isArticleOpened() {
        return waitUntilVisible(articleBreadcrumb);
    }
}