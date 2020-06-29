package Common;

import com.aventstack.extentreports.markuputils.ExtentColor;
import io.appium.java_client.MobileElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Utilities extends Base {

    private static Logger log = LogManager.getLogger(Utilities.class.getName());
    static WebDriver driver;

    public static Properties prop = new Properties();


    public Utilities() throws IOException {
        this.driver=capabilities("emul");
    }

    public static void waitUntilElementVisible(By by) throws MalformedURLException {
        setImplicitWait(10);

        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception var6) {
            String customErrorMessage = getCustomErrorMessageForReport(by);
            log.info(customErrorMessage, ExtentColor.RED);
            throw new ElementNotVisibleException(customErrorMessage, var6.getCause());
        } finally {
            setImplicitWait(10);
        }
    }
    public static String getProp(String attribute) throws IOException {
        String path= System.getProperty("user.dir")+"/src/main/resources/data.properties";
        FileInputStream fis = new FileInputStream(path);
        prop.load(fis);
        return prop.getProperty(attribute);
    }

    public static void setImplicitWait(int seconds) throws MalformedURLException {
        driver.manage().timeouts().implicitlyWait((long)seconds, TimeUnit.SECONDS);
    }

    public static String getCustomErrorMessageForReport(By by) {
        String className = by.getClass().getSimpleName();
        String value = "";
        try {
            Field field = by.getClass().getDeclaredFields()[1];
            field.setAccessible(true);
            value = field.get(by).toString();
        } catch (Exception var4) {
        }
        return "Timed out waiting for visibility of element located = " + className + "(" + value + ")";
    }

    public static void click(By by) throws MalformedURLException {
        findElement(by).click();
        log.info("Click on " + getCallingMethodName());
    }

    public static String getCallingMethodName() {
        String methodName;
        try {
            String[] str = Thread.currentThread().getStackTrace()[3].getMethodName().split("_");
            String newStr = "";

            for(int i = 0; i < str.length; ++i) {
                if (i != 0 && i != str.length - 1) {
                    newStr = newStr + str[i] + "_";
                }
            }

            methodName = newStr.substring(0, newStr.length() - 1);
        } catch (Exception var4) {
            methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        }

        return methodName;
    }

    public static MobileElement findElement(By by) throws MalformedURLException {
        waitUntilElementVisible(by);
        return (MobileElement) driver.findElement(by);
    }

    public static void enterdata(By by, String val) throws MalformedURLException {
        waitUntilElementVisible(by);
        findElement(by).sendKeys(val);
        log.info("Entered data on " + getCallingMethodName());
    }
}
