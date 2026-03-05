package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.EatAndDrink_KopitiamPage;

public class EatAndDrink_KopitiamSanityTest extends BaseTestParallelExecution {

    private EatAndDrink_KopitiamPage getPage() {
        EatAndDrink_KopitiamPage page = new EatAndDrink_KopitiamPage(page());
        page.open();
        return page;
    }

    @Test
    public void verifyPageLoads() {
        Assert.assertTrue(getPage().isPageVisible());
    }

    @Test
    public void verifyHeroAndArticles() {
        EatAndDrink_KopitiamPage page = getPage();
        Assert.assertTrue(page.isHeroVisible());
        Assert.assertTrue(page.hasAtLeastOneArticle());
    }
}