package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.base.BasePage;

public class ProfileMenuPage extends BasePage {

    /* ================= LOCATORS ================= */

    private final Locator profileIcon;
    private final Locator modal;

    private final Locator editPreferences;
    private final Locator bookmarks;
    private final Locator signOut;

    private final Locator manageAccountBtn;
    private final Locator closeButton;

    public ProfileMenuPage(Page page) {
        super(page);

        profileIcon = page.locator("a.user-avatar");
        modal = page.locator("//div[contains(@class,'modal')]");

        editPreferences = page.locator("//a[contains(text(),'Edit your preferences')]");
        bookmarks = page.locator("//a[contains(text(),'Bookmarks')]");
        signOut = page.locator("//a[contains(text(),'Sign Out')]");

        manageAccountBtn = page.locator("//button[contains(text(),'Manage meconnect account')]");
        closeButton = page.locator("//button[contains(@class,'close')]");
    }

    /* ================= ACTIONS ================= */

    public void openProfileMenu() {
        openBaseUrl();
        safeClick(profileIcon);
        waitUntilVisible(modal);
    }

    public void clickEditPreferences() {
        clickAndWaitForPageLoad(editPreferences);
    }

    public void clickBookmarks() {
        clickAndWaitForPageLoad(bookmarks);
    }

    public void clickSignOut() {
        clickAndWaitForPageLoad(signOut);
    }

    public void clickManageAccount() {
        clickAndWaitForPageLoad(manageAccountBtn);
    }

    public void closeMenu() {
        safeClick(closeButton);
    }

    /* ================= VERIFICATIONS ================= */

    public boolean isProfileMenuVisible() {
        return modal.isVisible();
    }

    public boolean isEditPreferencesVisible() {
        return editPreferences.isVisible();
    }

    public boolean isBookmarksVisible() {
        return bookmarks.isVisible();
    }

    public boolean isSignOutVisible() {
        return signOut.isVisible();
    }

    public boolean isManageAccountVisible() {
        return manageAccountBtn.isVisible();
    }

    public boolean isEditPreferencesPageOpened() {
        return page.url().contains("preferences");
    }

    public boolean isBookmarksPageOpened() {
        return page.url().contains("bookmarks");
    }

    public boolean isManageAccountPageOpened() {
        return page.url().contains("account");
    }

    public boolean isLoggedOut() {
        return page.url().contains("login");
    }
}