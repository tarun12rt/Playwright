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
/*
    @Test
    public void verifyHomePageUI() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isHomepageVisible());
    }

    */
/* ================= READ THIS SECTION ================= *//*


    @Test
    public void verifyReadThisSection() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Read This"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("Read This"));
    }

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

    */
/* ================= LATEST SECTION ================= *//*


    @Test
    public void verifyLatestSection() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Latest"));
        Assert.assertTrue(homePage.hasAtLeastOneItem("Latest"));
    }

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

    @Test
    public void verifyLatestArticlesTitlesAreClickable() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.allLatestArticlesAreClickable());
    }

    @Test
    public void verifyLatestArticlesImagesAreLoaded() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.allLatestArticleImagesAreLoaded());
    }

    @Test
    public void verifyLatestArticlesHaveCategoryLabel() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.allLatestArticlesHaveCategory());
    }


    */
/* ================= RECOMMENDED FOR YOU ================= *//*


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

    @Test
    public void verifyRecommendedItemsHaveCategory() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.recommendedItemsHaveCategory());
    }

    @Test
    public void verifyRecommendedItemNavigation() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isRecommendedItemClickable());
    }

    @Test
    public void verifyRecommendedItemsHaveImageAndTitle() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.recommendedItemsHaveImage());
        Assert.assertTrue(homePage.recommendedItemsHaveTitle());
    }


    */
/* ================= MOST POPULAR ================= *//*


    @Test
    public void verifyMostPopularSectionIsVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Most Popular"));
    }

    @Test
    public void verifyMostPopularHasAtLeastOneItem() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.hasAtLeastOneItem("Most Popular"));
    }

    @Test
    public void verifyMostPopularCardCount() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.getItemCount("Most Popular") >= 1);
    }

    @Test
    public void verifyMostPopularCardsHaveImages() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllItemsHaveImages("Most Popular"));
    }

    @Test
    public void verifyMostPopularCardsHaveTitles() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllItemsHaveTitles("Most Popular"));
    }

    @Test
    public void verifyMostPopularCardsHaveCategoryTag() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllItemsHaveCategoryTag("Most Popular"));
    }

    @Test
    public void verifyMostPopularTitleIsClickable() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isFirstItemTitleClickable("Most Popular"));
    }

    @Test
    public void verifyMostPopularImageIsClickable() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isFirstItemImageClickable("Most Popular"));
    }

    @Test
    public void verifyMostPopularNavigationFromTitle() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFirstItemNavigateCorrectly("Most Popular"));
    }

    @Test
    public void verifyMostPopularThreeDotMenuVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isThreeDotMenuVisible("Most Popular"));
    }

    @Test
    public void verifyMostPopularThreeDotMenuOpens() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isThreeDotMenuWorking("Most Popular"));
    }

    @Test
    public void verifyLoadMoreButtonVisibleInMostPopular() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isLoadMoreVisible("Most Popular"));
    }

    @Test
    public void verifyLoadMoreButtonWorkingInMostPopular() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isLoadMoreWorking("Most Popular"));
    }

    @Test
    public void verifyLoadMoreAddsNewItemsInMostPopular() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isNewItemsAddedAfterLoadMore("Most Popular"));
    }

    @Test
    public void verifyMostPopularHandlesEmptyState() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isEmptyStateHandledProperly("Most Popular"));
    }

    @Test
    public void verifyMostPopularHandlesBrokenImages() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.areBrokenImagesHandled("Most Popular"));
    }

    @Test
    public void verifyMostPopularLongTitleDoesNotBreakUI() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isLongTitleHandledProperly("Most Popular"));
    }


    */
