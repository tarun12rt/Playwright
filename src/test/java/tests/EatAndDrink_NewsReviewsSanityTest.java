package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.EatAndDrink_NewsReviewsPage;

public class EatAndDrink_NewsReviewsSanityTest extends BaseTestParallelExecution {

    private EatAndDrink_NewsReviewsPage getPage() {
        EatAndDrink_NewsReviewsPage page = new EatAndDrink_NewsReviewsPage(page());
        page.open();
        return page;
    }

    @Test
    public void verifyPageLoads() {
        Assert.assertTrue(getPage().isPageVisible(), "News & Reviews page not visible");
    }

    @Test
    public void verifyHeroSection() {
        EatAndDrink_NewsReviewsPage page = getPage();
        Assert.assertTrue(page.isHeroVisible());
        Assert.assertTrue(page.isHeroTitleVisible());
        Assert.assertTrue(page.isHeroReadNowButtonVisible());
    }

    @Test
    public void verifyArticleSection() {
        EatAndDrink_NewsReviewsPage page = getPage();
        Assert.assertTrue(page.isArticleSectionVisible());
        Assert.assertTrue(page.hasAtLeastOneArticle());
    }

    @Test
    public void verifyLoadMore() {
        EatAndDrink_NewsReviewsPage page = getPage();
        int before = page.getArticleCount();
        page.clickLoadMore();
        int after = page.getArticleCount();
        Assert.assertTrue(after > before);
    }
}