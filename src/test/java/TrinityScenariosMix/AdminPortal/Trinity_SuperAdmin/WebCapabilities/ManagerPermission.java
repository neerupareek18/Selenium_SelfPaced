package TrinityScenariosMix.AdminPortal.Trinity_SuperAdmin.WebCapabilities;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class ManagerPermission {
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

        driver.findElement(By.xpath("//a[text()='Web Capabilities ']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[@title='Manage Permissions']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[@title='Role/User Privileges']")).click();

        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("ABCD");

        System.out.println(driver.findElement(By.xpath("//input[@type='text']")).getText());

        if(driver.findElement(By.xpath("//div[@class='ng-option ng-option-disabled']")).isDisplayed()){
            System.out.println("Role is not available to set permission.");
        }

        driver.findElement(By.xpath("//input[@type='text']")).clear();
        driver.findElement(By.xpath("//span[@class='ng-arrow-wrapper']")).click();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("TSM");
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Keys.RETURN);
        Thread.sleep(1000);

//        List <WebElement> items1 = driver.findElements(By.xpath("//div[@class='ng-option']/span"));
//        System.out.println(items1.size());
//        for(WebElement i:items1){
//            if(i.getText().equalsIgnoreCase("TSM")){
//                i.click();
//                System.out.println("Role is selected");
//            }
//        }

        boolean clicked = false;
        List<WebElement> items = driver.findElements(By.xpath("//input[@type='checkbox']/following-sibling::label"));
        System.out.println(items.size());
        for(WebElement i:items){
            System.out.println(i.getText());
            if(i.getText().equals("Web Capabilities")){
                if(!(driver.findElement(By.xpath("//div/label[text()=' Web Capabilities ']/preceding-sibling::input"))).isSelected()){
                    driver.findElement(By.xpath("//div/label[text()=' Web Capabilities ']/preceding-sibling::input")).click();
                    clicked=true;

                }
            }
        }
List <WebElement> buttons = driver.findElements(By.xpath("//button[@class='btn btn-primary']"));
        for(WebElement b : buttons){
            if(b.getText().equalsIgnoreCase("Update Changes")) {
                System.out.println(b.getText());
                b.click();
                break;
            }
        }
        if(clicked){
            w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/i[@class='fa fa-check-circle']")));
            System.out.println(driver.findElement(By.xpath("//div/i[@class='fa fa-check-circle']")).getText());
            Assert.assertTrue(driver.findElement(By.xpath("//div/i[@class='fa fa-check-circle']")).isDisplayed());
        }
        else{
            w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/i[@class='fa fa-exclamation-circle']")));
            System.out.println(driver.findElement(By.xpath("//div/i[@class='fa fa-exclamation-circle']")).getText());
            Assert.assertTrue(driver.findElement(By.xpath("//div/i[@class='fa fa-exclamation-circle']")).isDisplayed());
        }
    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}
