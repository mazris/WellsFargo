package Tutorial;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HandlePop {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","/Users/mac/Documents/weekdays/Welsfargo/Generic/src/main/resources/chromedriver");
        WebDriver driverr = new ChromeDriver();
        driverr.manage().window().maximize();
        driverr.manage().deleteAllCookies();
        driverr.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //implicitly wait is always applied globaly , available for all webelements
        driverr.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        driverr.get("http://www.popuptest.com/goodpopups.html");
        /** handle browser window popup-Advertisment popup**/
        driverr.findElement(By.xpath("//a[contains(text(),'Good PopUp #3')]")).click();
        // a new window will pop up
        //we need to switch back to the previous window so we use getwindowhandles to get windows id

       Set<String> handler= driverr.getWindowHandles();
       //iterate the set of strings
       Iterator<String> it=handler.iterator();
       // next will go to the next value the parent window id
        String parentid=it.next();
       System.out.println("parent window id ="+parentid);
       String childId=it.next();
       System.out.println("child window id = "+childId);
       //switch to childwindow
        driverr.switchTo().window(childId);
        System.out.println("child window title = "+driverr.getTitle());
        driverr.close();
        //switch to parent
        driverr.switchTo().window(parentid);
        System.out.println("parent window title = "+driverr.getTitle());
        /** for file upload pop : we don't click on the button that upload a file , instead wo do this
         * Driver.findElement(By.xpath("give the xpath")).sendKeys("we give the path to our file")
         * type='file' should be available
         */
        /** to handle alert popup we use the class Alert=> Alert API(accept,dismiss)
         * Alert alert= driver.switchTo().alert();
         * alert.accept(): OK
         * alert.dismiss(); Click on cancel
         * alert.sendKeys();
         * alert.getText();
         */
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driverr.quit();

    }
}