/* ================= ENTERTAINMENT ================= *//*

    @Test
    public void verifyEntertainmentSectionIsVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Entertainment"));
    }

    @Test
    public void verifyEntertainmentHasAtLeastOneItem() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.hasAtLeastOneItem("Entertainment"));
    }

    @Test
    public void verifyEntertainmentFeaturedArticleVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isFeaturedItemVisible("Entertainment"));
    }



    @Test
    public void verifyEntertainmentFeaturedArticleHasImage() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFeaturedItemHaveImage("Entertainment"));
    }

    @Test
    public void verifyEntertainmentFeaturedArticleHasTitle() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFeaturedItemHaveTitle("Entertainment"));
    }

    @Test
    public void verifyEntertainmentFeaturedArticleIsClickable() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isFeaturedItemClickable("Entertainment"));
    }

    @Test
    public void verifyEntertainmentFeaturedNavigation() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFeaturedItemNavigateCorrectly("Entertainment"));
    }

    @Test
    public void verifyEntertainmentSideArticlesCount() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.getSideItemCount("Entertainment") >= 1);
    }

    @Test
    public void verifyEntertainmentSideArticlesHaveImages() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllSideItemsHaveImages("Entertainment"));
    }

    @Test
    public void verifyEntertainmentSideArticlesHaveTitles() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllSideItemsHaveTitles("Entertainment"));
    }

    @Test
    public void verifyEntertainmentSideArticlesHaveCategoryTag() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllSideItemsHaveCategoryTag("Entertainment"));
    }

    @Test
    public void verifyEntertainmentSideArticleNavigation() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFirstSideItemNavigateCorrectly("Entertainment"));
    }

    @Test
    public void verifyEntertainmentSeeMoreVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSeeMoreVisible("Entertainment"));
    }

    @Test
    public void verifyEntertainmentSeeMoreNavigation() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSeeMoreNavigationWorking("Entertainment"));
    }

    @Test
    public void verifyEntertainmentThreeDotMenuVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isThreeDotMenuVisible("Entertainment"));
    }

    @Test
    public void verifyEntertainmentThreeDotMenuWorking() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isThreeDotMenuWorking("Entertainment"));
    }


    */
/* ================= EAT & DRINK ================= *//*


    @Test
    public void verifyEatAndDrinkSectionIsVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Eat & Drink"));
    }

    @Test
    public void verifyEatAndDrinkHasAtLeastOneItem() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.hasAtLeastOneItem("Eat & Drink"));
    }

    @Test
    public void verifyEatAndDrinkFeaturedArticleVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isFeaturedItemVisible("Eat & Drink"));
    }

    @Test
    public void verifyEatAndDrinkFeaturedArticleHasImage() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFeaturedItemHaveImage("Eat & Drink"));
    }

    @Test
    public void verifyEatAndDrinkFeaturedArticleHasTitle() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFeaturedItemHaveTitle("Eat & Drink"));
    }

    @Test
    public void verifyEatAndDrinkFeaturedArticleIsClickable() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isFeaturedItemClickable("Eat & Drink"));
    }

    @Test
    public void verifyEatAndDrinkFeaturedNavigation() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFeaturedItemNavigateCorrectly("Eat & Drink"));
    }

    @Test
    public void verifyEatAndDrinkSideArticlesCount() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.getSideItemCount("Eat & Drink") >= 1);
    }

    @Test
    public void verifyEatAndDrinkSideArticlesHaveImages() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllSideItemsHaveImages("Eat & Drink"));
    }

    @Test
    public void verifyEatAndDrinkSideArticlesHaveTitles() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllSideItemsHaveTitles("Eat & Drink"));
    }

    @Test
    public void verifyEatAndDrinkSideArticlesHaveCategoryTag() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllSideItemsHaveCategoryTag("Eat & Drink"));
    }

    @Test
    public void verifyEatAndDrinkSideArticleNavigation() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFirstSideItemNavigateCorrectly("Eat & Drink"));
    }

    @Test
    public void verifyEatAndDrinkSeeMoreVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSeeMoreVisible("Eat & Drink"));
    }

    @Test
    public void verifyEatAndDrinkSeeMoreNavigation() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSeeMoreNavigationWorking("Eat & Drink"));
    }

    @Test
    public void verifyEatAndDrinkThreeDotMenuVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isThreeDotMenuVisible("Eat & Drink"));
    }

    @Test
    public void verifyEatAndDrinkThreeDotMenuWorking() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isThreeDotMenuWorking("Eat & Drink"));
    }



    */
