package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.LiveAndLearn_LifestylePage;

public class LiveAndLearn_LifestyleSanityTest extends BaseTestParallelExecution {

    private LiveAndLearn_LifestylePage getLifestylePage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        LiveAndLearn_LifestylePage page = new LiveAndLearn_LifestylePage(page());
        page.open();
        return page;
    }

    @Test
    public void verifyLifestylePageLoads() {
        LiveAndLearn_LifestylePage page = getLifestylePage();
        Assert.assertTrue(page.isPageVisible(), "Lifestyle page heading not visible");
    }

    @Test
    public void verifyHeroSection() {
        LiveAndLearn_LifestylePage page = getLifestylePage();
        Assert.assertTrue(page.isHeroVisible());
        Assert.assertTrue(page.isHeroTitleVisible());
        Assert.assertTrue(page.isHeroReadNowButtonVisible());
    }

    @Test
    public void verifyHeroNavigation() {
        LiveAndLearn_LifestylePage page = getLifestylePage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticleOpened());
    }

    @Test
    public void verifyArticleSection() {
        LiveAndLearn_LifestylePage page = getLifestylePage();
        Assert.assertTrue(page.isArticleSectionVisible());
        Assert.assertTrue(page.hasAtLeastOneArticle());
    }

    @Test
    public void verifyArticleNavigation() {
        LiveAndLearn_LifestylePage page = getLifestylePage();
        page.clickFirstArticle();
        Assert.assertTrue(page.isArticleOpened());
    }

    @Test
    public void verifyLoadMoreWorks() {
        LiveAndLearn_LifestylePage page = getLifestylePage();
        int before = page.getArticleCount();
        page.clickLoadMore();
        int after = page.getArticleCount();
        Assert.assertTrue(after > before);
    }
}