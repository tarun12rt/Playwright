package tests;

import base.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AsianEntertainmentPage;

public class AsianEntertainmentSanityTest extends BaseTestParallelExecution {

    private AsianEntertainmentPage getAsianPage() {
        AsianEntertainmentPage page = new AsianEntertainmentPage(page());
        page.open();
        return page;
    }

    @Test
    public void verifyAsianPageLoads() {
        AsianEntertainmentPage page = getAsianPage();
        Assert.assertTrue(page.isAsianPageVisible());
    }

    @Test
    public void verifyHeroSection() {
        AsianEntertainmentPage page = getAsianPage();
        Assert.assertTrue(page.isHeroSectionVisible());
        Assert.assertTrue(page.isHeroTitleVisible());
        Assert.assertTrue(page.isHeroReadNowButtonVisible());
    }

    @Test
    public void verifyHeroNavigation() {
        AsianEntertainmentPage page = getAsianPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticlePageOpened());
    }

    @Test
    public void verifyArticleSection() {
        AsianEntertainmentPage page = getAsianPage();
        Assert.assertTrue(page.isArticleSectionVisible());
        Assert.assertTrue(page.hasAtLeastOneArticle());
    }

    @Test
    public void verifyArticleNavigation() {
        AsianEntertainmentPage page = getAsianPage();
        page.clickFirstArticle();
        Assert.assertTrue(page.isArticlePageOpened());
    }

    @Test
    public void verifyLoadMoreWorks() {
        AsianEntertainmentPage page = getAsianPage();
        int before = page.getArticleCount();
        page.clickLoadMore();
        int after = page.getArticleCount();
        Assert.assertTrue(after > before);
    }
}