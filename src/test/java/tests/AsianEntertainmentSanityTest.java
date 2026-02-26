package tests;

import base.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AsianEntertainmentPage;

public class AsianEntertainmentSanityTest extends BaseTestParallelExecution {

    private AsianEntertainmentPage getAsianPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        AsianEntertainmentPage asianEntertainmentPage = new AsianEntertainmentPage(page());
        asianEntertainmentPage.open();
        return asianEntertainmentPage;
    }

    @Test
    public void verifyAsianPageLoads() {
        AsianEntertainmentPage asianEntertainmentPage = getAsianPage();
        Assert.assertTrue(asianEntertainmentPage.isAsianPageVisible());
    }

    @Test
    public void verifyHeroSection() {
        AsianEntertainmentPage asianEntertainmentPage = getAsianPage();
        Assert.assertTrue(asianEntertainmentPage.isHeroSectionVisible());
        Assert.assertTrue(asianEntertainmentPage.isHeroTitleVisible());
        Assert.assertTrue(asianEntertainmentPage.isHeroReadNowButtonVisible());
    }

    @Test
    public void verifyHeroNavigation() {
        AsianEntertainmentPage asianEntertainmentPage = getAsianPage();
        asianEntertainmentPage.clickHeroReadNow();
        Assert.assertTrue(asianEntertainmentPage.isArticlePageOpened());
    }

    @Test
    public void verifyArticleSection() {
        AsianEntertainmentPage asianEntertainmentPage = getAsianPage();
        Assert.assertTrue(asianEntertainmentPage.isArticleSectionVisible());
        Assert.assertTrue(asianEntertainmentPage.hasAtLeastOneArticle());
    }

    @Test
    public void verifyArticleNavigation() {
        AsianEntertainmentPage asianEntertainmentPage = getAsianPage();
        asianEntertainmentPage.clickFirstArticle();
        Assert.assertTrue(asianEntertainmentPage.isArticlePageOpened());
    }

    @Test
    public void verifyLoadMoreWorks() {
        AsianEntertainmentPage asianEntertainmentPage = getAsianPage();
        int before = asianEntertainmentPage.getArticleCount();
        asianEntertainmentPage.clickLoadMore();
        int after = asianEntertainmentPage.getArticleCount();
        Assert.assertTrue(after > before);
    }
}