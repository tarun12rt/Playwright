package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.SeeAndDo_ThingsToDoPage;

public class SeeAndDo_ThingsToDoSanityTest extends BaseTestParallelExecution {

    private SeeAndDo_ThingsToDoPage getThingsToDoPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        SeeAndDo_ThingsToDoPage page = new SeeAndDo_ThingsToDoPage(page());
        page.open();
        return page;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifyThingsToDoPageLoads() {
        SeeAndDo_ThingsToDoPage page = getThingsToDoPage();
        Assert.assertTrue(page.isPageVisible(), "Things To Do page heading not visible");
    }

    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSection() {
        SeeAndDo_ThingsToDoPage page = getThingsToDoPage();
        Assert.assertTrue(page.isHeroVisible(), "Hero section not visible");
        Assert.assertTrue(page.isHeroTitleVisible(), "Hero title not visible");
        Assert.assertTrue(page.isHeroReadNowButtonVisible(), "Hero Read Now button not visible");
    }

    @Test
    public void verifyHeroNavigation() {
        SeeAndDo_ThingsToDoPage page = getThingsToDoPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticleOpened(), "Hero article did not open");
    }

    /* ================= ARTICLE SECTION ================= */

    @Test
    public void verifyArticleSection() {
        SeeAndDo_ThingsToDoPage page = getThingsToDoPage();
        Assert.assertTrue(page.isArticleSectionVisible(), "Article section not visible");
        Assert.assertTrue(page.hasAtLeastOneArticle(), "No articles found");
    }

    @Test
    public void verifyArticleNavigation() {
        SeeAndDo_ThingsToDoPage page = getThingsToDoPage();
        page.clickFirstArticle();
        Assert.assertTrue(page.isArticleOpened(), "Article did not open");
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreWorks() {
        SeeAndDo_ThingsToDoPage page = getThingsToDoPage();
        int before = page.getArticleCount();
        page.clickLoadMore();
        int after = page.getArticleCount();
        Assert.assertTrue(after > before, "Load More did not increase article count");
    }
}