package SeleniumPractice.Trinity_OuterPages;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Trinity_SignInPage_Positive {
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

    //Types of ways the xpath is provided
  /*
//*[@id=""] --> find all the tags with the id = ...
 //input[@id=""] --> find all the input with the id = ...  ---better performance
*/
        /* Xpath functions ---
        //a[contains(@id, "Value")]
        //a[starts-with(@id, "Value")]
        //a[normalize-space()="Value"]   --- removing the leading and trailing spaces
        //a[@id="Value" and @text="Value"]
        //a[@id="Value" or @text="Value"]
         */

        //Always try to find small and efficient locator

        /*
Xpath Axes ---
//div[@class=""]/parent::div
//div[@class=""]/child::div
//div[@class=""]/anscestor::div
//div[@class=""]/self::div
//div[@class=""]/descendant-or-self::div
//div[@class=""]/descendant::div
//div[@class=""]/following-sibling::div
//div[@class=""]/preceding::div
     */

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
    System.out.println(password.getAttribute("type"));
    Assert.assertEquals(password.getAttribute("type"),"password");

    driver.findElement(By.xpath("//div/i[@class='fa fa-eye']")).click();
    System.out.println(password.getAttribute("type"));
        Assert.assertEquals(password.getAttribute("type"),"text");

    signInButton.click();

    Thread.sleep(3000);

    Assert.assertEquals(driver.getCurrentUrl(),"https://atlas-web-qa.azurewebsites.net/dashboard");

    WebElement name = driver.findElement(By.xpath("//*[@id=\"navbarDropdown\"]/span/p"));
    Assert.assertEquals(name.getText(),"Naveen 1111");

//    WebElement module1 = driver.findElement(By.xpath("//a[text()='Web Capabilities']"));
//    Assert.assertEquals(module1.getText(),"Web Capabilites");

    driver.quit();
}
}
