package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.EatAndDrinkNewsReviewsPage;

public class EatAndDrinkNewsReviewsSanityTest extends BaseTestParallelExecution {

    private EatAndDrinkNewsReviewsPage getPage() {
        EatAndDrinkNewsReviewsPage page = new EatAndDrinkNewsReviewsPage(page());
        page.open();
        return page;
    }

    @Test
    public void verifyPageLoads() {
        Assert.assertTrue(getPage().isPageVisible(), "News & Reviews page not visible");
    }

    @Test
    public void verifyHeroSection() {
        EatAndDrinkNewsReviewsPage page = getPage();
        Assert.assertTrue(page.isHeroVisible());
        Assert.assertTrue(page.isHeroTitleVisible());
        Assert.assertTrue(page.isHeroReadNowButtonVisible());
    }

    @Test
    public void verifyArticleSection() {
        EatAndDrinkNewsReviewsPage page = getPage();
        Assert.assertTrue(page.isArticleSectionVisible());
        Assert.assertTrue(page.hasAtLeastOneArticle());
    }

    @Test
    public void verifyLoadMore() {
        EatAndDrinkNewsReviewsPage page = getPage();
        int before = page.getArticleCount();
        page.clickLoadMore();
        int after = page.getArticleCount();
        Assert.assertTrue(after > before);
    }
}