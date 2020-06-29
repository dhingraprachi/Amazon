package pageObjects;

import Common.Base;
import Common.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.MalformedURLException;

public class login extends Base {

    WebDriver driver;

    By signInBtn = By.id("com.amazon.mShop.android.shopping:id/sign_in_button");
    By userId = By.id("ap_email_login");
    By continueBtn = By.xpath("//android.view.View[@resource-id='continue']");
    By password = By.id("ap_password");
    By login = By.id("signInSubmit");

    String user = Utilities.getProp("user");
    String pwd = Utilities.getProp("pwd");
    String device=Utilities.getProp("device");

    public login() throws IOException {
        this.driver=capabilities(device);
    }

    public void clickOnExisting() throws MalformedURLException {
        Utilities.waitUntilElementVisible(signInBtn);
        Utilities.click(signInBtn);
    }

    public void enterDetailsOfUser() throws MalformedURLException {
        Utilities.enterdata(userId,user);
        Utilities.click(continueBtn);
        Utilities.enterdata(password,pwd);
        Utilities.click(login);
    }
}
