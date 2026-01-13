package tests;

import base.BaseTestParallelExecution;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FooterPage;

public class LoginTest extends BaseTestParallelExecution {
    @Test
    public void loginWithMeconnectCredentials() {
        System.out.println("Running on Thread ID: " + Thread.currentThread().getId());

        FooterPage footerPage = new FooterPage(page());
        footerPage.open();
        Assert.assertTrue(footerPage.verifyFooterIsVisible(),"Footer is not visible");
    }
}
