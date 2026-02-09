package tests;

import base.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTest extends BaseTestParallelExecution {

    private HomePage getHomePage(){
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        HomePage homepage = new HomePage(page());
        homepage.open();
        return homepage;
    }

    @Test
    public void verifyHomePageUI() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isHomepageVisible());
    }

    /* ================= READ THIS SECTION ================= */

    @Test
    public void verifyReadThisSectionIsVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isReadThisSectionVisible());
    }

    @Test
    public void verifyReadThisHeroContentIsVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isReadThisImageVisible());
        Assert.assertTrue(homePage.isReadThisTitleVisible());
        Assert.assertTrue(homePage.isReadNowButtonVisible());
    }

    @Test
    public void verifyReadNowButtonNavigation() {
        HomePage homePage = getHomePage();
        homePage.clickReadNowButton();
        Assert.assertTrue(homePage.isArticlePageOpened());
    }

    /* ================= LATEST SECTION ================= */

    @Test
    public void verifyLatestSectionIsVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isLatestSectionVisible());
    }

    @Test
    public void verifyLatestArticlesListIsNotEmpty() {
        HomePage homePage = getHomePage();
        Assert.assertEquals(homePage.getLatestArticlesCount(), 5);
    }

    @Test
    public void verifyLatestArticlesHaveThumbnailAndTitle() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.allLatestArticlesHaveImage());
        Assert.assertTrue(homePage.allLatestArticlesHaveTitle());
    }

    @Test
    public void verifyLatestArticleNavigation() {
        HomePage homePage = getHomePage();
        homePage.clickFirstLatestArticle();
        Assert.assertTrue(homePage.isArticlePageOpened());
    }

    /* ================= READ THIS ================= */

    @Test
    public void verifyReadThisSection() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Read This"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("Read This"));
    }

    /* ================= LATEST ================= */

    @Test
    public void verifyLatestSection() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Latest"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("Latest"));
    }

    /* ================= RECOMMENDED FOR YOU ================= */

    @Test
    public void verifyRecommendedForYouSection() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Recommended For You"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("Recommended For You"));
    }

    @Test
    public void verifyRecommendedCarouselRightButton() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.verifyRecommendedCarouselNext());
    }

    @Test
    public void verifyRecommendedCarouselLeftButton() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.verifyRecommendedCarouselPrevious());
    }

    /* ================= MOST POPULAR ================= */

    @Test
    public void verifyMostPopularSection() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Most Popular"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("Most Popular"));
    }

    /* ================= ENTERTAINMENT ================= */

    @Test
    public void verifyEntertainmentSection() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Entertainment"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("Entertainment"));
    }

    /* ================= EAT & DRINK ================= */

    @Test
    public void verifyEatAndDrinkSection() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Eat & Drink"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("Eat & Drink"));
    }

    /* ================= SEE & DO ================= */

    @Test
    public void verifySeeAndDoSection() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("See & Do"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("See & Do"));
    }

    /* ================= LIVE & LEARN ================= */

    @Test
    public void verifyLiveAndLearnSection() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Live & Learn"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("Live & Learn"));
    }

    /* ================= WATCH ================= */

    @Test
    public void verifyWatchSection() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Watch"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("Watch"));
    }

    /* ================= FEATURED PODCASTS ================= */

    @Test
    public void verifyFeaturedPodcastsSection() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Featured Podcasts"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("Featured Podcasts"));
    }

    /* ================= CHECK THESE OUT ================= */

    @Test
    public void verifyCheckTheseOutSection() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Check These Out"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("Check These Out"));
    }



}
