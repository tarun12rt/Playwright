package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.categories.SeeAndDoPage;

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
        Assert.assertTrue(page.isPageVisible(), "Page heading not visible");
    }

    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSectionVisible() {
        SeeAndDoPage page = getSeeAndDoPage();
        Assert.assertTrue(page.isHeroVisible(), "Hero section not visible");
        Assert.assertTrue(page.isHeroTitleVisible(), "Hero title not visible");
        Assert.assertTrue(page.isHeroReadNowButtonVisible(), "Hero Read Now button not visible");
    }

    @Test
    public void verifyHeroReadNowNavigation() {
        SeeAndDoPage page = getSeeAndDoPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticleOpened(), "Hero article did not open");
    }

    /* ================= LATEST SECTION ================= */

    @Test
    public void verifyLatestSectionVisible() {
        SeeAndDoPage page = getSeeAndDoPage();
        Assert.assertTrue(page.isLatestSectionVisible(), "Latest section not visible");
        Assert.assertTrue(page.hasAtLeastOneLatestArticle(), "No latest articles found");
    }

    @Test
    public void verifyLatestArticleNavigation() {
        SeeAndDoPage page = getSeeAndDoPage();
        page.clickFirstLatestArticle();
        Assert.assertTrue(page.isArticleOpened(), "Latest article did not open");
    }

    /* ================= POPULAR SECTION ================= */

    @Test
    public void verifyPopularSectionVisible() {
        SeeAndDoPage page = getSeeAndDoPage();
        Assert.assertTrue(page.isPopularSectionVisible(), "Popular section not visible");
        Assert.assertTrue(page.hasPopularArticles(), "No popular articles found");
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreWorks() {
        SeeAndDoPage page = getSeeAndDoPage();
        int before = page.getPopularArticleCount();
        page.clickLoadMore();
        int after = page.getPopularArticleCount();
        Assert.assertTrue(after > before, "Load More did not increase article count");
    }
}