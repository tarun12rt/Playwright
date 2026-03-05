package tests;

import runner.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.common.HomePage;

public class HomePageTest extends BaseTestParallelExecution {

    private HomePage getHomePage(){
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        HomePage homepage = new HomePage(page());
        homepage.open();
        return homepage;
    }
    /* ================= BASIC LOAD ================= */

    @Test
    public void verifyHomePageLoadsSuccessfully() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isHomepageVisible());
    }

    /* ================= CORE SECTIONS ================= */

    @Test
    public void verifyMajorSectionsAreVisible() {
        HomePage homePage = getHomePage();

        Assert.assertTrue(homePage.isSectionVisible("Read This"));
        Assert.assertTrue(homePage.isSectionVisible("Latest"));
        Assert.assertTrue(homePage.isSectionVisible("Recommended For You"));
        Assert.assertTrue(homePage.isSectionVisible("Most Popular"));
        Assert.assertTrue(homePage.isSectionVisible("Entertainment"));
        Assert.assertTrue(homePage.isSectionVisible("Eat & Drink"));
        Assert.assertTrue(homePage.isSectionVisible("See & Do"));
        Assert.assertTrue(homePage.isSectionVisible("Live & Learn"));
        Assert.assertTrue(homePage.isSectionVisible("Watch"));
        Assert.assertTrue(homePage.isSectionVisible("Featured Podcasts"));
        Assert.assertTrue(homePage.isSectionVisible("Check These Out"));
    }

    /* ================= CONTENT EXISTS ================= */

    @Test
    public void verifySectionsContainContent() {
        HomePage homePage = getHomePage();

        Assert.assertTrue(homePage.hasAtLeastOneItem("Latest"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("Recommended For You"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("Most Popular"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("Entertainment"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("Eat & Drink"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("Watch"));
    }

    /* ================= NAVIGATION CHECK ================= */

    @Test
    public void verifyLatestArticleNavigationWorks() {
        HomePage homePage = getHomePage();
        homePage.clickFirstLatestArticle();
        Assert.assertTrue(homePage.isArticlePageOpened());
    }

    @Test
    public void verifyRecommendedCarouselWorks() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.verifyRecommendedCarouselNext());
    }

    /* ================= NEWSLETTER BASIC ================= */

    @Test
    public void verifyNewsletterSectionVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isNewsletterSectionVisible());
    }

    @Test
    public void verifyNewsletterRejectsEmptyEmail() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isErrorShownForEmptyEmail());
    }
}
