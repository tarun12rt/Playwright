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

    @Test(groups = "login")
    public void verifyLoginPageUI() {
        LoginPage loginPage = getLoginPage();
        Assert.assertTrue(loginPage.isLoginFormVisible());
    }

    @Test(groups = "login")
    public void verifySocialLoginButtonsAreVisible() {
        LoginPage loginPage = getLoginPage();
        Assert.assertTrue(loginPage.areSocialLoginButtonsVisible());
    }

    @Test(groups = "login")
    public void verifyFooterLinksAreVisibleOnLoginPage() {
        LoginPage loginPage = getLoginPage();
        Assert.assertTrue(loginPage.areFooterLinksVisible());
    }

    @Test(groups = "login")
    public void verifyLoginWithInvalidCredentialsShowsError() {
        LoginPage loginPage = getLoginPage();
        loginPage.enterEmail("invalid@email.com");
        loginPage.enterPassword("wrongPassword");
        loginPage.clickSignIn();
        Assert.assertTrue(
                loginPage.verifyErrorsMessage("Invalid email or password")
        );
    }
}
