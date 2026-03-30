package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;

public class SearchSanityTest extends BaseTestParallelExecution {

    private SearchPage getSearchPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        SearchPage page = new SearchPage(page());
        page.open();
        return page;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifySearchPageLoads() {
        SearchPage page = getSearchPage();
        Assert.assertTrue(page.isSearchPageVisible(), "Search page not loaded properly");
    }

    /* ================= BASIC SEARCH ================= */

    @Test
    public void verifySearchWithValidKeyword() {
        SearchPage page = getSearchPage();
        page.search("melisten");
        Assert.assertTrue(page.isResultsDisplayed(), "Search results not displayed");
    }

    /* ================= RESULT RELEVANCE ================= */

/*    @Test
    public void verifySearchResultsContainKeyword() {
        SearchPage page = getSearchPage();
        String keyword = "melisten";
        page.search(keyword);

        Assert.assertTrue(page.areResultsRelevant(keyword),
                "Search results are not relevant to keyword");
    }*/

    /* ================= EMPTY SEARCH ================= */

    @Test
    public void verifyEmptySearch() {
        SearchPage page = getSearchPage();
        page.search("");

        Assert.assertTrue(page.isEmptySearchHandled(),
                "Empty search not handled properly");
    }

    /* ================= NO RESULT ================= */

    @Test
    public void verifyNoResultMessage() {
        SearchPage page = getSearchPage();
        page.search("xyz123abc");

        Assert.assertTrue(page.isNoResultMessageDisplayed(),
                "No result message not displayed");
    }

    /* ================= CASE SENSITIVITY ================= */

    @Test
    public void verifyCaseInsensitiveSearch() {
        SearchPage page = getSearchPage();

        page.search("melisten");
        int lowerCount = page.getResultCount();
        getSearchPage();
        page.search("MELISTEN");
        int upperCount = page.getResultCount();

        Assert.assertEquals(lowerCount, upperCount,
                "Search is case sensitive");
    }

    /* ================= FILTER ================= */

    @Test
    public void verifySortByNewest() {
        SearchPage page = getSearchPage();
        page.search("melisten");

        page.selectSortBy("Newest to Oldest");

        Assert.assertTrue(page.isSortedByNewest(),
                "Sorting by newest failed");
    }

    @Test
    public void verifyCategoryFilter() {
        SearchPage page = getSearchPage();
        page.search("melisten");

        page.selectCategory("LOCAL");

        Assert.assertTrue(page.isCategoryFiltered("LOCAL"),
                "Category filter not working");
    }

    /* ================= NAVIGATION ================= */

    @Test
    public void verifyClickOnSearchResult() {
        SearchPage page = getSearchPage();
        page.search("melisten");

        page.clickFirstResult();

        Assert.assertTrue(page.isArticleOpened(),
                "Clicking search result did not open article");
    }

    /* ================= URL VALIDATION ================= */

    @Test
    public void verifySearchUrlContainsQuery() {
        SearchPage page = getSearchPage();
        String keyword = "melisten";

        page.search(keyword);

        Assert.assertTrue(page.getCurrentUrl().contains("q=" + keyword),
                "Search URL does not contain query parameter");
    }
}