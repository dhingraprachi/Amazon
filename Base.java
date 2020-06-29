package Common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Base {

    public static AndroidDriver<AndroidElement> capabilities(String device) throws MalformedURLException {

        File f = new File("src");
        File appFile = new File(f, "main/resources/Amazon_shopping.apk");

        DesiredCapabilities dc = new DesiredCapabilities();

        if(device.equalsIgnoreCase("Real"))
            dc.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Device");
        else
            dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emul");
        dc.setCapability(MobileCapabilityType.APP,appFile.getAbsolutePath());
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        AndroidDriver<AndroidElement> ad = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"), dc);
        return ad;
    }
}
