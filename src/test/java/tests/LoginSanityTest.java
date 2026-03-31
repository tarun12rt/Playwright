package tests;

import config.Config;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import runner.BaseTestParallelExecution;

public class LoginSanityTest extends BaseTestParallelExecution {

    private LoginPage getLoginPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        LoginPage page = new LoginPage(page());
        page.open();
        return page;
    }

    /* ================= PAGE LOAD ================= */

    @Test
    public void verifyLoginPageLoads() {
        LoginPage page = getLoginPage();
        Assert.assertTrue(page.isLoginPageVisible(),
                "Login page not loaded properly");
    }

    /* ================= UI ELEMENTS ================= */

    @Test
    public void verifyLoginPageElements() {
        LoginPage page = getLoginPage();

        Assert.assertTrue(page.isEmailFieldVisible(), "Email field not visible");
        Assert.assertTrue(page.isPasswordFieldVisible(), "Password field not visible");
        Assert.assertTrue(page.isSignInButtonVisible(), "Sign in button not visible");
    }

    /* ================= VALID LOGIN ================= */

    @Test
    public void verifyLoginWithValidCredentials() {
        LoginPage page = getLoginPage();

        page.login(Config.get("username"),Config.get("password") );
        Assert.assertTrue(page.isLoginSuccessful(),
                "Login failed with valid credentials");
    }

    /* ================= INVALID LOGIN ================= */

    @Test
    public void verifyLoginWithInvalidCredentials() {
        LoginPage page = getLoginPage();

        page.login("invalid@gmail.com", "wrongpassword");

        Assert.assertTrue(page.isErrorMessageDisplayed(),
                "Error message not displayed for invalid login");
    }


    /* ================= FORGOT PASSWORD ================= */

    @Test
    public void verifyForgotPasswordNavigation() {
        LoginPage page = getLoginPage();

        page.clickForgotPassword();

        Assert.assertTrue(page.isForgotPasswordPageOpened(),
                "Forgot password page not opened");
    }
}