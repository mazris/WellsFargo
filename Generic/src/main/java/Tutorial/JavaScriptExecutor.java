package Tutorial;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class JavaScriptExecutor {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","/Users/mac/Documents/weekdays/Welsfargo/Generic/src/main/resources/chromedriver");
        WebDriver driverr = new ChromeDriver();
        driverr.manage().window().maximize();
        driverr.manage().deleteAllCookies();
        driverr.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //implicitly wait is always applied globaly , available for all webelements
        driverr.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        driverr.get("https://ui.freecrm.com/");
       WebElement username= driverr.findElement(By.name("email"));
       username.sendKeys("sonia");
        driverr.findElement(By.name("password")).sendKeys("123");
        WebElement login=driverr.findElement(By.xpath("//div[@class='ui fluid large blue submit button']"));
        flash(login,driverr);
        drawBorder(login,driverr);
        clickOnElement(driverr,login);
         refreshBrowser(driverr);
        System.out.println(getTitleByjs(driverr));
       System.out.println(getPageSource(driverr));
       //scrollDown(driverr);
       WebElement noaccount= driverr.findElement(By.xpath("//div[contains(text(),'No Account? Registration takes only a few seconds? ')]"));
       scrollIntoView(driverr,noaccount);
       sendKeys(driverr,"sonia");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driverr.quit();
    }
    //highlight element
    public static void flash(WebElement element , WebDriver driver){

        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String bgcolor= element.getCssValue("backgroundColor");
        for(int i=0;i<10;i++){
           changeColor("rgb(0,200,0)",element,driver);
           changeColor(bgcolor,element,driver);
        }
    }
    public static void changeColor(String color,WebElement element,WebDriver driver){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor='"+color+"'",element);

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static void drawBorder(WebElement element,WebDriver driver){
        JavascriptExecutor js= ((JavascriptExecutor)driver);
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }
    public static void clickOnElement(WebDriver driver, WebElement element){
        JavascriptExecutor js=((JavascriptExecutor) driver);
        js.executeScript("arguments[0].click();",element);
    }
    public static void refreshBrowser(WebDriver driver){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("history.go(0)");
    }
    public static String getTitleByjs(WebDriver driver){
        JavascriptExecutor js=((JavascriptExecutor) driver);
        String title =js.executeScript("return document.title;").toString();
        return  title;
    }
    //getpagesource
    public static String getPageSource(WebDriver driver){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String innertext=js.executeScript("return document.documentElement.innerText").toString();
        return innertext;
    }
    //scrolldown
    public static void scrollDown(WebDriver driver){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }
    public static void scrollIntoView(WebDriver driver, WebElement element){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView(true)",element);
    }
    public static void sendKeys(WebDriver driver, String value){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("document.getElementById('email').value='"+value+"'");
    }
    //handle hidden elements
    public static void hiddenelement(WebDriver driver){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("document.getElementByClassName(locator).click();");
    }
}
