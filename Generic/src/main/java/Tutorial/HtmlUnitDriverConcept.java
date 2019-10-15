package Tutorial;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.concurrent.TimeUnit;

public class HtmlUnitDriverConcept {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","/Users/mac/Documents/weekdays/Welsfargo/Generic/src/main/resources/chromedriver");
        //to use htmlunitDriver we need to have htmlunitdriver jar file in libraries
        /**advantages:
        testing is happening behind the scene - no browser is launched
         Very fast
        disadvantage not suitable for Actions class :mousemovement , doubleclick , drag and drop
         **/
        WebDriver driverr= new HtmlUnitDriver();
        //WebDriver driverr = new ChromeDriver();
        driverr.manage().window().maximize();
        driverr.manage().deleteAllCookies();
        driverr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driverr.get("http://www.facebook.com");
        System.out.println("before logging "+driverr.getTitle());
        driverr.findElement(By.name("email")).sendKeys("Sonia");
        driverr.findElement(By.name("pass")).sendKeys("123");
        driverr.findElement(By.xpath("//button[@type='submit']")).click();
        System.out.println("after logging "+driverr.getTitle());
        driverr.quit();

    }
}
