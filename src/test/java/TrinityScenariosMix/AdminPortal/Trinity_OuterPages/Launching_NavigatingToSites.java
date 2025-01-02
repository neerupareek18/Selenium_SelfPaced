package TrinityScenariosMix.AdminPortal.Trinity_OuterPages;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Launching_NavigatingToSites {

    @Test //Instead of using main -- We can make the file to testNG
    public void LaunchingAndNavigation() throws MalformedURLException
    {
        ChromeOptions c = new ChromeOptions();
        c.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        c.addArguments("--headless");

        WebDriver driver=new ChromeDriver(c); //We need to find the options reference while initialising the driver
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
