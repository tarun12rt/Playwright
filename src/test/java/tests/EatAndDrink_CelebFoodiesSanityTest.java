package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.EatAndDrink_CelebFoodiesPage;

public class EatAndDrink_CelebFoodiesSanityTest extends BaseTestParallelExecution {

    private EatAndDrink_CelebFoodiesPage getPage() {
        EatAndDrink_CelebFoodiesPage page = new EatAndDrink_CelebFoodiesPage(page());
        page.open();
        return page;
    }

    @Test
    public void verifyPageLoads() {
        Assert.assertTrue(getPage().isPageVisible());
    }

    @Test
    public void verifyHeroAndArticles() {
        EatAndDrink_CelebFoodiesPage page = getPage();
        Assert.assertTrue(page.isHeroVisible());
        Assert.assertTrue(page.hasAtLeastOneArticle());
    }
}