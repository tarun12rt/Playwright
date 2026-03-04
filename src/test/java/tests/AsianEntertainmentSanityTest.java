package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.AsianEntertainmentPage;

public class AsianEntertainmentSanityTest extends BaseTestParallelExecution {

    private AsianEntertainmentPage getAsianPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        AsianEntertainmentPage page = new AsianEntertainmentPage(page());
        page.open();
        return page;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifyAsianPageLoads() {
        AsianEntertainmentPage page = getAsianPage();
        Assert.assertTrue(page.isPageVisible(), "Asian page heading not visible");
    }

    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSection() {
        AsianEntertainmentPage page = getAsianPage();
        Assert.assertTrue(page.isHeroVisible(), "Hero section not visible");
        Assert.assertTrue(page.isHeroTitleVisible(), "Hero title not visible");
        Assert.assertTrue(page.isHeroReadNowButtonVisible(), "Hero Read Now button not visible");
    }

    @Test
    public void verifyHeroNavigation() {
        AsianEntertainmentPage page = getAsianPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticleOpened(), "Hero article did not open");
    }

    /* ================= ARTICLE SECTION ================= */

    @Test
    public void verifyArticleSection() {
        AsianEntertainmentPage page = getAsianPage();
        Assert.assertTrue(page.isArticleSectionVisible(), "Article section not visible");
        Assert.assertTrue(page.hasAtLeastOneArticle(), "No articles found");
    }

    @Test
    public void verifyArticleNavigation() {
        AsianEntertainmentPage page = getAsianPage();
        page.clickFirstArticle();
        Assert.assertTrue(page.isArticleOpened(), "Article did not open");
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreWorks() {
        AsianEntertainmentPage page = getAsianPage();
        int before = page.getArticleCount();
        page.clickLoadMore();
        int after = page.getArticleCount();
        Assert.assertTrue(after > before, "Load More did not increase article count");
    }
}