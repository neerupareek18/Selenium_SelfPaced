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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ManageRoles_FindARole {
    WebDriver driver;
    boolean record_exist=false;
    String role_name;
    @BeforeTest
    public void NavigateToManageRoles(){
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
        WebElement mr = driver.findElement(By.xpath("//span[text()='Manage Roles']"));
//
//        w.until(ExpectedConditions.elementSelectionStateToBe(wc,true));
//        w.until(ExpectedConditions.elementSelectionStateToBe(um,false));
//        w.until(ExpectedConditions.elementSelectionStateToBe(mr,false));

        driver.findElement(By.xpath("//a[text()='Web Capabilities ']")).click();
        driver.findElement(By.xpath("//span[text()='User Management']")).click();
        driver.findElement(By.xpath("//span[text()='Manage Roles']")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://atlas-web-qa.azurewebsites.net/role");
    }

    @Test
    public void TraverseRoleTable() {
//        role_name = "TestRole11";
//        WebDriverWait w4 = new WebDriverWait(driver, Duration.ofSeconds(5));
//        w4.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='search']")));
//
//        driver.findElement(By.xpath("//input[@type='search']")).sendKeys(role_name);
        List <WebElement> header = driver.findElements(By.xpath("//table[@id='DataTables_Table_0']/thead/tr"));
        int columns = driver.findElements(By.xpath("//table[@id='DataTables_Table_0']/thead/tr[1]/th")).size();
        int index=0;
        for(int i=1;i<=columns;i++){
            index++;
            System.out.println(driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/thead/tr[1]/th["+i+"]")).getText());
            if(driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/thead/tr[1]/th["+i+"]")).getText().equals("Name")){
                break;
            }
        }


        for(WebElement header1:header) {
            System.out.println(header1.getText());
        }

        int rows = driver.findElements(By.xpath("//table[starts-with(@id,'DataTables_Table')]/descendant::tbody/tr")).size();
        int columns1 = driver.findElements(By.xpath("//table[starts-with(@id,'DataTables_Table')]/descendant::tbody/tr[1]/td")).size();
//
        for(int i=1;i<=rows;i++){
            System.out.println(driver.findElement(By.xpath("//table[starts-with(@id,'DataTables_Table')]/descendant::tbody/tr["+i+"]/td["+index+"]")).getText());
        }

//        if(!(driver.findElement(By.xpath("//*[@id='DataTables_Table_0_wrapper']/table/tbody/tr[1]/td")).getText() == "No matching records found")){
//            record_exist = true;
//        }
//        else{
//            Assert.assertEquals(driver.findElement(By.xpath("//*[@id='DataTables_Table_0_wrapper']/table/tbody/tr[1]/td")),"No matching records found");
//
//        }

    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}
