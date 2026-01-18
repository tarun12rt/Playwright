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

    public LoginPage(Page page) {
        super(page);

        emailInput = page.getByLabel("Email");
        passwordInput = page.getByLabel("Password");
        signInButton = page.locator("(//button[@type='submit'])[2]");

        forgotPasswordLink = page.getByText("Forgot your password?");
        createAccountLink = page.getByText("CREATE ONE NOW");

        continueWithApple = page.getByText("Continue with Apple");
        continueWithFacebook = page.getByText("Continue with Facebook");
        continueWithGoogle = page.getByText("Continue with Google");

        termsOfService = page.getByText("Terms of Services");
        privacyPolicy = page.getByText("Privacy Policy");
        reportVulnerability = page.getByText("Report Vulnerability");
        onlineLinksPolicy = page.getByText("Online Links Policy");

        headerProfileIcon = page.locator("a.sign-in-link[href*='/profile/sso/login']");
    }

    // --- Actions ---
    public void open() {
        page.navigate("https://auth.mediacorp.sg/login");
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
//        safeClick(signInButton);
    }

    public void clickProfileIcon() {
        safeClick(headerProfileIcon);
    }
}
