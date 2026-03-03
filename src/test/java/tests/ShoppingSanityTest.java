package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.categories.ShoppingPage;

public class ShoppingSanityTest extends BaseTestParallelExecution {

    private ShoppingPage getShoppingPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        ShoppingPage page = new ShoppingPage(page());
        page.open();
        return page;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifyShoppingPageLoads() {
        ShoppingPage page = getShoppingPage();
        Assert.assertTrue(page.isPageVisible(), "Shopping page heading not visible");
    }

    /* ================= HERO ================= */

    @Test
    public void verifyHeroSectionVisible() {
        ShoppingPage page = getShoppingPage();
        Assert.assertTrue(page.isHeroVisible(), "Hero section not visible");
    }

    @Test
    public void verifyHeroNavigation() {
        ShoppingPage page = getShoppingPage();
        page.clickHeroArticle();
        Assert.assertTrue(page.isArticleOpened(), "Hero article did not open");
    }

    /* ================= 8days Tested ================= */

    @Test
    public void verifyTestedSectionVisible() {
        ShoppingPage page = getShoppingPage();
        Assert.assertTrue(page.isTestedSectionVisible(), "8days Tested section not visible");
        Assert.assertTrue(page.hasTestedArticles(), "No Tested articles found");
    }

    /* ================= Latest ================= */

    @Test
    public void verifyLatestSectionVisible() {
        ShoppingPage page = getShoppingPage();
        Assert.assertTrue(page.isLatestSectionVisible(), "Latest section not visible");
        Assert.assertTrue(page.hasLatestArticles(), "No Latest articles found");
    }

    /* ================= Deals ================= */

    @Test
    public void verifyDealsSectionVisible() {
        ShoppingPage page = getShoppingPage();
        Assert.assertTrue(page.isDealsSectionVisible(), "Deals section not visible");
        Assert.assertTrue(page.hasDealsArticles(), "No Deals articles found");
    }

    /* ================= Check These Out ================= */

    @Test
    public void verifyCheckTheseOutSectionVisible() {
        ShoppingPage page = getShoppingPage();
        Assert.assertTrue(page.isCheckTheseOutVisible(), "Check These Out section not visible");
        Assert.assertTrue(page.hasCheckTheseOutArticles(), "No Check These Out articles found");
    }

    /* ================= Load More ================= */

    @Test
    public void verifyLoadMoreWorks() {
        ShoppingPage page = getShoppingPage();
        int before = page.getLatestArticleCount();
        page.clickLoadMore();
        int after = page.getLatestArticleCount();
        Assert.assertTrue(after > before, "Load More did not increase article count");
    }
}