package tests;

import base.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HollywoodEntertainmentPage;
import pages.LocalEntertainmentPage;

public class HollywoodEntertainmentSanityTest extends BaseTestParallelExecution {

    private HollywoodEntertainmentPage getHollywoodPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        HollywoodEntertainmentPage hollywoodEntertainmentPage = new HollywoodEntertainmentPage(page());
        hollywoodEntertainmentPage.open();
        return hollywoodEntertainmentPage;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifyLocalPageLoads() {
        HollywoodEntertainmentPage hollywoodEntertainmentPage = getHollywoodPage();
        Assert.assertTrue(hollywoodEntertainmentPage.isHollywoodPageTitleVisible());
    }


    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSectionVisible() {
        HollywoodEntertainmentPage hollywoodEntertainmentPage = getHollywoodPage();
        Assert.assertTrue(hollywoodEntertainmentPage.isHeroSectionVisible());
        Assert.assertTrue(hollywoodEntertainmentPage.isHeroTitleVisible());
        Assert.assertTrue(hollywoodEntertainmentPage.isHeroReadNowButtonVisible());
    }

    @Test
    public void verifyHeroReadNowNavigation() {
        HollywoodEntertainmentPage hollywoodEntertainmentPage = getHollywoodPage();
        hollywoodEntertainmentPage.clickHeroReadNow();
        Assert.assertTrue(hollywoodEntertainmentPage.isArticlePageOpened());
    }

    /* ================= ARTICLE LIST SECTION ================= */

    @Test
    public void verifyArticleListVisible() {
        HollywoodEntertainmentPage hollywoodEntertainmentPage = getHollywoodPage();
        Assert.assertTrue(hollywoodEntertainmentPage.isArticleListVisible());
        Assert.assertTrue(hollywoodEntertainmentPage.hasAtLeastOneArticle());
    }

    @Test
    public void verifyArticleNavigation() {
        HollywoodEntertainmentPage hollywoodEntertainmentPage = getHollywoodPage();
        hollywoodEntertainmentPage.clickFirstArticle();
        Assert.assertTrue(hollywoodEntertainmentPage.isArticlePageOpened());
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreButtonVisible() {
        HollywoodEntertainmentPage hollywoodEntertainmentPage = getHollywoodPage();
        Assert.assertTrue(hollywoodEntertainmentPage.isLoadMoreButtonVisible());
    }

    @Test
    public void verifyLoadMoreFunctionality() {
        HollywoodEntertainmentPage hollywoodEntertainmentPage = getHollywoodPage();
        int beforeCount = hollywoodEntertainmentPage.getArticleCount();
        hollywoodEntertainmentPage.clickLoadMore();
        int afterCount = hollywoodEntertainmentPage.getArticleCount();
        Assert.assertTrue(afterCount > beforeCount);
    }
}