package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProfileMenuPage;
import runner.BaseTestParallelExecution;

public class ProfileMenuSanityTest extends BaseTestParallelExecution {

    private ProfileMenuPage getProfileMenuPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        ProfileMenuPage page = new ProfileMenuPage(page());
        page.openProfileMenu();
        return page;
    }

    /* ================= MODAL LOAD ================= */

    @Test
    public void verifyProfileMenuOpens() {
        ProfileMenuPage page = getProfileMenuPage();

        Assert.assertTrue(page.isProfileMenuVisible(),
                "Profile menu did not open");
    }

    /* ================= UI ELEMENTS ================= */

    @Test
    public void verifyProfileMenuElements() {
        ProfileMenuPage page = getProfileMenuPage();

        Assert.assertTrue(page.isEditPreferencesVisible(), "Edit Preferences not visible");
        Assert.assertTrue(page.isBookmarksVisible(), "Bookmarks not visible");
        Assert.assertTrue(page.isSignOutVisible(), "Sign Out not visible");
        Assert.assertTrue(page.isManageAccountVisible(), "Manage account button not visible");
    }

    /* ================= NAVIGATION ================= */

    @Test
    public void verifyEditPreferencesNavigation() {
        ProfileMenuPage page = getProfileMenuPage();

        page.clickEditPreferences();

        Assert.assertTrue(page.isEditPreferencesPageOpened(),
                "Edit Preferences page not opened");
    }

    @Test
    public void verifyBookmarksNavigation() {
        ProfileMenuPage page = getProfileMenuPage();

        page.clickBookmarks();

        Assert.assertTrue(page.isBookmarksPageOpened(),
                "Bookmarks page not opened");
    }

    /* ================= SIGN OUT ================= */

    @Test
    public void verifySignOut() {
        ProfileMenuPage page = getProfileMenuPage();

        page.clickSignOut();

        Assert.assertTrue(page.isLoggedOut(),
                "User not logged out properly");
    }

    /* ================= CLOSE MODAL ================= */

    @Test
    public void verifyCloseProfileMenu() {
        ProfileMenuPage page = getProfileMenuPage();

        page.closeMenu();

        Assert.assertFalse(page.isProfileMenuVisible(),
                "Profile menu did not close");
    }

    /* ================= BUTTON ================= */

    @Test
    public void verifyManageAccountNavigation() {
        ProfileMenuPage page = getProfileMenuPage();

        page.clickManageAccount();

        Assert.assertTrue(page.isManageAccountPageOpened(),
                "Manage account page not opened");
    }
}