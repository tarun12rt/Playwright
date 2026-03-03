package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.categories.EatAndDrinkPage;

public class EatAndDrinkSanityTest extends BaseTestParallelExecution {

    private EatAndDrinkPage getEatAndDrinkPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        EatAndDrinkPage page = new EatAndDrinkPage(page());
        page.open();
        return page;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifyEatAndDrinkPageLoads() {
        EatAndDrinkPage page = getEatAndDrinkPage();
        Assert.assertTrue(page.isPageVisible(), "Page heading not visible");
    }

    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSectionVisible() {
        EatAndDrinkPage page = getEatAndDrinkPage();
        Assert.assertTrue(page.isHeroVisible(), "Hero section not visible");
        Assert.assertTrue(page.isHeroTitleVisible(), "Hero title not visible");
        Assert.assertTrue(page.isHeroReadNowButtonVisible(), "Hero Read Now button not visible");
    }

    @Test
    public void verifyHeroReadNowNavigation() {
        EatAndDrinkPage page = getEatAndDrinkPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticleOpened(), "Hero article did not open");
    }

    /* ================= LATEST SECTION ================= */

    @Test
    public void verifyLatestSectionVisible() {
        EatAndDrinkPage page = getEatAndDrinkPage();
        Assert.assertTrue(page.isLatestSectionVisible(), "Latest section not visible");
        Assert.assertTrue(page.hasAtLeastOneLatestArticle(), "No latest articles found");
    }

    @Test
    public void verifyLatestArticleNavigation() {
        EatAndDrinkPage page = getEatAndDrinkPage();
        page.clickFirstLatestArticle();
        Assert.assertTrue(page.isArticleOpened(), "Latest article did not open");
    }

    /* ================= POPULAR SECTION ================= */

    @Test
    public void verifyPopularSectionVisible() {
        EatAndDrinkPage page = getEatAndDrinkPage();
        Assert.assertTrue(page.isPopularSectionVisible(), "Popular section not visible");
        Assert.assertTrue(page.hasPopularArticles(), "No popular articles found");
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreWorks() {
        EatAndDrinkPage page = getEatAndDrinkPage();
        int before = page.getPopularArticleCount();
        page.clickLoadMore();
        int after = page.getPopularArticleCount();
        Assert.assertTrue(after > before, "Load More did not increase article count");
    }
}