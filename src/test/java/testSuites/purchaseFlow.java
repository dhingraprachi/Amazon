package testSuites;

import Common.Base;
import org.testng.annotations.Test;
import pageObjects.login;

import java.io.IOException;

public class purchaseFlow extends Base {

    @Test(description = "Login to app and chekout the product")
    public void purchaseAndCheckout() throws IOException {
            login instance = new login();
             instance.clickOnExisting();
             instance.enterDetailsOfUser();
        }
    }


