package CustomerPartsCatalog;

import ReadingExcelFile.ReadExcel;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDT_PartOrder {
    WebDriver chromeDriver = new ChromeDriver();
    @Test(priority = 1, dataProvider="getData", dataProviderClass = ReadExcel.class)
    public void partsOrdering(String partName) throws InterruptedException {
        System.out.println(partName);

        chromeDriver.manage().window().maximize();
        chromeDriver.get("https://shop-trinitytrailer-stage-ecd7ecceb7hqb9c2.westus-01.azurewebsites.net/part-catalog");
        Thread.sleep(5000);

        chromeDriver.findElement(By.xpath("//input[@placeholder=\"Search Here\"]")).clear();
        chromeDriver.findElement(By.xpath("//input[@placeholder=\"Search Here\"]")).sendKeys(partName);
        chromeDriver.findElement(By.xpath("//input[@placeholder=\"Search Here\"]")).sendKeys(Keys.RETURN);
        Thread.sleep(3000);

        if (chromeDriver.getPageSource().contains("No Parts Found")) {
            System.out.println("Part Not Found");
        } else {
            System.out.println("Part Found");
            chromeDriver.findElement(By.xpath("//div/a/span/i[@class='fa fa-heart-o me-2']")).click();
            chromeDriver.findElement(By.xpath("//a/i[@class='fa fa-shopping-cart me-2']")).click();

            chromeDriver.findElement(By.xpath("//li/a[@class='cart']")).click();
            Thread.sleep(3000);
            chromeDriver.findElement(By.xpath("//div/a[@class='process-btn']")).click();

            chromeDriver.findElement(By.xpath("//div/button[@class='login-btn mt-3 btn-primary']")).click();
            Thread.sleep(3000);

            Assert.assertTrue(chromeDriver.getCurrentUrl().contains("https://shop-trinitytrailer-stage-ecd7ecceb7hqb9c2.westus-01.azurewebsites.net/login"));
            chromeDriver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("customer@yopmail.com");
            chromeDriver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Admin@123");
            chromeDriver.findElement(By.xpath("//button[@type='submit']")).click();

            Thread.sleep(5000);

            chromeDriver.findElement(By.xpath("//div/input[@placeholder='Enter Card Number']")).sendKeys("4111-1111-1111-1111");
            chromeDriver.findElement(By.xpath("//div/input[@placeholder='MM/YY']")).sendKeys("1131");
            chromeDriver.findElement(By.xpath("//div/input[@placeholder='CVV']")).sendKeys("1234");
            chromeDriver.findElement(By.xpath("//div/input[@placeholder='Enter Card Holder Name']")).sendKeys("Neeru Pareek");
            Thread.sleep(3000);
            chromeDriver.findElement(By.xpath("//button[@class='place-order-btn']")).click();

            Thread.sleep(8000);

            Assert.assertTrue(chromeDriver.getPageSource().contains("Your order is placed successfully."));
            System.out.println("Part Ordered Successfully!");
        }
    }

    @Test(priority = 2)
        public void closeBrowser(){
            chromeDriver.quit();
        }

    }
