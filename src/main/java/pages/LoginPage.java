package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.base.BasePage;

public class LoginPage extends BasePage {

    /* ================= LOCATORS ================= */

    /* ===== Login Form ===== */
    private final Locator emailInput;
    private final Locator passwordInput;
    private final Locator signInButton;

    /* ===== Links ===== */
    private final Locator forgotPasswordLink;
    private final Locator createAccountLink;

    /* ===== Validation / Errors ===== */
    private final Locator errorMessage;
    private final Locator validationMessage;

    /* ===== Page Identifier ===== */
    private final Locator loginHeader;

    /* ===== Post Login ===== */
    private final Locator profileIcon; // assume appears after login
    private final Locator unloggedProfileIcon;

    public LoginPage(Page page) {
        super(page);

        /* ===== Input Fields ===== */
        emailInput = page.locator("//input[@type='email']");
        passwordInput = page.locator("//input[@type='password']");
        signInButton = page.locator("(//button[normalize-space()='SIGN IN'])[2]");

        /* ===== Links ===== */
        forgotPasswordLink = page.locator("//a[contains(text(),'Forgot your password')]");
        createAccountLink = page.locator("//a[contains(text(),'CREATE ONE NOW')]");

        /* ===== Messages ===== */
        errorMessage = page.locator("(//span[@id='error-element-password'])[1]");
        validationMessage = page.locator("//span[contains(@class,'error') or contains(text(),'required')]");

        /* ===== Page ===== */
        loginHeader = page.locator("//h2[contains(text(),'Sign in')]");

        /* ===== Post Login ===== */
        profileIcon = page.locator("(//img[@id='user-avatar'])[2]");
        unloggedProfileIcon =  page.locator("(//nav[@id='profile-menu-nav']//li)[1]");
    }

    /* ================= ACTIONS ================= */

    public void open() {
        openBaseUrl();
        navigateToLogin(); // optional helper if login is via redirect
        waitUntilVisible(emailInput);
    }

    private void navigateToLogin() {
       safeClick(unloggedProfileIcon);
    }

    public void login(String email, String password) {
        safeFill(emailInput, email);
        safeFill(passwordInput, password);
        safeClick(signInButton);
    }

    public void clickForgotPassword() {
        clickAndWaitForPageLoad(forgotPasswordLink);
    }

    public void clickCreateAccount() {
        clickAndWaitForPageLoad(createAccountLink);
    }

    /* ================= VERIFICATIONS ================= */

    public boolean isLoginPageVisible() {
        return waitUntilVisible(emailInput);
    }

    public boolean isEmailFieldVisible() {
        return emailInput.isVisible();
    }

    public boolean isPasswordFieldVisible() {
        return passwordInput.isVisible();
    }

    public boolean isSignInButtonVisible() {
        return signInButton.isVisible();
    }

    public boolean isSignInButtonDisabled() {
        return signInButton.isDisabled();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isVisible();
    }

    public boolean isValidationMessageDisplayed() {
        return validationMessage.isVisible();
    }

    public boolean isForgotPasswordPageOpened() {
        return page.url().contains("reset-password");
    }

    public boolean isLoginSuccessful() {
        waitUntilVisible(profileIcon);
        return page.url().contains("logged");
    }

    public String getCurrentUrl() {
        return page.url();
    }
}