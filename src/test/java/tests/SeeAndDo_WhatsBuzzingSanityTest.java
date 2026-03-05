package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.subcategories.SeeAndDo_WhatsBuzzingPage;

public class SeeAndDo_WhatsBuzzingSanityTest extends BaseTestParallelExecution {

    private SeeAndDo_WhatsBuzzingPage getWhatsBuzzingPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        SeeAndDo_WhatsBuzzingPage page = new SeeAndDo_WhatsBuzzingPage(page());
        page.open();
        return page;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifyWhatsBuzzingPageLoads() {
        SeeAndDo_WhatsBuzzingPage page = getWhatsBuzzingPage();
        Assert.assertTrue(page.isPageVisible(), "What's Buzzing page heading not visible");
    }

    /* ================= HERO SECTION ================= */

    @Test
    public void verifyHeroSection() {
        SeeAndDo_WhatsBuzzingPage page = getWhatsBuzzingPage();
        Assert.assertTrue(page.isHeroVisible(), "Hero section not visible");
        Assert.assertTrue(page.isHeroTitleVisible(), "Hero title not visible");
        Assert.assertTrue(page.isHeroReadNowButtonVisible(), "Hero Read Now button not visible");
    }

    @Test
    public void verifyHeroNavigation() {
        SeeAndDo_WhatsBuzzingPage page = getWhatsBuzzingPage();
        page.clickHeroReadNow();
        Assert.assertTrue(page.isArticleOpened(), "Hero article did not open");
    }

    /* ================= ARTICLE SECTION ================= */

    @Test
    public void verifyArticleSection() {
        SeeAndDo_WhatsBuzzingPage page = getWhatsBuzzingPage();
        Assert.assertTrue(page.isArticleSectionVisible(), "Article section not visible");
        Assert.assertTrue(page.hasAtLeastOneArticle(), "No articles found");
    }

    @Test
    public void verifyArticleNavigation() {
        SeeAndDo_WhatsBuzzingPage page = getWhatsBuzzingPage();
        page.clickFirstArticle();
        Assert.assertTrue(page.isArticleOpened(), "Article did not open");
    }

    /* ================= LOAD MORE ================= */

    @Test
    public void verifyLoadMoreWorks() {
        SeeAndDo_WhatsBuzzingPage page = getWhatsBuzzingPage();
        int before = page.getArticleCount();
        page.clickLoadMore();
        int after = page.getArticleCount();
        Assert.assertTrue(after > before, "Load More did not increase article count");
    }
}