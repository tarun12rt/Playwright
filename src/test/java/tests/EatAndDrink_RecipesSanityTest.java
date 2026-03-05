package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.EatAndDrink_RecipesPage;

public class EatAndDrink_RecipesSanityTest extends BaseTestParallelExecution {

    private EatAndDrink_RecipesPage getPage() {
        EatAndDrink_RecipesPage page = new EatAndDrink_RecipesPage(page());
        page.open();
        return page;
    }

    @Test
    public void verifyPageLoads() {
        Assert.assertTrue(getPage().isPageVisible());
    }

    @Test
    public void verifyHeroAndArticles() {
        EatAndDrink_RecipesPage page = getPage();
        Assert.assertTrue(page.isHeroVisible());
        Assert.assertTrue(page.hasAtLeastOneArticle());
    }
}