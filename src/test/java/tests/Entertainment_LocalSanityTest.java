package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.Entertainment_LocalPage;

public class Entertainment_LocalSanityTest extends BaseTestParallelExecution {

    private Entertainment_LocalPage getLocalPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        Entertainment_LocalPage page = new Entertainment_LocalPage(page());
        page.open();
        return page;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifyLocalPageLoads() {
        Entertainment_LocalPage page = getLocalPage();
        Assert.assertTrue(page.isPageVisible(), "Local page heading not visible");
    }

    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSectionVisible() {
        Entertainment_LocalPage page = getLocalPage();
        Assert.assertTrue(page.isHeroVisible(), "Hero section not visible");
        Assert.assertTrue(page.isHeroTitleVisible(), "Hero title not visible");
        Assert.assertTrue(page.isHeroReadNowButtonVisible(), "Hero Read Now not visible");
    }

    @Test
    public void verifyHeroReadNowNavigation() {
        Entertainment_LocalPage page = getLocalPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticleOpened(), "Hero article did not open");
    }

    /* ================= ARTICLE LIST SECTION ================= */

    @Test
    public void verifyArticleListVisible() {
        Entertainment_LocalPage page = getLocalPage();
        Assert.assertTrue(page.isArticleSectionVisible(), "Article list not visible");
        Assert.assertTrue(page.hasAtLeastOneArticle(), "No articles found");
    }

    @Test
    public void verifyArticleNavigation() {
        Entertainment_LocalPage page = getLocalPage();
        page.clickFirstArticle();
        Assert.assertTrue(page.isArticleOpened(), "Article did not open");
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreButtonVisible() {
        Entertainment_LocalPage page = getLocalPage();
        Assert.assertTrue(page.isLoadMoreButtonVisible(), "Load More button not visible");
    }

    @Test
    public void verifyLoadMoreFunctionality() {
        Entertainment_LocalPage page = getLocalPage();
        int before = page.getArticleCount();
        page.clickLoadMore();
        int after = page.getArticleCount();
        Assert.assertTrue(after > before, "Load More did not increase article count");
    }
}