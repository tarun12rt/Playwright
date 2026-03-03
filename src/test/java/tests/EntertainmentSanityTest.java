package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.categories.EntertainmentPage;

public class EntertainmentSanityTest extends BaseTestParallelExecution {

    private EntertainmentPage getEntertainmentPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        EntertainmentPage page = new EntertainmentPage(page());
        page.open();
        return page;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifyEntertainmentPageLoads() {
        EntertainmentPage page = getEntertainmentPage();
        Assert.assertTrue(page.isPageVisible(), "Page heading is not visible");
    }

    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSectionVisible() {
        EntertainmentPage page = getEntertainmentPage();
        Assert.assertTrue(page.isHeroVisible(), "Hero section not visible");
        Assert.assertTrue(page.isHeroTitleVisible(), "Hero title not visible");
        Assert.assertTrue(page.isHeroReadNowButtonVisible(), "Hero Read Now button not visible");
    }

    @Test
    public void verifyHeroReadNowNavigation() {
        EntertainmentPage page = getEntertainmentPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticleOpened(), "Hero article did not open");
    }

    /* ================= LATEST SECTION ================= */

    @Test
    public void verifyLatestSectionVisible() {
        EntertainmentPage page = getEntertainmentPage();
        Assert.assertTrue(page.isLatestSectionVisible(), "Latest section not visible");
        Assert.assertTrue(page.hasAtLeastOneLatestArticle(), "No latest articles found");
    }

    @Test
    public void verifyLatestArticleNavigation() {
        EntertainmentPage page = getEntertainmentPage();
        page.clickFirstLatestArticle();
        Assert.assertTrue(page.isArticleOpened(), "Latest article did not open");
    }

    /* ================= POPULAR SECTION ================= */

    @Test
    public void verifyPopularSectionVisible() {
        EntertainmentPage page = getEntertainmentPage();
        Assert.assertTrue(page.isPopularSectionVisible(), "Popular section not visible");
        Assert.assertTrue(page.hasPopularArticles(), "No popular articles found");
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreWorks() {
        EntertainmentPage page = getEntertainmentPage();
        int before = page.getPopularArticleCount();
        page.clickLoadMore();
        int after = page.getPopularArticleCount();
        Assert.assertTrue(after > before, "Load More did not increase article count");
    }
}