/* ================= SEE & DO ================= *//*


    @Test
    public void verifySeeAndDoSectionIsVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("See & Do"));
    }

    @Test
    public void verifySeeAndDoHasAtLeastOneItem() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.hasAtLeastOneItem("See & Do"));
    }

    @Test
    public void verifySeeAndDoSectionOrder() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionInCorrectOrder("See & Do"));
    }

    @Test
    public void verifySeeAndDoFeaturedArticleHasImage() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFeaturedItemHaveImage("See & Do"));
    }

    @Test
    public void verifySeeAndDoFeaturedArticleHasTitle() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFeaturedItemHaveTitle("See & Do"));
    }

    @Test
    public void verifySeeAndDoFeaturedNavigation() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFeaturedItemNavigateCorrectly("See & Do"));
    }

    @Test
    public void verifySeeAndDoSideArticlesCount() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.getSideItemCount("See & Do") >= 1);
    }

    @Test
    public void verifySeeAndDoSideArticlesHaveImages() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllSideItemsHaveImages("See & Do"));
    }

    @Test
    public void verifySeeAndDoSideArticlesHaveCategoryTag() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllSideItemsHaveCategoryTag("See & Do"));
    }

    @Test
    public void verifySeeAndDoSeeMoreVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSeeMoreVisible("See & Do"));
    }

    @Test
    public void verifySeeAndDoSeeMoreNavigation() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSeeMoreNavigationWorking("See & Do"));
    }

    */
/* ================= LIVE & LEARN ================= *//*


    @Test
    public void verifyLiveAndLearnSectionIsVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Live & Learn"));
    }

    @Test
    public void verifyLiveAndLearnHasAtLeastOneItem() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.hasAtLeastOneItem("Live & Learn"));
    }

    @Test
    public void verifyLiveAndLearnFeaturedArticleVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isFeaturedItemVisible("Live & Learn"));
    }

    @Test
    public void verifyLiveAndLearnFeaturedArticleHasImage() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFeaturedItemHaveImage("Live & Learn"));
    }

    @Test
    public void verifyLiveAndLearnFeaturedArticleHasTitle() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFeaturedItemHaveTitle("Live & Learn"));
    }

    @Test
    public void verifyLiveAndLearnFeaturedNavigation() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFeaturedItemNavigateCorrectly("Live & Learn"));
    }

    @Test
    public void verifyLiveAndLearnSideArticlesCount() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.getSideItemCount("Live & Learn") >= 1);
    }

    @Test
    public void verifyLiveAndLearnSideArticlesHaveImages() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllSideItemsHaveImages("Live & Learn"));
    }

    @Test
    public void verifyLiveAndLearnSideArticlesHaveTitles() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllSideItemsHaveTitles("Live & Learn"));
    }

    @Test
    public void verifyLiveAndLearnSideArticlesHaveCategoryTag() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllSideItemsHaveCategoryTag("Live & Learn"));
    }

    @Test
    public void verifyLiveAndLearnSeeMoreVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSeeMoreVisible("Live & Learn"));
    }

    @Test
    public void verifyLiveAndLearnSeeMoreNavigation() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSeeMoreNavigationWorking("Live & Learn"));
    }



    */
/* ================= WATCH ================= *//*


    @Test
    public void verifyWatchSectionIsVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Watch"));
    }

    @Test
    public void verifyWatchHasAtLeastOneVideo() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.hasAtLeastOneItem("Watch"));
    }

    @Test
    public void verifyWatchVideosHaveThumbnail() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllItemsHaveImages("Watch"));
    }

    @Test
    public void verifyWatchVideosHaveTitle() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllItemsHaveTitles("Watch"));
    }

    @Test
    public void verifyWatchVideosHaveDuration() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllVideosHaveDuration("Watch"));
    }

    @Test
    public void verifyWatchVideosHavePlayIcon() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllVideosHavePlayIcon("Watch"));
    }

    @Test
    public void verifyWatchVideoIsClickable() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isFirstVideoClickable("Watch"));
    }

    @Test
    public void verifyWatchVideoNavigationOrPlayback() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFirstVideoPlayOrNavigate("Watch"));
    }

    @Test
    public void verifyWatchCarouselRightArrowVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isCarouselRightArrowVisible("Watch"));
    }

    @Test
    public void verifyWatchCarouselLeftArrowVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isCarouselLeftArrowVisible("Watch"));
    }

    @Test
    public void verifyWatchCarouselScrollsRight() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isCarouselScrollingRight("Watch"));
    }

    @Test
    public void verifyWatchCarouselScrollsLeft() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isCarouselScrollingLeft("Watch"));
    }

    @Test
    public void verifyWatchVideoDurationFormat() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isVideoDurationFormatCorrect("Watch"));
    }



    */
