import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Launching_NavigatingToSites {

    @Test
    public void LaunchingAndNavigation() throws MalformedURLException
    {
        ChromeOptions c = new ChromeOptions();
        c.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver=new ChromeDriver();
        driver.get("https://atlas-web-qa.azurewebsites.net/");
        //This will not work without giving the protocol http/https
        //In get, we can't go forward and backwards
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.navigate().to("https://atlas-web-qa.azurewebsites.net/trailer-catalog");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().to(new URL("https://google.com")); //add the Exception throws MalformedURLException
        driver.navigate().refresh();
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        //To reiterate navigate().to() and navigate().get() does the same function
        //in navigate to, we can go backward and forward
        driver.close();
    }

}
