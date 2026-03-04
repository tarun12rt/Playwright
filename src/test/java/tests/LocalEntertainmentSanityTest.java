package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.LocalEntertainmentPage;

public class LocalEntertainmentSanityTest extends BaseTestParallelExecution {

    private LocalEntertainmentPage getLocalPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        LocalEntertainmentPage page = new LocalEntertainmentPage(page());
        page.open();
        return page;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifyLocalPageLoads() {
        LocalEntertainmentPage page = getLocalPage();
        Assert.assertTrue(page.isPageVisible(), "Local page heading not visible");
    }

    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSectionVisible() {
        LocalEntertainmentPage page = getLocalPage();
        Assert.assertTrue(page.isHeroVisible(), "Hero section not visible");
        Assert.assertTrue(page.isHeroTitleVisible(), "Hero title not visible");
        Assert.assertTrue(page.isHeroReadNowButtonVisible(), "Hero Read Now not visible");
    }

    @Test
    public void verifyHeroReadNowNavigation() {
        LocalEntertainmentPage page = getLocalPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticleOpened(), "Hero article did not open");
    }

    /* ================= ARTICLE LIST SECTION ================= */

    @Test
    public void verifyArticleListVisible() {
        LocalEntertainmentPage page = getLocalPage();
        Assert.assertTrue(page.isArticleSectionVisible(), "Article list not visible");
        Assert.assertTrue(page.hasAtLeastOneArticle(), "No articles found");
    }

    @Test
    public void verifyArticleNavigation() {
        LocalEntertainmentPage page = getLocalPage();
        page.clickFirstArticle();
        Assert.assertTrue(page.isArticleOpened(), "Article did not open");
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreButtonVisible() {
        LocalEntertainmentPage page = getLocalPage();
        Assert.assertTrue(page.isLoadMoreButtonVisible(), "Load More button not visible");
    }

    @Test
    public void verifyLoadMoreFunctionality() {
        LocalEntertainmentPage page = getLocalPage();
        int before = page.getArticleCount();
        page.clickLoadMore();
        int after = page.getArticleCount();
        Assert.assertTrue(after > before, "Load More did not increase article count");
    }
}