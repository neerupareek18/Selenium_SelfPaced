package TrinityScenariosMix.CustomerPortal.TrailerCatalog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class NavigatingToTrailerCatalog {
    @Test
    public void navigateToTrailerCatalo(){
        ChromeOptions c = new ChromeOptions();
        c.addArguments("--start-maximized");
        c.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(c);
        driver.get("https://trinitytrailer.com/");
        Actions a = new Actions(driver);

        List <WebElement> menuItems = driver.findElements(By.xpath("//div/ul[@id='menu-primary-menu']/li"));
        int i=1;
        for(WebElement m: menuItems){
            if(driver.findElement(By.xpath("//div/ul[@id='menu-primary-menu']/li["+i+"]/a")).getText().equalsIgnoreCase("Trailers & Truck Bodies")){
                a.moveToElement(driver.findElement(By.xpath("//div/ul[@id='menu-primary-menu']/li["+i+"]/a"))).build().perform();
                List <WebElement> submenuitems = driver.findElements(By.xpath("//div/ul[@id='menu-primary-menu']/li["+i+"]/ul/li"));
                int j=1;
                for(WebElement s : submenuitems){
                    System.out.println(driver.findElement(By.xpath("//div/ul[@id='menu-primary-menu']/li["+i+"]/ul/li["+j+"]/a")).getText());
                    if(driver.findElement(By.xpath("//div/ul[@id='menu-primary-menu']/li["+i+"]/ul/li["+j+"]/a")).getText().equalsIgnoreCase("Pre-Owned In Stock")){
                        driver.findElement(By.xpath("//div/ul[@id='menu-primary-menu']/li["+i+"]/ul/li["+j+"]/a")).click();
                        break;
                    }
                    j++;
                }
                i++;
                break;
            }
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("https://shop.trinitytrailer.com/trailer-catalog"));

         driver.close();
        }

    }
