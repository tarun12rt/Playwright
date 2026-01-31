package tests;

import base.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class HomePageTest extends BaseTestParallelExecution {

    private HomePage getHomePage(){
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());
        HomePage homepage = new HomePage(page());
        homepage.open();
        return homepage;
    }

    @Test
    public void verifyHomePageUI() {
        LoginPage loginPage = getLoginPage();
        Assert.assertTrue(loginPage.isLoginFormVisible());
    }



}
