package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.Entertainment_AsianPage;

public class Entertainment_AsianSanityTest extends BaseTestParallelExecution {

    private Entertainment_AsianPage getAsianPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        Entertainment_AsianPage page = new Entertainment_AsianPage(page());
        page.open();
        return page;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifyAsianPageLoads() {
        Entertainment_AsianPage page = getAsianPage();
        Assert.assertTrue(page.isPageVisible(), "Asian page heading not visible");
    }

    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSection() {
        Entertainment_AsianPage page = getAsianPage();
        Assert.assertTrue(page.isHeroVisible(), "Hero section not visible");
        Assert.assertTrue(page.isHeroTitleVisible(), "Hero title not visible");
        Assert.assertTrue(page.isHeroReadNowButtonVisible(), "Hero Read Now button not visible");
    }

    @Test
    public void verifyHeroNavigation() {
        Entertainment_AsianPage page = getAsianPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticleOpened(), "Hero article did not open");
    }

    /* ================= ARTICLE SECTION ================= */

    @Test
    public void verifyArticleSection() {
        Entertainment_AsianPage page = getAsianPage();
        Assert.assertTrue(page.isArticleSectionVisible(), "Article section not visible");
        Assert.assertTrue(page.hasAtLeastOneArticle(), "No articles found");
    }

    @Test
    public void verifyArticleNavigation() {
        Entertainment_AsianPage page = getAsianPage();
        page.clickFirstArticle();
        Assert.assertTrue(page.isArticleOpened(), "Article did not open");
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreWorks() {
        Entertainment_AsianPage page = getAsianPage();
        int before = page.getArticleCount();
        page.clickLoadMore();
        int after = page.getArticleCount();
        Assert.assertTrue(after > before, "Load More did not increase article count");
    }
}