package Tutorial;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Wait {
    //Synchronisation in selenium
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","/Users/mac/Documents/weekdays/Welsfargo/Generic/src/main/resources/chromedriver");
        WebDriver driverr = new ChromeDriver();
        driverr.manage().window().maximize();
        driverr.manage().deleteAllCookies();
        driverr.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //implicitly wait is always applied globaly , available for all webelements
        driverr.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        driverr.get("http://www.facebook.com");
        WebElement firstname= driverr.findElement(By.name("email"));
        sendKeys(driverr,firstname,10,"sonia");
        WebElement login= driverr.findElement(By.xpath("//button[@type='submit']"));
        clickOnElement(driverr,login,10);
        driverr.quit();
        //never use implicitly wait and explicitly wait together because it will increase the timeout
    }
    public static void sendKeys(WebDriver driver, WebElement element , int timeout,String value){
        //wait for the element to be visible before throwing an exception
        new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);
    }
    public static void clickOnElement(WebDriver driver, WebElement element , int timeout){
        new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
}
