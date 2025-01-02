package SeleniumPractice.Trinity_SuperAdmin.WebCapabilities;

import org.openqa.selenium.*;
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
@Test
public class ManageRoles_AddNewRole {
    WebDriver driver;
    boolean record_exist=false;
    String role_name;
    @BeforeTest
    public void NavigateToManageRoles() throws InterruptedException {
        ChromeOptions c = new ChromeOptions();
        c.addArguments("--start-maximized");
        //c.addArguments("--headless");
        c.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        driver = new ChromeDriver(c);
        driver.get("https://atlas-web-qa.azurewebsites.net/");
        WebElement email = driver.findElement(By.xpath("//input[@formcontrolname=\"email\"]"));
        WebElement password = driver.findElement(By.xpath("//input[@formcontrolname=\"password\"]"));
    /*
    cssSelector
    By.cssSelector("input[type='submit']")
    cssSelector("#idName")
    cssSelector(".className")
    cssSelector(contains(type,'submit'))
     */

        WebElement signInButton = driver.findElement(By.cssSelector("[type='submit']"));

        //Correct Username and Password
        email.sendKeys("Naveen1@yopmail.com");
        password.sendKeys("Admin@123");
        signInButton.click();

        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]")));
WebElement wc = driver.findElement(By.xpath("//a[text()='Web Capabilities ']"));
WebElement um = driver.findElement(By.xpath("//span[text()='User Management']"));
Thread.sleep(1000);
WebElement mr = driver.findElement(By.xpath("//span[text()='Manage Roles']"));
//
//        w.until(ExpectedConditions.elementSelectionStateToBe(wc,true));
//        w.until(ExpectedConditions.elementSelectionStateToBe(um,false));
//        w.until(ExpectedConditions.elementSelectionStateToBe(mr,false));

        driver.findElement(By.xpath("//a[text()='Web Capabilities ']")).click();
        driver.findElement(By.xpath("//span[text()='User Management']")).click();

        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[text()='Manage Roles']")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://atlas-web-qa.azurewebsites.net/role");
    }

    @Test
    public void AddNewRoleForEmployee() throws InterruptedException {
        role_name = "TestRole123456";
        WebDriverWait w4 = new WebDriverWait(driver, Duration.ofSeconds(5));
        w4.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='search']")));

        driver.findElement(By.xpath("//input[@type='search']")).sendKeys(role_name);


        if(!(driver.findElement(By.xpath("//*[@id='DataTables_Table_0_wrapper']/table/tbody/tr[1]/td")).getText() == "No matching records found")){
           record_exist = true;}
else{
         Assert.assertEquals(driver.findElement(By.xpath("//*[@id='DataTables_Table_0_wrapper']/table/tbody/tr[1]/td")),"No matching records found");

        }

driver.findElement(By.xpath("//a[@type='button' and text()='Add New']")).click();
Assert.assertEquals(driver.getCurrentUrl(),"https://atlas-web-qa.azurewebsites.net/roleEdit");

driver.findElement(By.xpath("//input[@placeholder='Enter Role Name']")).sendKeys(role_name);

WebElement usertype = driver.findElement(By.xpath("//select[@formcontrolname='userType']"));
Select s = new Select(usertype);
s.selectByValue("Employee");

        WebElement isActive = driver.findElement(By.xpath("//select[@formcontrolname='isActive']"));
        Select s1 = new Select(isActive);
        s1.selectByVisibleText("Active");

        driver.findElement(By.xpath("//button[@name='btnSubmit' and @type='submit']")).click();

        if(!(record_exist)) {
            WebDriverWait w1 = new WebDriverWait(driver, Duration.ofSeconds(5));
            w1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Success']")));

            WebElement successtag = driver.findElement(By.xpath("//p[text()='Success']"));

            Assert.assertEquals(successtag.getText(), "Success");
            Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Role created successfully.']")).getText(), "Role created successfully.");

        }
        WebDriverWait w5 = new WebDriverWait(driver, Duration.ofSeconds(5));
        w5.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='search']")));

        driver.findElement(By.xpath("//input[@type='search']")).sendKeys(role_name);
        w5.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@class='fa fa-trash']")));
            driver.findElement(By.xpath("//i[@class='fa fa-trash']")).click();
        System.out.println("Delete is clicked");
        Thread.sleep(10);
            Alert a = new Alert() {
                @Override
                public void dismiss() {
                }

                @Override
                public void accept() {
                    driver.findElement(By.xpath("//button[text()='Yes, Delete']")).click();
                }

                @Override
                public String getText() {
                    return "";
                }

                @Override
                public void sendKeys(String keysToSend) {

                }
            };
            a.accept();

            WebDriverWait w3 = new WebDriverWait(driver, Duration.ofSeconds(5));
            w3.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Success']")));

            Thread.sleep(10);
            Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Success']")).isDisplayed());
        System.out.println("Success is there");

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Role deleted successfully.']")).getText().equalsIgnoreCase("")==false);

        }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}
