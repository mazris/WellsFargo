package package2;

import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import package1.Menu;

public class TestMenu extends CommonAPI {
    Menu menu;

    @BeforeMethod
    public void intialization(){
        menu= PageFactory.initElements(driver, Menu.class);
    }
    @Test
    public void testMenu(){
        menu.BankingMenu();
    }

}
