import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class LoginPage {

    @Test
    public void LaunchingThePortal()
    {
        ChromeOptions c = new ChromeOptions();
        c.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver=new ChromeDriver();
        driver.get("https://atlas-web-qa.azurewebsites.net/");
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        driver.close();
    }

}
