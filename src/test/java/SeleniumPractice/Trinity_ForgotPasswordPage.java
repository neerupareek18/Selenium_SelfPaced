package SeleniumPractice;

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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Trinity_ForgotPasswordPage {
    WebDriver driver;
    @BeforeTest
    public void openBrowser(){
        ChromeOptions c = new ChromeOptions();
        c.addArguments("--start-maximized");
        //c.addArguments("--headless");
        c.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        driver = new ChromeDriver(c);
    }

    @Test
    public void ForgotPasswordElements() {
        driver.get("https://atlas-web-qa.azurewebsites.net/");
        WebElement forgotpassword = driver.findElement(By.xpath("//a[text()=\"Forgot Password?\"]"));
        WebElement SignUplink = driver.findElement(By.linkText("Sign Up"));

        forgotpassword.click();
        System.out.println(driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(),"https://atlas-web-qa.azurewebsites.net/forgotpaswword");

        WebElement email = driver.findElement(By.xpath("//label[text()=\"Email Address\"]"));
        Assert.assertTrue(email.isDisplayed());

        WebElement emailplaceholder = driver.findElement(By.cssSelector("[placeholder='Enter your Email Address']"));
        Assert.assertTrue(emailplaceholder.isDisplayed());

        WebElement submitButton = driver.findElement(By.xpath("//button[text()=\" Send the Email to Reset the Password \"]"));
        Assert.assertTrue(submitButton.isDisplayed());
        Assert.assertTrue(submitButton.isEnabled());

        WebElement backtologin = driver.findElement(By.linkText("Back to Login"));
        Assert.assertTrue(backtologin.isDisplayed());

    }

    @Test
    public void InvalidEmailAddress(){
        driver.get("https://atlas-web-qa.azurewebsites.net/");
        WebElement forgotpassword = driver.findElement(By.xpath("//a[text()=\"Forgot Password?\"]"));
        forgotpassword.click();

        WebElement emailplaceholder = driver.findElement(By.cssSelector("[placeholder='Enter your Email Address']"));
        String emailaddress = "abcd";
        emailplaceholder.sendKeys(emailaddress);
        Pattern p = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,15}$");
        Matcher m = p.matcher(emailaddress);
        boolean b = m.matches();

        if(!b){
            WebElement error = driver.findElement(By.className("text-danger"));
            Assert.assertEquals(error.getText(), "*Enter valid Email Address");
        }
    }

    @Test
    public void NonExistingEmailAddress() throws InterruptedException {
        driver.get("https://atlas-web-qa.azurewebsites.net/");
        WebElement forgotpassword = driver.findElement(By.xpath("//a[text()=\"Forgot Password?\"]"));
        forgotpassword.click();

        WebElement emailplaceholder = driver.findElement(By.cssSelector("[placeholder='Enter your Email Address']"));
        String emailaddress = "abcd@yopmail.com";
        emailplaceholder.sendKeys(emailaddress);
        WebElement submitButton = driver.findElement(By.xpath("//button[text()=\" Send the Email to Reset the Password \"]"));
        submitButton.click();

        Thread.sleep(2000);

        WebElement errortag = driver.findElement(By.xpath("//p[text()=\"ERROR\"]"));
        WebElement errormessage= driver.findElement(By.xpath("//p[text()=\"User does not exists\"]"));

        Assert.assertEquals(errortag.getText(),"ERROR");
        Assert.assertEquals(errormessage.getText(),"User does not exists");

    }

    @Test
    public void ExistingEmailAddress() throws InterruptedException{
        driver.get("https://atlas-web-qa.azurewebsites.net/");
        WebElement forgotpassword = driver.findElement(By.xpath("//a[text()=\"Forgot Password?\"]"));
        forgotpassword.click();

        WebElement emailplaceholder = driver.findElement(By.cssSelector("[placeholder='Enter your Email Address']"));
        String emailaddress = "naveen1@yopmail.com";
        emailplaceholder.sendKeys(emailaddress);
        WebElement submitButton = driver.findElement(By.xpath("//button[text()=\" Send the Email to Reset the Password \"]"));
        submitButton.click();

        Thread.sleep(5000);

        WebElement successtag = driver.findElement(By.xpath("//p[text()=\"Success\"]"));
        WebElement successmessage= driver.findElement(By.xpath("//p[text()=\"Email sent. Please check the inbox to reset the password\"]"));

        Assert.assertEquals(successtag.getText(),"Success");
        Assert.assertEquals(successmessage.getText(),"Email sent. Please check the inbox to reset the password");

        WebElement backtologin = driver.findElement(By.linkText("Back to Login"));
        backtologin.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://atlas-web-qa.azurewebsites.net/login");

    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}
