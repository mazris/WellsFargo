package package1;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends CommonAPI {
    @FindBy(id = "destination")
    WebElement destination;
    @FindBy(id = "userid")
    WebElement userid;
    @FindBy(id = "password")
    WebElement password;
    @FindBy(id = "btnSignon")
    WebElement btnSignon;
    @FindBy(id = "bankingTab")
    WebElement bankingTab;
    @FindBy(linkText = "Checking Accounts")
    WebElement CheckingAccounts;
    @FindBy(linkText = "Savings Accounts and CDs")
    WebElement SavingAccountCd;
    @FindBy(linkText = "Debit and Prepaid Cards")
    WebElement debitprepaidCard;
    @FindBy(linkText = "Credit Cards")
    WebElement CreditCards;
    @FindBy(linkText = "Foreign Exchange")
    WebElement ForeignExchange;
    @FindBy(linkText = "Global Remittance Services")
    WebElement GlobalRemittanceService;
    @FindBy(tagName = "a")
    List<WebElement> aTag;


    public void signON(){
        Select Destination = new Select(destination);
        Destination.selectByValue("Transfer");
        userid.sendKeys("SoniMazri");
        password.sendKeys("123");
        btnSignon.click();
        sleepFor(3);
    }
    public void BankingMenu(){
        ArrayList<WebElement> banking = new ArrayList<WebElement>();
        banking.add(CheckingAccounts); banking.add(SavingAccountCd) ; banking.add(debitprepaidCard);banking.add(CreditCards);
        banking.add(ForeignExchange);banking.add(GlobalRemittanceService);
        ArrayList<String> url = new ArrayList<String>();
        url.add("https://www.wellsfargo.com/checking/?linkLoc=fn");
        url.add("https://www.wellsfargo.com/savings-cds/?linkLoc=fn");
        url.add("https://www.wellsfargo.com/debit-card/?linkLoc=fn");
        url.add("https://www.wellsfargo.com/credit-cards/?linkLoc=fn");
        url.add("https://www.wellsfargo.com/foreign-exchange/?linkLoc=fn");
        url.add("https://www.wellsfargo.com/international-remittances/?linkLoc=fn");
        for(int i=0;i<banking.size();i++){
            mouseHoverByXpath(bankingTab);
            banking.get(i).click();
            Assert.assertEquals(driver.getCurrentUrl(),url.get(i));
            driver.navigate().back();
        }

    }
    public void testBrokenLinks() throws IOException {
       System.out.println("running test");
    }


}
