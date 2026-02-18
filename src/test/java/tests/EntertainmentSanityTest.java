package tests;

import base.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EntertainmentPage;

public class EntertainmentSanityTest extends BaseTestParallelExecution {

    private EntertainmentPage getEntertainmentPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        EntertainmentPage entertainmentPage = new EntertainmentPage(page());
        entertainmentPage.open();
        return entertainmentPage;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifyEntertainmentPageLoads() {
        EntertainmentPage entertainmentPage = getEntertainmentPage();
        Assert.assertTrue(entertainmentPage.isEntertainmentPageVisible());
    }

    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSectionVisible() {
        EntertainmentPage entertainmentPage = getEntertainmentPage();
        Assert.assertTrue(entertainmentPage.isHeroSectionVisible());
        Assert.assertTrue(entertainmentPage.isHeroTitleVisible());
        Assert.assertTrue(entertainmentPage.isHeroReadNowButtonVisible());
    }

    @Test
    public void verifyHeroReadNowNavigation() {
        EntertainmentPage entertainmentPage = getEntertainmentPage();
        entertainmentPage.clickHeroReadNow();
        Assert.assertTrue(entertainmentPage.isArticlePageOpened());
    }

    /* ================= LATEST SECTION ================= */

    @Test
    public void verifyLatestSectionVisible() {
        EntertainmentPage entertainmentPage = getEntertainmentPage();
        Assert.assertTrue(entertainmentPage.isLatestSectionVisible());
        Assert.assertTrue(entertainmentPage.hasAtLeastOneLatestArticle());
    }

    @Test
    public void verifyLatestArticleNavigation() {
        EntertainmentPage entertainmentPage = getEntertainmentPage();
        entertainmentPage.clickFirstLatestArticle();
        Assert.assertTrue(entertainmentPage.isArticlePageOpened());
    }

    /* ================= POPULAR SECTION ================= */

    @Test
    public void verifyPopularSectionVisible() {
        EntertainmentPage entertainmentPage = getEntertainmentPage();
        Assert.assertTrue(entertainmentPage.isPopularSectionVisible());
        Assert.assertTrue(entertainmentPage.hasAtLeastOnePopularArticle());
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreWorks() {
        EntertainmentPage entertainmentPage = getEntertainmentPage();

        int beforeCount = entertainmentPage.getPopularArticleCount();
        entertainmentPage.clickLoadMore();
        int afterCount = entertainmentPage.getPopularArticleCount();

        Assert.assertTrue(afterCount > beforeCount);
    }
}
