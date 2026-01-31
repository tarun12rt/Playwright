package tests;

import base.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTestParallelExecution {

    private LoginPage getLoginPage() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        LoginPage loginPage = new LoginPage(page());
        loginPage.open();
        loginPage.clickProfileIcon();
        return loginPage;
    }

    @Test
    public void verifyLoginPageUI() {
        LoginPage loginPage = getLoginPage();
        Assert.assertTrue(loginPage.isLoginFormVisible());
    }

    @Test
    public void verifySocialLoginButtonsAreVisible() {
        LoginPage loginPage = getLoginPage();
        Assert.assertTrue(loginPage.areSocialLoginButtonsVisible());
    }

    @Test
    public void verifyFooterLinksAreVisibleOnLoginPage() {
        LoginPage loginPage = getLoginPage();
        Assert.assertTrue(loginPage.areFooterLinksVisible());
    }

    @Test
    public void verifyLoginWithInvalidCredentialsShowsError() {
        LoginPage loginPage = getLoginPage();
        loginPage.enterEmail("inssaxqvalid@gmail.com");
        loginPage.enterPassword("wrongPassword");
        loginPage.clickSignIn();
        Assert.assertTrue(loginPage.verifyErrorsMessage("Invalid email or password"));
    }

    @Test
    public void verifyLoginWithValidCredentials() {
        LoginPage loginPage = getLoginPage();
        loginPage.enterEmail("spd@gmail.com");
        loginPage.enterPassword("testautomation");
        loginPage.clickSignIn();
        Assert.assertTrue(loginPage.verifyLoggedInProfileIcon());

    }
}