/* ================= FEATURED PODCASTS ================= *//*


    @Test
    public void verifyFeaturedPodcastsSectionIsVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Featured Podcasts"));
    }

    @Test
    public void verifyFeaturedPodcastsHasAtLeastOneItem() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.hasAtLeastOneItem("Featured Podcasts"));
    }

    @Test
    public void verifyFeaturedPodcastsSectionOrder() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionInCorrectOrder("Featured Podcasts"));
    }

    @Test
    public void verifyFeaturedPodcastsHaveThumbnail() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllItemsHaveImages("Featured Podcasts"));
    }

    @Test
    public void verifyFeaturedPodcastsHaveTitle() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllItemsHaveTitles("Featured Podcasts"));
    }

    @Test
    public void verifyFeaturedPodcastsAreClickable() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isFirstItemClickable("Featured Podcasts"));
    }

    @Test
    public void verifyFeaturedPodcastsNavigation() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFirstItemNavigateCorrectly("Featured Podcasts"));
    }

    @Test
    public void verifyFeaturedPodcastsRightArrowVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isCarouselRightArrowVisible("Featured Podcasts"));
    }

    @Test
    public void verifyFeaturedPodcastsLeftArrowVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isCarouselLeftArrowVisible("Featured Podcasts"));
    }

    @Test
    public void verifyFeaturedPodcastsCarouselScrollsRight() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isCarouselScrollingRight("Featured Podcasts"));
    }

    @Test
    public void verifyFeaturedPodcastsMoreLinkVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSeeMoreVisible("Featured Podcasts"));
    }

    @Test
    public void verifyFeaturedPodcastsMoreLinkNavigation() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSeeMoreNavigationWorking("Featured Podcasts"));
    }

    */
/* ================= CHECK THESE OUT ================= *//*


    @Test
    public void verifyCheckTheseOutSectionIsVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isSectionVisible("Check These Out"));
    }

    @Test
    public void verifyCheckTheseOutHasAtLeastOneItem() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.hasAtLeastOneItem("Check These Out"));
    }

    @Test
    public void verifyCheckTheseOutItemsHaveImages() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllItemsHaveImages("Check These Out"));
    }

    @Test
    public void verifyCheckTheseOutItemsHaveTitles() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllItemsHaveTitles("Check These Out"));
    }

    @Test
    public void verifyCheckTheseOutItemsHaveSourceLabel() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doAllItemsHaveCategoryTag("Check These Out"));
    }

    @Test
    public void verifyCheckTheseOutItemNavigation() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.doesFirstItemNavigateCorrectly("Check These Out"));
    }

    @Test
    public void verifyCheckTheseOutThreeDotMenuVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isThreeDotMenuVisible("Check These Out"));
    }

    @Test
    public void verifyCheckTheseOutThreeDotMenuWorking() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isThreeDotMenuWorking("Check These Out"));
    }

    */
/* ================= NEWSLETTER ================= *//*


    @Test
    public void verifyNewsletterSectionIsVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isNewsletterSectionVisible());
    }

    @Test
    public void verifyNewsletterLogoIsVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isNewsletterLogoVisible());
    }

    @Test
    public void verifyNewsletterDescriptionIsVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isNewsletterDescriptionVisible());
    }

    @Test
    public void verifyEmailInputFieldIsVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isNewsletterEmailFieldVisible());
    }

    @Test
    public void verifySubscribeButtonIsVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isNewsletterSubscribeButtonVisible());
    }

    @Test
    public void verifyNewsletterSubscriptionWithValidEmail() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.subscribeWithValidEmail("testuser@example.com"));
    }

    @Test
    public void verifyNewsletterSubscriptionWithEmptyEmail() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isErrorShownForEmptyEmail());
    }

    @Test
    public void verifyNewsletterSubscriptionWithInvalidEmail() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isErrorShownForInvalidEmail("invalidemail"));
    }

    @Test
    public void verifyNewsletterSubscriptionWithSpecialCharacterEmail() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isErrorShownForInvalidEmail("test@@@example..com"));
    }

    @Test
    public void verifyNewsletterSubscriptionWithExistingEmail() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isDuplicateEmailHandled("automation.testingp@gmail.com"));
    }

    @Test
    public void verifyNewsletterTermsLinkVisible() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isNewsletterTermsLinkVisible());
    }

    @Test
    public void verifyNewsletterTermsLinkNavigation() {
        HomePage homePage = getHomePage();
        Assert.assertTrue(homePage.isNewsletterTermsLinkWorking());
    }


}
*/
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
