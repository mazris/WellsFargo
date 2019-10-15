package package1;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BrokenLinks extends CommonAPI {
    @FindBy(tagName = "a")
    List<WebElement> aTag;

    @FindBy(tagName = "img")
    List<WebElement> IMG;

    public void testBrokenLinks() throws IOException {
        List<WebElement> list = aTag;
        list.addAll(IMG);

        List<WebElement> activeLinks = new ArrayList<WebElement>();

        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i).getAttribute("href") != null)) {
                activeLinks.add(list.get(i));
            }
        }

        System.out.println("Total links is " + list.size());
        System.out.println("Total activelinks is " + activeLinks.size());


        for (int j = 0; j < activeLinks.size(); j++) {


            HttpURLConnection connection = (HttpURLConnection) new URL(activeLinks.get(j).getAttribute("href")).openConnection();

            connection.connect();
            String response = connection.getResponseMessage();
            int code = connection.getResponseCode();
            connection.disconnect();

            System.out.println(activeLinks.get(j).getAttribute("href")+"     "+ response+"    "+ code);

        }
    }
}