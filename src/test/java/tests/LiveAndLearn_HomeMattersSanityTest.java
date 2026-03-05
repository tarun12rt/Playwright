package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.LiveAndLearn_HomeMattersPage;

public class LiveAndLearn_HomeMattersSanityTest extends BaseTestParallelExecution {

    private LiveAndLearn_HomeMattersPage getHomeMattersPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        LiveAndLearn_HomeMattersPage page = new LiveAndLearn_HomeMattersPage(page());
        page.open();
        return page;
    }

    @Test
    public void verifyHomeMattersPageLoads() {
        LiveAndLearn_HomeMattersPage page = getHomeMattersPage();
        Assert.assertTrue(page.isPageVisible());
    }

    @Test
    public void verifyHeroSection() {
        LiveAndLearn_HomeMattersPage page = getHomeMattersPage();
        Assert.assertTrue(page.isHeroVisible());
        Assert.assertTrue(page.isHeroTitleVisible());
        Assert.assertTrue(page.isHeroReadNowButtonVisible());
    }

    @Test
    public void verifyHeroNavigation() {
        LiveAndLearn_HomeMattersPage page = getHomeMattersPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticleOpened());
    }

    @Test
    public void verifyArticleSection() {
        LiveAndLearn_HomeMattersPage page = getHomeMattersPage();
        Assert.assertTrue(page.isArticleSectionVisible());
        Assert.assertTrue(page.hasAtLeastOneArticle());
    }

    @Test
    public void verifyArticleNavigation() {
        LiveAndLearn_HomeMattersPage page = getHomeMattersPage();
        page.clickFirstArticle();
        Assert.assertTrue(page.isArticleOpened());
    }

    @Test
    public void verifyLoadMoreWorks() {
        LiveAndLearn_HomeMattersPage page = getHomeMattersPage();
        int before = page.getArticleCount();
        page.clickLoadMore();
        int after = page.getArticleCount();
        Assert.assertTrue(after > before);
    }
}