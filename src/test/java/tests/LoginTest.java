package tests;

import base.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FooterPage;
import pages.LoginPage;

public class LoginTest extends BaseTestParallelExecution {

    @Test
    public void verifyLoginPageUI() {

        LoginPage loginPage = new LoginPage(page());
        loginPage.open();
        loginPage.clickProfileIcon();
        Assert.assertTrue(loginPage.isLoginFormVisible());
    }

    @Test
    public void verifySocialLoginButtonsAreVisible() {

        LoginPage loginPage = new LoginPage(page());
        loginPage.open();
        Assert.assertTrue(loginPage.areSocialLoginButtonsVisible());
    }

    @Test
    public void verifyFooterLinksAreVisibleOnLoginPage() {

        LoginPage loginPage = new LoginPage(page());
        loginPage.open();
        Assert.assertTrue(loginPage.areFooterLinksVisible());
    }

    @Test
    public void verifyLoginWithInvalidCredentialsShowsError() {

        LoginPage loginPage = new LoginPage(page());
        loginPage.open();

        loginPage.enterEmail("invalid@email.com");
        loginPage.enterPassword("wrongPassword");
        loginPage.clickSignIn();

        // Example: URL should not change to dashboard
        Assert.assertTrue(
                page().url().contains("auth.mediacorp.sg"),
                "Unexpected navigation after invalid login"
        );

        test().pass("Invalid login scenario verified");
    }
}
