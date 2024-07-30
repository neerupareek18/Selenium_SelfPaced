import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login_Positive_Trinity {
    @Test
    public void Login_ValidCredentials() throws InterruptedException {

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

        //Correct Username and Password
    email.sendKeys("Naveen1@yopmail.com");
    password.sendKeys("Admin@123");
    signInButton.click();

    Thread.sleep(3000);

    Assert.assertEquals(driver.getCurrentUrl(),"https://atlas-web-qa.azurewebsites.net/dashboard");

    driver.quit();
}
}
