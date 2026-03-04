package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.CelebFoodiesPage;

public class CelebFoodiesSanityTest extends BaseTestParallelExecution {

    private CelebFoodiesPage getPage() {
        CelebFoodiesPage page = new CelebFoodiesPage(page());
        page.open();
        return page;
    }

    @Test
    public void verifyPageLoads() {
        Assert.assertTrue(getPage().isPageVisible());
    }

    @Test
    public void verifyHeroAndArticles() {
        CelebFoodiesPage page = getPage();
        Assert.assertTrue(page.isHeroVisible());
        Assert.assertTrue(page.hasAtLeastOneArticle());
    }
}