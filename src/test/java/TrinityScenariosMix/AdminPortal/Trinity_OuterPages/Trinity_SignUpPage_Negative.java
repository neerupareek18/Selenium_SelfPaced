package TrinityScenariosMix.AdminPortal.Trinity_OuterPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Trinity_SignUpPage_Negative {
    @Test
    public void SignUp_NoMandatoryField(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://atlas-web-qa.azurewebsites.net/");

        WebElement singUpLink = driver.findElement(By.linkText("Sign Up"));
        singUpLink.click();

        Assert.assertEquals(driver.getCurrentUrl(),"https://atlas-web-qa.azurewebsites.net/signup");
    WebElement signUpButton = driver.findElement(By.cssSelector("[type='submit']"));

    signUpButton.click();

        List<WebElement> error = driver.findElements(By.className("text-danger"));

        Assert.assertEquals(error.get(0).getText(),"*First Name is required");
        Assert.assertEquals(error.get(1).getText(),"*Email Address is required");
        Assert.assertEquals(error.get(2).getText(),"*Password is required");

        driver.quit();
    }

    @Test
    public void SignUp_InvalidEmail(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://atlas-web-qa.azurewebsites.net/");

        WebElement singUpLink = driver.findElement(By.linkText("Sign Up"));
        singUpLink.click();

        String email = "abc98@@YOPMAIL98.com";
        Pattern p = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,15}$");
        Matcher m = p.matcher(email);
        boolean b = m.matches();

        WebElement emailTextBox = driver.findElement(By.cssSelector("[formcontrolname='email']"));
        emailTextBox.sendKeys(email);

        if(!b){
            List<WebElement> error = driver.findElements(By.className("text-danger"));
            System.out.println(error.get(0).getText());
            Assert.assertEquals(error.get(0).getText(),"*Enter valid Email Address");
        }

        driver.quit();

    }
@Test
    public void SignUpPasswordNotMatching(){
        ChromeOptions c = new ChromeOptions();
        c.addArguments("--headless");

        WebDriver driver = new ChromeDriver();
        driver.get("https://atlas-web-qa.azurewebsites.net/");

        WebElement singUpLink = driver.findElement(By.linkText("Sign Up"));
        singUpLink.click();

        WebElement password = driver.findElement(By.cssSelector("[formcontrolname='password']"));

        WebElement confirm_password = driver.findElement(By.cssSelector("[formcontrolname='confirmpassword']"));

        password.sendKeys("Admin@123");
        confirm_password.sendKeys("Admin");

        List <WebElement> error = driver.findElements(By.className("text-danger"));
        Assert.assertEquals(error.get(0).getText(), "*Password doesn't match");
        driver.quit();
    }

    @Test
    public void SignUpInvalidPassword(){
        ChromeOptions c = new ChromeOptions();
        c.addArguments("--headless");

        WebDriver driver = new ChromeDriver();
        driver.get("https://atlas-web-qa.azurewebsites.net/");
        driver.manage().window().maximize();

        WebElement singUpLink = driver.findElement(By.linkText("Sign Up"));
        singUpLink.click();

        WebElement password = driver.findElement(By.cssSelector("[formcontrolname='password']"));
        String input_password = "Admin@@#^";
        password.sendKeys(input_password);

       // Pattern p = Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{9,})$");
        Pattern p = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{9,}$");

        Matcher m = p.matcher(input_password);
        boolean b = m.matches();

        if(!b) {
            List<WebElement> error = driver.findElements(By.className("text-danger"));
            System.out.println(error.get(0).getText());
            Assert.assertEquals(error.get(0).getText(), "*Password must contain more than 8 characters, atleast 1 upper, lower case letter, 1 number and 1 special character");
        }
        driver.quit();


    }


}
