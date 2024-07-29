import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login {
    @Test
    public void LoginNoUsername(){}

    @Test
    public void LoginNoPassword(){}

    @Test
    public void LoginNoUsernameAndPassword(){
        WebDriver driver=new ChromeDriver();
        driver.get("https://atlas-web-qa.azurewebsites.net/");
        driver.manage().window().maximize();

        WebElement signInButton = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div/div[2]/div/form/button[1]"));
        WebElement username_error = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div/div[2]/div/form/div[1]/small"));
        WebElement passwrod_error = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div/div[2]/div/form/div[2]/small"));
        signInButton.click();

        Assert.assertEquals(username_error.getText(), "*Email Address/Username is required");
        Assert.assertEquals(passwrod_error.getText(), "*Password is required");

        driver.quit();

    }

    @Test
    public void LoginInvalidUsername(){}

    @Test
    public void LoginInvalidPassword(){}

    @Test
    public void LoginValidCredentials() throws InterruptedException {

    ChromeOptions c = new ChromeOptions();
    //By Default, the page loader is Normal - wait for all the elements to load
    // There are EAGER and NONE also
    c.setPageLoadStrategy(PageLoadStrategy.NORMAL);

    //To maximize using options
    c.addArguments("--start-maximized");

   //Create session via API and Session ID is generated
    WebDriver driver=new ChromeDriver();

    //Launch the Browser with the URL mentioned
    driver.get("https://atlas-web-qa.azurewebsites.net/");

    //maximize using driver
    driver.manage().window().maximize();

    WebElement email = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div/div[2]/div/form/div[1]/div/input"));
    WebElement password = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div/div[2]/div/form/div[2]/div/input"));
    WebElement signInButton = driver.findElement(By.cssSelector("[type='submit']"));

    email.sendKeys("Naveen1@yopmail.com");
    password.sendKeys("Admin@123");
    signInButton.click();

    Thread.sleep(3000);

    Assert.assertEquals(driver.getCurrentUrl(),"https://atlas-web-qa.azurewebsites.net/dashboard");

    driver.quit();
}
}
