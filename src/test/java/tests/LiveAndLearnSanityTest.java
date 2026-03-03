package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.categories.LiveAndLearnPage;

public class LiveAndLearnSanityTest extends BaseTestParallelExecution {

    private LiveAndLearnPage getLiveAndLearnPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        LiveAndLearnPage page = new LiveAndLearnPage(page());
        page.open();
        return page;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifyLiveAndLearnPageLoads() {
        LiveAndLearnPage page = getLiveAndLearnPage();
        Assert.assertTrue(page.isPageVisible(), "Page heading not visible");
    }

    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSectionVisible() {
        LiveAndLearnPage page = getLiveAndLearnPage();
        Assert.assertTrue(page.isHeroVisible(), "Hero section not visible");
        Assert.assertTrue(page.isHeroTitleVisible(), "Hero title not visible");
        Assert.assertTrue(page.isHeroReadNowButtonVisible(), "Hero Read Now button not visible");
    }

    @Test
    public void verifyHeroReadNowNavigation() {
        LiveAndLearnPage page = getLiveAndLearnPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticleOpened(), "Hero article did not open");
    }

    /* ================= LATEST SECTION ================= */

    @Test
    public void verifyLatestSectionVisible() {
        LiveAndLearnPage page = getLiveAndLearnPage();
        Assert.assertTrue(page.isLatestSectionVisible(), "Latest section not visible");
        Assert.assertTrue(page.hasAtLeastOneLatestArticle(), "No latest articles found");
    }

    @Test
    public void verifyLatestArticleNavigation() {
        LiveAndLearnPage page = getLiveAndLearnPage();
        page.clickFirstLatestArticle();
        Assert.assertTrue(page.isArticleOpened(), "Latest article did not open");
    }

    /* ================= POPULAR SECTION ================= */

    @Test
    public void verifyPopularSectionVisible() {
        LiveAndLearnPage page = getLiveAndLearnPage();
        Assert.assertTrue(page.isPopularSectionVisible(), "Popular section not visible");
        Assert.assertTrue(page.hasPopularArticles(), "No popular articles found");
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreWorks() {
        LiveAndLearnPage page = getLiveAndLearnPage();
        int before = page.getPopularArticleCount();
        page.clickLoadMore();
        int after = page.getPopularArticleCount();
        Assert.assertTrue(after > before, "Load More did not increase article count");
    }
}