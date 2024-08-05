package SeleniumPractice.Trinity_OuterPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Trinity_SignInPage_Negative {
    //id->name->classname->tag->css Selector->xpath

    @Test
    public void LoginNoUsernamePassword(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://atlas-web-qa.azurewebsites.net/");
        WebElement signInButton = driver.findElement(By.cssSelector("[type='submit']"));
        signInButton.click();

        List<WebElement> error = driver.findElements(By.className("text-danger"));
        for(WebElement e : error){
            System.out.println(e.getText());
        }

        for(int i =0; i< error.size();i++){
            System.out.println(error.get(i).getText());
        }

        Assert.assertEquals(error.get(0).getText(),"*Email Address/Username is required");
        Assert.assertEquals(error.get(1).getText(),"*Password is required");

        driver.quit();
    }

    @Test
    public void LoginNoUsername(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://atlas-web-qa.azurewebsites.net/");
        driver.manage().window().maximize();

        WebElement signInButton = driver.findElement(By.cssSelector("[type='submit']"));
        WebElement password = driver.findElement(By.cssSelector("[formcontrolname='password']"));

        password.sendKeys("Admin@123");
        signInButton.click();

        List<WebElement> error = driver.findElements(By.className("text-danger"));

        Assert.assertEquals(error.get(0).getText(),"*Email Address/Username is required");

        driver.quit();
    }

    @Test
    public void LoginNoPassword(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://atlas-web-qa.azurewebsites.net/");
        driver.manage().window().maximize();

        WebElement signInButton = driver.findElement(By.cssSelector("[type='submit']"));
        WebElement username = driver.findElement(By.cssSelector("[formcontrolname='email']"));

        username.sendKeys("naveen1@yopmail.com");
        signInButton.click();

        List<WebElement> error = driver.findElements(By.className("text-danger"));

        Assert.assertEquals(error.get(0).getText(),"*Password is required");

        driver.quit();
    }

    @Test
    public void LoginIncorrectUsername() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://atlas-web-qa.azurewebsites.net/");
        driver.manage().window().maximize();

        WebElement signInButton = driver.findElement(By.cssSelector("[type='submit']"));
        WebElement username = driver.findElement(By.cssSelector("[formcontrolname='email']"));
        WebElement password = driver.findElement(By.cssSelector("[formcontrolname='password']"));

        username.sendKeys("naveen1@yopmail.com");
        password.sendKeys("Admi123");
        signInButton.click();

        Thread.sleep(2000);

       //WebElement errortag = driver.findElement(By.xpath("//p[text()='Incorrect password.']"));
        WebElement errormessage = driver.findElement(By.xpath("//p[text()='Incorrect password.']"));

        //Assert.assertEquals(errortag.getText(),"ERROR");
        Assert.assertEquals(errormessage.getText(),"Incorrect password.");
        driver.quit();
    }



}
