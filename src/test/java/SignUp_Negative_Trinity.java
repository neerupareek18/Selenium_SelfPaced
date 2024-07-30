import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp_Negative_Trinity {
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

        String email = "abc@YOPMAILCOM";
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


}
