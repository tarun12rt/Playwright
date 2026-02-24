package tests;

import base.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LocalEntertainmentPage;

public class LocalEntertainmentSanityTest extends BaseTestParallelExecution {

    private LocalEntertainmentPage getLocalPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        LocalEntertainmentPage localPage = new LocalEntertainmentPage(page());
        localPage.open();
        return localPage;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifyLocalPageLoads() {
        LocalEntertainmentPage localPage = getLocalPage();
        Assert.assertTrue(localPage.isLocalPageTitleVisible());
    }


    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSectionVisible() {
        LocalEntertainmentPage localPage = getLocalPage();
        Assert.assertTrue(localPage.isHeroSectionVisible());
        Assert.assertTrue(localPage.isHeroTitleVisible());
        Assert.assertTrue(localPage.isHeroReadNowButtonVisible());
    }

    @Test
    public void verifyHeroReadNowNavigation() {
        LocalEntertainmentPage localPage = getLocalPage();
        localPage.clickHeroReadNow();
        Assert.assertTrue(localPage.isArticlePageOpened());
    }

    /* ================= ARTICLE LIST SECTION ================= */

    @Test
    public void verifyArticleListVisible() {
        LocalEntertainmentPage localPage = getLocalPage();
        Assert.assertTrue(localPage.isArticleListVisible());
        Assert.assertTrue(localPage.hasAtLeastOneArticle());
    }

    @Test
    public void verifyArticleNavigation() {
        LocalEntertainmentPage localPage = getLocalPage();
        localPage.clickFirstArticle();
        Assert.assertTrue(localPage.isArticlePageOpened());
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreButtonVisible() {
        LocalEntertainmentPage localPage = getLocalPage();
        Assert.assertTrue(localPage.isLoadMoreButtonVisible());
    }

    @Test
    public void verifyLoadMoreFunctionality() {
        LocalEntertainmentPage localPage = getLocalPage();
        int beforeCount = localPage.getArticleCount();
        localPage.clickLoadMore();
        int afterCount = localPage.getArticleCount();
        Assert.assertTrue(afterCount > beforeCount);
    }
}