package SeleniumPractice.CustomerPortal.TrailerCatalog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class DetailsPage {
        WebDriver driver;

        @BeforeTest
        public void navigateToTrailerCatalo() {
            ChromeOptions c = new ChromeOptions();
            c.addArguments("--start-maximized");
            c.addArguments("--incognito");
            driver = new ChromeDriver(c);
            driver.get("https://trinitytrailer.com/");
            Actions a = new Actions(driver);

            List<WebElement> menuItems = driver.findElements(By.xpath("//div/ul[@id='menu-primary-menu']/li"));
            int i = 1;
            for (WebElement m : menuItems) {
                if (driver.findElement(By.xpath("//div/ul[@id='menu-primary-menu']/li[" + i + "]/a")).getText().equalsIgnoreCase("Trailers & Truck Bodies")) {
                    a.moveToElement(driver.findElement(By.xpath("//div/ul[@id='menu-primary-menu']/li[" + i + "]/a"))).build().perform();
                    List<WebElement> submenuitems = driver.findElements(By.xpath("//div/ul[@id='menu-primary-menu']/li[" + i + "]/ul/li"));
                    int j = 1;
                    for (WebElement s : submenuitems) {
                        if (driver.findElement(By.xpath("//div/ul[@id='menu-primary-menu']/li[" + i + "]/ul/li[" + j + "]/a")).getText().equalsIgnoreCase("Pre-Owned In Stock")) {
                            driver.findElement(By.xpath("//div/ul[@id='menu-primary-menu']/li[" + i + "]/ul/li[" + j + "]/a")).click();
                            break;
                        }
                        j++;
                    }
                    i++;
                    break;
                }
            }
            Assert.assertTrue(driver.getCurrentUrl().contains("https://shop.trinitytrailer.com/trailer-catalog"));
        }

@Test
public void detailsPage() throws InterruptedException {
    WebElement trailername = driver.findElement(By.xpath("//div[@class='catalog-list-block']/descendant::div[@class='catalog-list-card row ms-0 me-0'][1]/descendant::span"));
    System.out.println(trailername.getText());
    System.out.println(driver.findElement(By.xpath("//div[@class='catalog-list-block']/descendant::div[@class='catalog-list-card row ms-0 me-0'][1]/descendant::div[@class='price-value cusrsorepointer']")).getText());
//To add Specs

    WebElement image = driver.findElement(By.xpath("//div[@class='catalog-list-block']/descendant::div[@class='catalog-list-card row ms-0 me-0'][1]/div[@class='catalog-img col-md-4 col-lg-4 col-12']"));
    WebElement saveItem = driver.findElement(By.xpath("//div[@class='catalog-list-block']/descendant::div[@class='catalog-list-card row ms-0 me-0'][1]/descendant::div/a/span/i[@class='fa fa-heart-o me-2']"));
    WebElement viewDetails = driver.findElement(By.xpath("//div[@class='catalog-list-block']/descendant::div[@class='catalog-list-card row ms-0 me-0'][1]/descendant::div/a/i[@class='fa fa-info-circle me-2']"));
    image.click();
    Thread.sleep(2000);
}
    @AfterTest
    public void tearDown(){
        driver.close();
    }
}
