package tests;

import base.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ShoppingPage;

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
        Assert.assertTrue(page.isShoppingPageVisible());
    }

    /* ================= HERO ================= */

    @Test
    public void verifyHeroSectionVisible() {
        ShoppingPage page = getShoppingPage();
        Assert.assertTrue(page.isHeroSectionVisible());
    }

    @Test
    public void verifyHeroNavigation() {
        ShoppingPage page = getShoppingPage();
        page.clickHeroArticle();
        Assert.assertTrue(page.isArticlePageOpened());
    }

    /* ================= 8days Tested ================= */

    @Test
    public void verifyTestedSectionVisible() {
        ShoppingPage page = getShoppingPage();
        Assert.assertTrue(page.isTestedSectionVisible());
        Assert.assertTrue(page.hasTestedArticles());
    }

    /* ================= Latest ================= */

    @Test
    public void verifyLatestSectionVisible() {
        ShoppingPage page = getShoppingPage();
        Assert.assertTrue(page.isLatestSectionVisible());
        Assert.assertTrue(page.hasLatestArticles());
    }

    /* ================= Deals ================= */

    @Test
    public void verifyDealsSectionVisible() {
        ShoppingPage page = getShoppingPage();
        Assert.assertTrue(page.isDealsSectionVisible());
        Assert.assertTrue(page.hasDealsArticles());
    }

    /* ================= Check These Out ================= */

    @Test
    public void verifyCheckTheseOutSectionVisible() {
        ShoppingPage page = getShoppingPage();
        Assert.assertTrue(page.isCheckTheseOutVisible());
        Assert.assertTrue(page.hasCheckTheseOutArticles());
    }

    /* ================= Load More ================= */

    @Test
    public void verifyLoadMoreWorks() {
        ShoppingPage page = getShoppingPage();
        page.clickLoadMore();
    }
}