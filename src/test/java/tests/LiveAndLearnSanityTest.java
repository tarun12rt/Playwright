package tests;

import base.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LiveAndLearnPage;

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
        Assert.assertTrue(page.isLiveAndLearnPageVisible());
    }

    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSectionVisible() {
        LiveAndLearnPage page = getLiveAndLearnPage();
        Assert.assertTrue(page.isHeroSectionVisible());
        Assert.assertTrue(page.isHeroTitleVisible());
        Assert.assertTrue(page.isHeroReadNowButtonVisible());
    }

    @Test
    public void verifyHeroReadNowNavigation() {
        LiveAndLearnPage page = getLiveAndLearnPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticlePageOpened());
    }

    /* ================= LATEST SECTION ================= */

    @Test
    public void verifyLatestSectionVisible() {
        LiveAndLearnPage page = getLiveAndLearnPage();
        Assert.assertTrue(page.isLatestSectionVisible());
        Assert.assertTrue(page.hasAtLeastOneLatestArticle());
    }

    @Test
    public void verifyLatestArticleNavigation() {
        LiveAndLearnPage page = getLiveAndLearnPage();
        page.clickFirstLatestArticle();
        Assert.assertTrue(page.isArticlePageOpened());
    }

    /* ================= POPULAR SECTION ================= */

    @Test
    public void verifyPopularSectionVisible() {
        LiveAndLearnPage page = getLiveAndLearnPage();
        Assert.assertTrue(page.isPopularSectionVisible());
        Assert.assertTrue(page.hasAtLeastOnePopularArticle());
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreWorks() {
        LiveAndLearnPage page = getLiveAndLearnPage();
        int before = page.getPopularArticleCount();
        page.clickLoadMore();
        int after = page.getPopularArticleCount();
        Assert.assertTrue(after > before);
    }
}