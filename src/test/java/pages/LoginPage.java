package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    // --- Locators ---
    private final Locator emailInput;
    private final Locator passwordInput;
    private final Locator signInButton;
    private final Locator forgotPasswordLink;
    private final Locator createAccountLink;

    private final Locator continueWithApple;
    private final Locator continueWithFacebook;
    private final Locator continueWithGoogle;

    private final Locator termsOfService;
    private final Locator privacyPolicy;
    private final Locator reportVulnerability;
    private final Locator onlineLinksPolicy;
    private final Locator headerProfileIcon;
    private final Locator loginErrorMessage;
    private final Locator loggedInProfileIcon;


    public LoginPage(Page page) {
        super(page);

        emailInput = page.locator("#username");
        passwordInput = page.locator("#password");
        signInButton = page.locator("(//button[@type='submit'])[2]");

        forgotPasswordLink = page.getByText("Forgot your password?");
        createAccountLink = page.getByText("CREATE ONE NOW");

        continueWithApple = page.getByText("Continue with Apple");
        continueWithFacebook = page.getByText("Continue with Facebook");
        continueWithGoogle = page.getByText("Continue with Google");

        termsOfService = page.locator("//a[normalize-space()='Terms of Services']");
        privacyPolicy = page.locator("//a[normalize-space()='Privacy Policy']");
        reportVulnerability = page.locator("//a[normalize-space()='Report Vulnerability']");
        onlineLinksPolicy = page.locator("//a[normalize-space()='Online Links Policy']");

        headerProfileIcon = page.locator("(//nav[@id='profile-menu-nav']//a)[1]");
        loginErrorMessage=page.getByText("Invalid email or password");
        loggedInProfileIcon=page.locator("#user-avatar:visible");
    }

    // --- Actions ---
    public void open() {
        openBaseUrl();
    }

    // --- Verifications ---
    public boolean isLoginFormVisible() {
        return safeIsVisible(emailInput)
                && safeIsVisible(passwordInput)
                && safeIsVisible(signInButton);
    }

    public boolean areSocialLoginButtonsVisible() {
        return safeIsVisible(continueWithApple)
                && safeIsVisible(continueWithFacebook)
                && safeIsVisible(continueWithGoogle);
    }

    public boolean areFooterLinksVisible() {
        scrollTo(termsOfService);
        return safeIsVisible(termsOfService)
                && safeIsVisible(privacyPolicy)
                && safeIsVisible(reportVulnerability)
                && safeIsVisible(onlineLinksPolicy);
    }

    public void enterEmail(String email) {
        safeFill(emailInput, email);
    }

    public void enterPassword(String password) {
        safeFill(passwordInput, password);
    }

    public void clickSignIn() {
        safeClick(signInButton);
    }

    public void clickProfileIcon() {
        safeClick(headerProfileIcon);
    }

    public boolean verifyErrorsMessage(String errorMessage) {
        String actualText = getText(loginErrorMessage);
        return actualText.equals(errorMessage);
    }

    public boolean verifyLoggedInProfileIcon() {
       boolean element = safeIsVisible(loggedInProfileIcon);
       return element;

    }
}
