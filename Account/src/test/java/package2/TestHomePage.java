package package2;

import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import package1.HomePage;

import java.io.IOException;

public class TestHomePage extends CommonAPI {
    HomePage homePage;

    @BeforeMethod
    public void initialization(){
       homePage= PageFactory.initElements(driver, HomePage.class);
    }

    @Test(enabled = false)
    public void testSignOn(){
        homePage.signON();
    }
    @Test(enabled = false)
    public void testMenu(){
        homePage.BankingMenu();
    }
    @Test
    public void testLink() throws IOException {
        homePage.testBrokenLinks();
    }
}
