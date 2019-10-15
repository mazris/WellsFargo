package Tutorial;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class LaunchSafari {
    public static void main(String[] args) {
        WebDriver driver = new SafariDriver();
        // in safari we dont do the setProperty
        //must allow remote automation in develop menu of safari
        driver.get("http://wwww.google.com");
        driver.quit();
    }


}
