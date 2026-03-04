package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.HollywoodEntertainmentPage;

public class HollywoodEntertainmentSanityTest extends BaseTestParallelExecution {

    private HollywoodEntertainmentPage getHollywoodPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        HollywoodEntertainmentPage page = new HollywoodEntertainmentPage(page());
        page.open();
        return page;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifyHollywoodPageLoads() {
        HollywoodEntertainmentPage page = getHollywoodPage();
        Assert.assertTrue(page.isPageVisible(), "Hollywood page heading not visible");
    }

    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSectionVisible() {
        HollywoodEntertainmentPage page = getHollywoodPage();
        Assert.assertTrue(page.isHeroVisible(), "Hero section not visible");
        Assert.assertTrue(page.isHeroTitleVisible(), "Hero title not visible");
        Assert.assertTrue(page.isHeroReadNowButtonVisible(), "Hero Read Now button not visible");
    }

    @Test
    public void verifyHeroReadNowNavigation() {
        HollywoodEntertainmentPage page = getHollywoodPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticleOpened(), "Hero article did not open");
    }

    /* ================= ARTICLE LIST ================= */

    @Test
    public void verifyArticleSectionVisible() {
        HollywoodEntertainmentPage page = getHollywoodPage();
        Assert.assertTrue(page.isArticleSectionVisible(), "Article list not visible");
        Assert.assertTrue(page.hasAtLeastOneArticle(), "No articles found");
    }

    @Test
    public void verifyArticleNavigation() {
        HollywoodEntertainmentPage page = getHollywoodPage();
        page.clickFirstArticle();
        Assert.assertTrue(page.isArticleOpened(), "Article did not open");
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreButtonVisible() {
        HollywoodEntertainmentPage page = getHollywoodPage();
        Assert.assertTrue(page.isLoadMoreButtonVisible(), "Load More button not visible");
    }

    @Test
    public void verifyLoadMoreFunctionality() {
        HollywoodEntertainmentPage page = getHollywoodPage();
        int before = page.getArticleCount();
        page.clickLoadMore();
        int after = page.getArticleCount();
        Assert.assertTrue(after > before, "Load More did not increase article count");
    }
}