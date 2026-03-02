package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.EatAndDrinkNewsReviewsPage;

public class EatAndDrinkNewsReviewsSanityTest extends BaseTestParallelExecution {

    private EatAndDrinkNewsReviewsPage getPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        EatAndDrinkNewsReviewsPage page = new EatAndDrinkNewsReviewsPage(page());
        page.open();
        return page;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifyNewsReviewsPageLoads() {
        EatAndDrinkNewsReviewsPage page = getPage();
        Assert.assertTrue(page.isNewsReviewsPageVisible());
    }

    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSectionVisible() {
        EatAndDrinkNewsReviewsPage page = getPage();
        Assert.assertTrue(page.isHeroSectionVisible());
        Assert.assertTrue(page.isHeroTitleVisible());
        Assert.assertTrue(page.isHeroReadNowButtonVisible());
    }

    @Test
    public void verifyHeroReadNowNavigation() {
        EatAndDrinkNewsReviewsPage page = getPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticlePageOpened());
    }

    /* ================= ARTICLE LIST ================= */

    @Test
    public void verifyArticleListVisible() {
        EatAndDrinkNewsReviewsPage page = getPage();
        Assert.assertTrue(page.isArticleListVisible());
        Assert.assertTrue(page.hasAtLeastOneArticle());
    }

    @Test
    public void verifyArticleNavigation() {
        EatAndDrinkNewsReviewsPage page = getPage();
        page.clickFirstArticle();
        Assert.assertTrue(page.isArticlePageOpened());
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreButtonVisible() {
        EatAndDrinkNewsReviewsPage page = getPage();
        Assert.assertTrue(page.isLoadMoreButtonVisible());
    }

    @Test
    public void verifyLoadMoreFunctionality() {
        EatAndDrinkNewsReviewsPage page = getPage();
        int before = page.getArticleCount();
        page.clickLoadMore();
        int after = page.getArticleCount();
        Assert.assertTrue(after > before);
    }
}