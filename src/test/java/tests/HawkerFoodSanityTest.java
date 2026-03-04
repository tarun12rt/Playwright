package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.HawkerFoodPage;

public class HawkerFoodSanityTest extends BaseTestParallelExecution {

    private HawkerFoodPage getPage() {
        HawkerFoodPage page = new HawkerFoodPage(page());
        page.open();
        return page;
    }

    @Test
    public void verifyPageLoads() {
        Assert.assertTrue(getPage().isPageVisible());
    }

    @Test
    public void verifyHeroAndArticles() {
        HawkerFoodPage page = getPage();
        Assert.assertTrue(page.isHeroVisible());
        Assert.assertTrue(page.hasAtLeastOneArticle());
    }
}