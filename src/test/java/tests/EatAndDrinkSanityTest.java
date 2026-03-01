package tests;

import base.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EatAndDrinkPage;

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
        Assert.assertTrue(page.isEatAndDrinkPageVisible());
    }

    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSectionVisible() {
        EatAndDrinkPage page = getEatAndDrinkPage();
        Assert.assertTrue(page.isHeroSectionVisible());
        Assert.assertTrue(page.isHeroTitleVisible());
        Assert.assertTrue(page.isHeroReadNowButtonVisible());
    }

    @Test
    public void verifyHeroReadNowNavigation() {
        EatAndDrinkPage page = getEatAndDrinkPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticlePageOpened());
    }

    /* ================= LATEST SECTION ================= */

    @Test
    public void verifyLatestSectionVisible() {
        EatAndDrinkPage page = getEatAndDrinkPage();
        Assert.assertTrue(page.isLatestSectionVisible());
        Assert.assertTrue(page.hasAtLeastOneLatestArticle());
    }

    @Test
    public void verifyLatestArticleNavigation() {
        EatAndDrinkPage page = getEatAndDrinkPage();
        page.clickFirstLatestArticle();
        Assert.assertTrue(page.isArticlePageOpened());
    }

    /* ================= POPULAR SECTION ================= */

    @Test
    public void verifyPopularSectionVisible() {
        EatAndDrinkPage page = getEatAndDrinkPage();
        Assert.assertTrue(page.isPopularSectionVisible());
        Assert.assertTrue(page.hasAtLeastOnePopularArticle());
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreWorks() {
        EatAndDrinkPage page = getEatAndDrinkPage();
        int before = page.getPopularArticleCount();
        page.clickLoadMore();
        int after = page.getPopularArticleCount();
        Assert.assertTrue(after > before);
    }
}