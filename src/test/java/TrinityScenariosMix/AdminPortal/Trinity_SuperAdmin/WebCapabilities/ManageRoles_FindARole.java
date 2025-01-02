package TrinityScenariosMix.AdminPortal.Trinity_SuperAdmin.WebCapabilities;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
//Not Working after first click of 'Next' button
public class ManageRoles_FindARole {
    WebDriver driver;
    @BeforeTest
    public void NavigateToManageRoles(){
        ChromeOptions c = new ChromeOptions();
        c.addArguments("--start-maximized");
        //c.addArguments("--headless");
        c.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        c.addArguments("--incognito");

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
        driver.findElement(By.xpath("//span[text()='User Management']")).click();
        driver.findElement(By.xpath("//span[text()='Manage Roles']")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://atlas-web-qa.azurewebsites.net/role");
    }

    @Test
    public void findARoleName() throws InterruptedException {
        String role_name = "ABCDE";
        Select s = new Select(driver.findElement(By.xpath("//select[@name='DataTables_Table_0_length']")));
        s.selectByValue("10");

         int pages = driver.findElements(By.xpath("//div[@id='DataTables_Table_0_paginate']/span/a")).size();
        System.out.println(pages);
         WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
         w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/a[@id='DataTables_Table_0_next']")));

         WebElement nextbutton = driver.findElement(By.xpath("//div/a[@id='DataTables_Table_0_next']"));
        boolean b = false;
         for(int i=1;i<=pages;i++){

             int rows = driver.findElements(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr")).size();
             System.out.println(rows);
             for(int j =1; j<=rows; j++) {
                 System.out.println(driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr[" + j + "]/td[2]")).getText());

                 if (driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr[" + j + "]/td[2]")).getText().equals(role_name)) {
                     System.out.println(driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr[" + j + "]/td[1]")).getText());
                     b=true;
                     break;
                 }
             }
             if (b) break;
             else{
                 w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/a[@id='DataTables_Table_0_next']")));
                 Thread.sleep(1000);
                 WebElement nextbutton1 = driver.findElement(By.xpath("//div/a[@id='DataTables_Table_0_next']"));
                 nextbutton1.click();}
             }
         if(!b){
             System.out.println("Role not found");
         }
         }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
    }

