package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.RecipesPage;

public class RecipesSanityTest extends BaseTestParallelExecution {

    private RecipesPage getPage() {
        RecipesPage page = new RecipesPage(page());
        page.open();
        return page;
    }

    @Test
    public void verifyPageLoads() {
        Assert.assertTrue(getPage().isPageVisible());
    }

    @Test
    public void verifyHeroAndArticles() {
        RecipesPage page = getPage();
        Assert.assertTrue(page.isHeroVisible());
        Assert.assertTrue(page.hasAtLeastOneArticle());
    }
}