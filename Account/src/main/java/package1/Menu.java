package package1;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Menu extends CommonAPI {
    @FindBy(id = "bankingTab")
    WebElement bankingTab;
    @FindBy(linkText = "Checking Accounts")
    WebElement CheckingAccounts;

    public void BankingMenu(){
        mouseHoverByXpath(bankingTab);
        CheckingAccounts.click();
    }

}
