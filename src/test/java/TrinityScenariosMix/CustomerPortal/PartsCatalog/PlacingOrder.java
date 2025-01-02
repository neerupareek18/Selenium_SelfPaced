package TrinityScenariosMix.CustomerPortal.PartsCatalog;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlacingOrder {

    WebDriver chromeDriver = new ChromeDriver();
    Boolean partFoundCheck=false;

    @Test(priority = 1)
    public void navigatingToSite() throws InterruptedException {
        chromeDriver.manage().window().maximize();
        chromeDriver.get("https://shop-trinitytrailer-stage-ecd7ecceb7hqb9c2.westus-01.azurewebsites.net/part-catalog");
        Thread.sleep(5000);
    }

    @Test(priority = 2)
    public void invalidPart() throws InterruptedException {
      chromeDriver.findElement(By.xpath("//input[@placeholder=\"Search Here\"]")).sendKeys("ABCD");
      chromeDriver.findElement(By.xpath("//input[@placeholder=\"Search Here\"]")).sendKeys(Keys.RETURN);
      Thread.sleep(3000);

    if(chromeDriver.getPageSource().contains("No Parts Found")){
        System.out.println("Part Not Found");
}
    }

    @Test(priority = 3)
    public void validPartToSaveItemAndCart() throws InterruptedException {

        chromeDriver.findElement(By.xpath("//input[@placeholder=\"Search Here\"]")).clear();
        chromeDriver.findElement(By.xpath("//input[@placeholder=\"Search Here\"]")).sendKeys("TARP,ROLL OVER,40'X96\",ALFC,OTH RC");
        chromeDriver.findElement(By.xpath("//input[@placeholder=\"Search Here\"]")).sendKeys(Keys.RETURN);
        Thread.sleep(3000);

        if(chromeDriver.getPageSource().contains("No Parts Found")){
            System.out.println("Part Not Found");
            chromeDriver.quit();
        }
        else{
            partFoundCheck=true;
            chromeDriver.findElement(By.xpath("//div/a/span/i[@class='fa fa-heart-o me-2']")).click();
            chromeDriver.findElement(By.xpath("//a/i[@class='fa fa-shopping-cart me-2']")).click();
        }
    }

    @Test(priority = 4)
    public void checkingCountExistsForSaveItemAndCart() {
        if (partFoundCheck) {
            if (chromeDriver.findElement(By.xpath("//a/span[@class='wishlist-number']")).isDisplayed()) {
                System.out.println("Saved Items Count --");
                System.out.println(chromeDriver.findElement(By.xpath("//a/span[@class='wishlist-number']")).getText());
            }
            if (chromeDriver.findElement(By.xpath("//a/span[@class='number-count']")).isDisplayed()) {
                System.out.println("Cart Items Count");
                System.out.println(chromeDriver.findElement(By.xpath("//a/span[@class='wishlist-number']")).getText());
            }

        }
    }
    @Test(priority = 5)
public void navigateToCartPage() throws InterruptedException {
        if(partFoundCheck){
            chromeDriver.findElement(By.xpath("//li/a[@class='cart']")).click();
            Thread.sleep(3000);
            chromeDriver.findElement(By.xpath("//div/a[@class='process-btn']")).click();
        }
}

@Test(priority = 6)
public void singInAsCustomer() throws InterruptedException {
        chromeDriver.findElement(By.xpath("//div/button[@class='login-btn mt-3 btn-primary']")).click();
        Thread.sleep(3000);

        Assert.assertTrue(chromeDriver.getCurrentUrl().contains("https://shop-trinitytrailer-stage-ecd7ecceb7hqb9c2.westus-01.azurewebsites.net/login"));
    chromeDriver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("customer@yopmail.com");
    chromeDriver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Admin@123");
    chromeDriver.findElement(By.xpath("//button[@type='submit']")).click();

    Thread.sleep(5000);

}

@Test(priority = 7)
public void failedOrder() throws InterruptedException {
        chromeDriver.findElement(By.xpath("//div/input[@placeholder='Enter Card Number']")).sendKeys("4102-0000-0000-0000");
        chromeDriver.findElement(By.xpath("//div/input[@placeholder='MM/YY']")).sendKeys("1131");
        chromeDriver.findElement(By.xpath("//div/input[@placeholder='CVV']")).sendKeys("1234");
        chromeDriver.findElement(By.xpath("//div/input[@placeholder='Enter Card Holder Name']")).sendKeys("Neeru Pareek");
        chromeDriver.findElement(By.xpath("//button[@class='place-order-btn']")).click();

        Thread.sleep(8000);

        Assert.assertTrue(chromeDriver.getPageSource().contains("Sorry, Your Payment Failed !"));
}

@Test(priority = 8)
    public void successfulOrder() throws InterruptedException {
        chromeDriver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();
        Thread.sleep(3000);

    chromeDriver.findElement(By.xpath("//div/input[@placeholder='Enter Card Number']")).sendKeys("4111-1111-1111-1111");
    chromeDriver.findElement(By.xpath("//div/input[@placeholder='MM/YY']")).sendKeys("1131");
    chromeDriver.findElement(By.xpath("//div/input[@placeholder='CVV']")).sendKeys("1234");
    chromeDriver.findElement(By.xpath("//div/input[@placeholder='Enter Card Holder Name']")).sendKeys("Neeru Pareek");
    chromeDriver.findElement(By.xpath("//button[@class='place-order-btn']")).click();

    Thread.sleep(8000);

    Assert.assertTrue(chromeDriver.getPageSource().contains("Your order is placed successfully."));
    chromeDriver.quit();
    }

}

