package TrinityScenariosMix.CustomerPortal.PartsCatalog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class partsCatalogHomePage {
    WebDriver chromeDriver = new ChromeDriver();

    @Test
    public void clickOnTile() throws InterruptedException {
        chromeDriver.manage().window().maximize();
        chromeDriver.get("https://shop-trinitytrailer-stage-ecd7ecceb7hqb9c2.westus-01.azurewebsites.net/part-catalog-home");
        Thread.sleep(5000);

        chromeDriver.findElement(By.xpath("//div/p[text()='CONVEYOR SYSTEM']")).click();
        Thread.sleep(5000);
        chromeDriver.quit();

    }
}
