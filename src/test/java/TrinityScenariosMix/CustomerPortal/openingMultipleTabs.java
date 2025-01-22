package TrinityScenariosMix.CustomerPortal;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class openingMultipleTabs {
    WebDriver chromeDriver = new ChromeDriver();

    @Test
    public void openMultipleTabs() throws InterruptedException {
        chromeDriver.get("https://atlas-web-stage.azurewebsites.net");

        ((JavascriptExecutor) chromeDriver).executeScript("window.open()");
        ((JavascriptExecutor) chromeDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(chromeDriver.getWindowHandles());

        chromeDriver.switchTo().window(tabs.get(1));
        chromeDriver.get("https://atlas-web-stage.azurewebsites.net");

        chromeDriver.switchTo().window(tabs.get(2));
        chromeDriver.get("https://atlas-web-stage.azurewebsites.net");
        Thread.sleep(5000);

        chromeDriver.quit();

    }
}
