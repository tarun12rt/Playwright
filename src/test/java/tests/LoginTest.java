package tests;

import base.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FooterPage;
import pages.LoginPage;

public class LoginTest extends BaseTestParallelExecution {
    @Test
    public void loginWithMeconnectCredentials() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());

        LoginPage loginPage = new LoginPage(page());
        loginPage.open();
        Assert.assertTrue(loginPage.clickProfileIconAndVerifyLoginPage());
    }
}
