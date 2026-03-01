package tests;

import base.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SeeAndDoPage;

public class SeeAndDoSanityTest extends BaseTestParallelExecution {

    private SeeAndDoPage getSeeAndDoPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        SeeAndDoPage page = new SeeAndDoPage(page());
        page.open();
        return page;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifySeeAndDoPageLoads() {
        SeeAndDoPage page = getSeeAndDoPage();
        Assert.assertTrue(page.isSeeAndDoPageVisible());
    }

    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSectionVisible() {
        SeeAndDoPage page = getSeeAndDoPage();
        Assert.assertTrue(page.isHeroSectionVisible());
        Assert.assertTrue(page.isHeroTitleVisible());
        Assert.assertTrue(page.isHeroReadNowButtonVisible());
    }

    @Test
    public void verifyHeroReadNowNavigation() {
        SeeAndDoPage page = getSeeAndDoPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticlePageOpened());
    }

    /* ================= LATEST SECTION ================= */

    @Test
    public void verifyLatestSectionVisible() {
        SeeAndDoPage page = getSeeAndDoPage();
        Assert.assertTrue(page.isLatestSectionVisible());
        Assert.assertTrue(page.hasAtLeastOneLatestArticle());
    }

    @Test
    public void verifyLatestArticleNavigation() {
        SeeAndDoPage page = getSeeAndDoPage();
        page.clickFirstLatestArticle();
        Assert.assertTrue(page.isArticlePageOpened());
    }

    /* ================= POPULAR SECTION ================= */

    @Test
    public void verifyPopularSectionVisible() {
        SeeAndDoPage page = getSeeAndDoPage();
        Assert.assertTrue(page.isPopularSectionVisible());
        Assert.assertTrue(page.hasAtLeastOnePopularArticle());
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreWorks() {
        SeeAndDoPage page = getSeeAndDoPage();
        int before = page.getPopularArticleCount();
        page.clickLoadMore();
        int after = page.getPopularArticleCount();
        Assert.assertTrue(after > before);
    }
}