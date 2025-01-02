package TrinityScenariosMix.AdminPortal.Trinity_OuterPages;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Trinity_SignInPageElements {
    WebDriver driver;
    @BeforeTest
    public void openBrowser(){
        ChromeOptions c = new ChromeOptions();
        c.addArguments("--start-maximized");
        c.addArguments("--headless");
        c.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        driver = new ChromeDriver(c);
    }

    @Test
    public void ElementsOnSignInPage() {
        driver.get("https://atlas-web-qa.azurewebsites.net/");

        WebElement h1 = driver.findElement(By.xpath("//h1[text()=\"Easy to do business with.\"]"));
        WebElement h2 = driver.findElement(By.xpath("//h1[text()=\"The complete solution.\"]"));
        WebElement h3 = driver.findElement(By.xpath("//h1[text()=\" Built to last.\"]"));

        Assert.assertTrue(h1.isDisplayed());
        Assert.assertTrue(h2.isDisplayed());
        Assert.assertTrue(h3.isDisplayed());

        //Logo Image locator
        Assert.assertTrue(driver.findElement(By.xpath("//div/a/img[@src='assets/images/TrinityLogo.png']")).isDisplayed());
        //Text of the image
        //Assert.assertEquals(logo.getText(), "TRINITY");

        WebElement emailtitle = driver.findElement(By.xpath("//label[text()=\"Email Address/Username\"]"));
        WebElement passwordtitle = driver.findElement(By.xpath("//label[text()=\"Password\"]"));

        WebElement emailplaceholder = driver.findElement(By.xpath("//input[@placeholder=\"Enter Email Address/Username\"]"));
        WebElement passwordplaceholder = driver.findElement(By.xpath("//input[@placeholder=\"Enter Password\"]"));

        WebElement signInButton = driver.findElement(By.cssSelector("[type='submit']"));

        WebElement forgotpassword = driver.findElement(By.xpath("//a[text()=\"Forgot Password?\"]"));

        Assert.assertTrue(emailtitle.isDisplayed());
        Assert.assertTrue(passwordtitle.isDisplayed());
        Assert.assertTrue(emailplaceholder.isDisplayed());
        Assert.assertTrue(passwordplaceholder.isDisplayed());

        Assert.assertTrue(signInButton.isDisplayed());
        Assert.assertTrue(signInButton.isEnabled());

        Assert.assertTrue(forgotpassword.isDisplayed());
        Assert.assertTrue(forgotpassword.isEnabled());

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='signup-password text-center']")).getText(),"Don't have an account? Sign Up");

        WebElement SignUplink = driver.findElement(By.linkText("Sign Up"));

        Assert.assertTrue(SignUplink.isDisplayed());
        Assert.assertTrue(SignUplink.isEnabled());

//Locator for Don't have an account
    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }

}
