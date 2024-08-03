package SeleniumPractice.Trinity_SuperAdmin.Services;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TrailerCatalog_UploadImage {
    ChromeDriver driver;

    @Test
    public void NavigateToManagerPermissions() throws InterruptedException {
        ChromeOptions c = new ChromeOptions();
        c.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        c.addArguments("--start-maximized");
        c.addArguments("--incognito");
        //c.addArguments("--headless");

        driver = new ChromeDriver(c);
        driver.get("https://atlas-web-qa.azurewebsites.net/");

        WebElement email = driver.findElement(By.xpath("//input[@formcontrolname=\"email\"]"));
        WebElement password = driver.findElement(By.xpath("//input[@formcontrolname=\"password\"]"));

        WebElement signInButton = driver.findElement(By.cssSelector("[type='submit']"));

        email.sendKeys("Naveen1@yopmail.com");
        password.sendKeys("Admin@123");
        signInButton.click();

        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]")));
        driver.findElement(By.xpath("//a[text()='Services ']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[@title='Trailer Catalog']")).click();

        Thread.sleep(10000);
        WebElement row1 = driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/descendant::tr"));
        Actions a = new Actions(driver);
        a.doubleClick(row1).perform();

        List<WebElement> accordians = driver.findElements(By.xpath("//div[@class='accordionItem close']"));
    for(WebElement a1 : accordians){
        if(a1.getText().equalsIgnoreCase("Images")){
            a1.click();
            break;
        }
    }
    driver.findElement(By.xpath("//input[@type='file']")).sendKeys("C:\\Users\\Neeru\\Downloads\\Comming_soon.jpg");

    //Click Open in the pop-up to upload photo
    }
    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}