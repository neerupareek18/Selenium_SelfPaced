package SeleniumPractice.CustomerPortal.TrailerCatalog;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Details_Page_InquireNow {

    WebDriver driver;
    @Test
    public void detailsPage_Inquire_Download() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://shop.trinitytrailer.com/trailer-catalog");
        driver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement trailername = driver.findElement(By.xpath("//div[@class='catalog-list-block']/descendant::div[@class='catalog-list-card row ms-0 me-0'][2]/descendant::span"));
        trailername.click();
        driver.navigate().back();
        Thread.sleep(5000);

        WebElement viewDetails = driver.findElement(By.xpath("//div[@class='catalog-list-block']/descendant::div[@class='catalog-list-card row ms-0 me-0'][2]/descendant::div/a/i[@class='fa fa-info-circle me-2']"));
        viewDetails.click();
        Thread.sleep(5000);

        //Inquire Now
        driver.findElement(By.xpath("//div/a[@class='product-btn btn']")).click();
        for(int i=1;i<=3;i++){
            driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_UP);
        }
        driver.findElement(By.xpath("//div/a[@class='product-btn btn']/i[@class= 'fa fa-envelope me-2']")).click();

        //RequestMoreInformation
        driver.findElement(By.xpath("//div/input[@formcontrolname='firstName']")).sendKeys("TestName");
        driver.findElement(By.xpath("//div/input[@formcontrolname='lastName']")).sendKeys("TestLastName");
        driver.findElement(By.xpath("//div/input[@formcontrolname='email']")).sendKeys("test@yopmail.com");

        Select s = new Select(driver.findElement(By.xpath("//div/select[@formcontrolname='state']")));
        s.selectByIndex(1);
        driver.findElement(By.xpath("//div/button/i[@class='fa fa-location-arrow me-2']")).click();
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/h2[@class='intro__title']")));
        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://trinitytrailer.com/pre-owned-trailers-request-received/"));

        driver.navigate().back();

        driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_UP);
        Thread.sleep(1000);
        driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_UP);
        Thread.sleep(1000);

        //Download Quote
        Actions a = new Actions(driver);
        a.moveToElement(driver.findElement(By.linkText("Download Quote"))).click();
        Thread.sleep(10000);


    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }


}
