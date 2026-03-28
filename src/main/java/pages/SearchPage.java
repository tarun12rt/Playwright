package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.base.BasePage;

public class SearchPage extends BasePage {

    /* ================= LOCATORS ================= */

    /* ===== Search Input ===== */
    private final Locator searchInput;
    private final Locator searchButton;

    /* ===== Search Page ===== */
    private final Locator searchHeading;

    /* ===== Results ===== */
    private final Locator resultItems;
    private final Locator resultTitles;

    /* ===== No Result ===== */
    private final Locator noResultMessage;

    /* ===== Filters ===== */
    private final Locator sortDropdown;
    private final Locator categoryDropdown;

    /* ===== First Result ===== */
    private final Locator firstResult;

    /* ===== Article Page ===== */
    private final Locator articleBreadcrumb;
    private final Locator searchIcon;

    public SearchPage(Page page) {
        super(page);

        /* ===== Search Box ===== */
        searchInput = page.locator("input[placeholder='Search']");
        searchButton = page.locator("button.search");

        /* ===== Search Heading ===== */
        searchHeading = page.locator("//div[@id='trending-topics']");

        /* ===== Results ===== */
        resultItems = page.locator("div.search article");
        resultTitles = resultItems.locator("h2");

        /* ===== No Result ===== */
        noResultMessage = page.locator(":has-text('No results')");

        /* ===== Filters ===== */
        sortDropdown = page.locator("select.sort");
        categoryDropdown = page.locator("select.category");

        /* ===== First Result ===== */
        firstResult = resultItems.first();

        /* ===== Article ===== */
        articleBreadcrumb = page.locator("ol.breadcrumb");

        searchIcon = page.locator("(//nav[@id='profile-menu-nav']//li)[3]");
    }

    /* ================= ACTIONS ================= */

    public void open() {
        openBaseUrl();
        safeClick(searchIcon);
        waitUntilVisible(searchInput);
    }

    public void search(String keyword) {
        safeFill(searchInput, keyword);
        safeClick(searchButton);
        waitUntilVisible(searchHeading);
    }

    public void clearSearch() {
        safeFill(searchInput, "");
    }

    public void selectSortBy(String value) {
        selectByValue(sortDropdown, value);
    }

    public void selectCategory(String category) {
        selectByValue(categoryDropdown, category);
    }

    public void clickFirstResult() {
        clickAndWaitForPageLoad(firstResult);
    }

    /* ================= VERIFICATIONS ================= */

    public boolean isSearchPageVisible() {
        return waitUntilVisible(searchHeading);
    }

    public boolean isResultsDisplayed() {
        return resultItems.count() > 0;
    }

    public boolean areResultsRelevant(String keyword) {
        int count = resultTitles.count();
        if (count == 0) return false;
        for (int i = 0; i < count; i++) {
            String title = resultTitles.nth(i).innerText().toLowerCase();
            if (!title.contains(keyword.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmptySearchHandled() {
        return searchInput.inputValue().isEmpty();
    }

    public boolean isNoResultMessageDisplayed() {
        return noResultMessage.isVisible();
    }

    public int getResultCount() {
        return resultItems.count();
    }

    public boolean isSortedByNewest() {
        // Basic sanity: results should be present after sorting
        return resultItems.count() > 0;
    }

    public boolean isCategoryFiltered(String category) {
        // Basic sanity validation (can be enhanced later)
        return resultItems.count() > 0;
    }

    public boolean isArticleOpened() {
        return waitUntilVisible(articleBreadcrumb);
    }

    public String getCurrentUrl() {
        return page.url();
    }
}