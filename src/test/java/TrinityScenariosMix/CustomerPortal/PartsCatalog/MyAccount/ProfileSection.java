package TrinityScenariosMix.CustomerPortal.PartsCatalog.MyAccount;

import net.bytebuddy.asm.Advice;
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
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class ProfileSection {

    WebDriver chromeDriver = new ChromeDriver();

@Test(priority = 1)
    public void login() throws InterruptedException {
        chromeDriver.get("https://shop-trinitytrailer-stage-ecd7ecceb7hqb9c2.westus-01.azurewebsites.net/part-catalog");
        chromeDriver.manage().window().maximize();
        WebElement signInLink = chromeDriver.findElement(By.linkText("Sign In"));
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Sign In")));
        signInLink.click();

        Assert.assertEquals(chromeDriver.getCurrentUrl(),"https://shop-trinitytrailer-stage-ecd7ecceb7hqb9c2.westus-01.azurewebsites.net/login");
Thread.sleep(3000);
    chromeDriver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("customer@yopmail.com");
    chromeDriver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Admin@123");
    chromeDriver.findElement(By.xpath("//button[@type='submit']")).click();

    Thread.sleep(20000);
    WebElement userName = chromeDriver.findElement(By.xpath("//span/p[@class=\"profile-name-wrap\"]"));

    WebDriverWait webDriverWait1 = new WebDriverWait(chromeDriver,Duration.ofSeconds(20));
    webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span/p[@class=\"profile-name-wrap\"]")));
}
@Test(priority = 2)
    public void myAccount() throws InterruptedException {
WebElement userName = chromeDriver.findElement(By.xpath("//span/p[@class=\"profile-name-wrap\"]"));

        Actions actions = new Actions(chromeDriver);
        userName.click();
        Thread.sleep(3000);

        WebElement myAccount = chromeDriver.findElement(By.xpath("//div/li/a[text()=\"My Account\"]"));
        actions.moveToElement(myAccount);
        actions.click().build().perform();

        Thread.sleep(2000);

        Assert.assertEquals(chromeDriver.getCurrentUrl(),"https://shop-trinitytrailer-stage-ecd7ecceb7hqb9c2.westus-01.azurewebsites.net/myaccount");
   Thread.sleep(2000);
    }
@Test(priority = 3)
    public void userAddressCountAndAdd() throws InterruptedException {
    WebElement addressListExists = chromeDriver.findElement(By.xpath("//div[@class=\"address-card-list\"]//div[@class=\"adddress-card\"]"));
    if(addressListExists.isDisplayed()){
        List<WebElement> addressListCount = chromeDriver.findElements(By.xpath("//div[@class=\"address-card-list\"]//div[@class=\"adddress-card\"]"));
    int addCount = addressListCount.size();
        System.out.println(addCount);
    }
    chromeDriver.findElement(By.xpath("//div[@class=\"left-menu-sec\"]/ul/li/a[text()=\" My Profile \"]")).click();
    chromeDriver.findElement(By.xpath("//div[@class=\"address-sec\"]/div/span/i[@class=\"fa fa-plus\"]")).click();
    Thread.sleep(2000);

    Actions actions = new Actions(chromeDriver);
    WebElement firstName=chromeDriver.findElement(By.xpath("//div[@class=\"modal-body\"]/descendant::input[@placeholder=\"Enter First Name\"]"));
    actions.sendKeys(firstName,"Neeru").perform();
    WebElement lastName = chromeDriver.findElement(By.xpath("//div[@class=\"modal-body\"]/descendant::input[@placeholder=\"Enter Last Name\"]"));
    actions.sendKeys(lastName,"Pareek").perform();
    WebElement zipCode = chromeDriver.findElement(By.xpath("//div/input[@placeholder=\"Enter Zip Code\"]"));
    actions.sendKeys(zipCode,"95015").perform();
    Thread.sleep(2000);
    WebElement add = chromeDriver.findElement(By.xpath("//div/input[@placeholder=\"Enter Address Line 1\"]"));
    actions.sendKeys(add,"Address 1").perform();
    Thread.sleep(8000);
    WebElement ph =chromeDriver.findElement(By.xpath("//div[@class=\"modal-body\"]/descendant::input[@placeholder=\"Enter Phone Number\"]"));
    actions.sendKeys(ph,"9998887778").perform();
    Thread.sleep(2000);
    WebElement save = chromeDriver.findElement(By.xpath("//div[@class=\"modal-body\"]/descendant::button[text()=\" Save \"]"));
    actions.moveToElement(save).click().perform();
    Thread.sleep(3000);
    }

     @Test(priority = 4)
    public void delFirstAddress() throws InterruptedException {
         chromeDriver.findElement(By.xpath("//div[@class=\"address-card-list\"]//div[@class=\"adddress-card\"][1]/div[@class=\"action-icon\"]/i[@class=\"fa fa-trash\"]")).click();
         Actions actions = new Actions(chromeDriver);
         WebElement accept = chromeDriver.findElement(By.xpath("//button[text()=\"Yes\"]"));
         actions.moveToElement(accept).click().build().perform();

         Thread.sleep(2000);
    }

    @Test(priority = 5)
    public void tearDown(){
        chromeDriver.quit();
    }
}
