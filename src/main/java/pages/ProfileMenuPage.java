package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import config.Config;
import pages.base.BasePage;

public class ProfileMenuPage extends BasePage {

    /* ================= LOCATORS ================= */

    private final Locator profileIcon;

    private final Locator editPreferences;
    private final Locator bookmarks;
    private final Locator signOut;

    private final Locator manageAccountBtn;
    private final Locator closeButton;

    public ProfileMenuPage(Page page) {
        super(page);

        profileIcon = page.locator("(//img[@id='user-avatar'])[2]");

        editPreferences = page.locator("//a[contains(text(),'Edit your preferences')]");
        bookmarks = page.locator("//a[contains(text(),'Bookmarks')]");
        signOut = page.locator("//a[contains(text(),'Sign Out')]");

        manageAccountBtn = page.locator("//button[contains(text(),'Manage meconnect account')]");
        closeButton = page.locator("//button[contains(@class,'close')]");
    }

    /* ================= ACTIONS ================= */

    public void openProfileMenu() {
        openBaseUrl();
        navigateToLogin();
        Locator emailInput = page.locator("//input[@type='email']");
        Locator passwordInput = page.locator("//input[@type='password']");
        Locator signInButton = page.locator("(//button[normalize-space()='SIGN IN'])[2]");
        waitUntilVisible(emailInput);

        safeFill(emailInput, Config.get("username"));
        safeFill(passwordInput, Config.get("password"));
        safeClick(signInButton);
        waitUntilVisible(profileIcon);
        safeClick(profileIcon);
        waitUntilVisible(editPreferences);

    }

    private void navigateToLogin() {
        Locator unloggedProfileIcon = page.locator("(//nav[@id='profile-menu-nav']//li)[1]");
        safeClick(unloggedProfileIcon);
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
        return profileIcon.isVisible();
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