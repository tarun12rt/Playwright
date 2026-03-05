package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.EatAndDrink_HawkerFoodPage;

public class EatAndDrink_HawkerFoodSanityTest extends BaseTestParallelExecution {

    private EatAndDrink_HawkerFoodPage getPage() {
        EatAndDrink_HawkerFoodPage page = new EatAndDrink_HawkerFoodPage(page());
        page.open();
        return page;
    }

    @Test
    public void verifyPageLoads() {
        Assert.assertTrue(getPage().isPageVisible());
    }

    @Test
    public void verifyHeroAndArticles() {
        EatAndDrink_HawkerFoodPage page = getPage();
        Assert.assertTrue(page.isHeroVisible());
        Assert.assertTrue(page.hasAtLeastOneArticle());
    }
}