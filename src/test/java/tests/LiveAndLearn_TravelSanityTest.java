package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.LiveAndLearn_TravelPage;

public class LiveAndLearn_TravelSanityTest extends BaseTestParallelExecution {

    private LiveAndLearn_TravelPage getTravelPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        LiveAndLearn_TravelPage page = new LiveAndLearn_TravelPage(page());
        page.open();
        return page;
    }

    @Test
    public void verifyTravelPageLoads() {
        LiveAndLearn_TravelPage page = getTravelPage();
        Assert.assertTrue(page.isPageVisible());
    }

    @Test
    public void verifyHeroSection() {
        LiveAndLearn_TravelPage page = getTravelPage();
        Assert.assertTrue(page.isHeroVisible());
        Assert.assertTrue(page.isHeroTitleVisible());
        Assert.assertTrue(page.isHeroReadNowButtonVisible());
    }

    @Test
    public void verifyHeroNavigation() {
        LiveAndLearn_TravelPage page = getTravelPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticleOpened());
    }

    @Test
    public void verifyArticleSection() {
        LiveAndLearn_TravelPage page = getTravelPage();
        Assert.assertTrue(page.isArticleSectionVisible());
        Assert.assertTrue(page.hasAtLeastOneArticle());
    }

    @Test
    public void verifyArticleNavigation() {
        LiveAndLearn_TravelPage page = getTravelPage();
        page.clickFirstArticle();
        Assert.assertTrue(page.isArticleOpened());
    }

    @Test
    public void verifyLoadMoreWorks() {
        LiveAndLearn_TravelPage page = getTravelPage();
        int before = page.getArticleCount();
        page.clickLoadMore();
        int after = page.getArticleCount();
        Assert.assertTrue(after > before);
    }
}