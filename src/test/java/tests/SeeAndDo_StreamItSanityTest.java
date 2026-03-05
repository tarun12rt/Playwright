package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.SeeAndDo_StreamItPage;

public class SeeAndDo_StreamItSanityTest extends BaseTestParallelExecution {

    private SeeAndDo_StreamItPage getStreamItPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        SeeAndDo_StreamItPage page = new SeeAndDo_StreamItPage(page());
        page.open();
        return page;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifyStreamItPageLoads() {
        SeeAndDo_StreamItPage page = getStreamItPage();
        Assert.assertTrue(page.isPageVisible(), "Stream It page heading not visible");
    }

    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSection() {
        SeeAndDo_StreamItPage page = getStreamItPage();
        Assert.assertTrue(page.isHeroVisible(), "Hero section not visible");
        Assert.assertTrue(page.isHeroTitleVisible(), "Hero title not visible");
        Assert.assertTrue(page.isHeroReadNowButtonVisible(), "Hero Read Now button not visible");
    }

    @Test
    public void verifyHeroNavigation() {
        SeeAndDo_StreamItPage page = getStreamItPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticleOpened(), "Hero article did not open");
    }

    /* ================= ARTICLE SECTION ================= */

    @Test
    public void verifyArticleSection() {
        SeeAndDo_StreamItPage page = getStreamItPage();
        Assert.assertTrue(page.isArticleSectionVisible(), "Article section not visible");
        Assert.assertTrue(page.hasAtLeastOneArticle(), "No articles found");
    }

    @Test
    public void verifyArticleNavigation() {
        SeeAndDo_StreamItPage page = getStreamItPage();
        page.clickFirstArticle();
        Assert.assertTrue(page.isArticleOpened(), "Article did not open");
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreWorks() {
        SeeAndDo_StreamItPage page = getStreamItPage();
        int before = page.getArticleCount();
        page.clickLoadMore();
        int after = page.getArticleCount();
        Assert.assertTrue(after > before, "Load More did not increase article count");
    }
}