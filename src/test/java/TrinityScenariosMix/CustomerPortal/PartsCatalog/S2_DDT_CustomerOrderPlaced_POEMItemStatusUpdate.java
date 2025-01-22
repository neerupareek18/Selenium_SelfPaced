package TrinityScenariosMix.CustomerPortal.PartsCatalog;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.ReadExcel;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class S2_DDT_CustomerOrderPlaced_POEMItemStatusUpdate {
    WebDriver chromeDriver = new ChromeDriver();
    String orderId;

    @Test(priority = 1)
    public void openURL() throws InterruptedException {
        chromeDriver.manage().window().maximize();
        chromeDriver.get("https://shop-trinitytrailer-stage-ecd7ecceb7hqb9c2.westus-01.azurewebsites.net/part-catalog");
        Thread.sleep(5000);
    }
    @Test(priority = 2, dataProvider="getData", dataProviderClass = ReadExcel.class)
    public void partsOrdering_getTransactionId_OrderId(String partName) throws InterruptedException {
        System.out.println(partName);

        chromeDriver.findElement(By.xpath("//input[@placeholder=\"Search Here\"]")).clear();
        chromeDriver.findElement(By.xpath("//input[@placeholder=\"Search Here\"]")).sendKeys(partName);
        chromeDriver.findElement(By.xpath("//input[@placeholder=\"Search Here\"]")).sendKeys(Keys.RETURN);
        Thread.sleep(4000);

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

            Thread.sleep(7000);

            chromeDriver.findElement(By.xpath("//div/input[@placeholder='Enter Card Number']")).sendKeys("4111-1111-1111-1111");
            chromeDriver.findElement(By.xpath("//div/input[@placeholder='MM/YY']")).sendKeys("1131");
            chromeDriver.findElement(By.xpath("//div/input[@placeholder='CVV']")).sendKeys("1234");
            chromeDriver.findElement(By.xpath("//div/input[@placeholder='Enter Card Holder Name']")).sendKeys("Neeru Pareek");
            Thread.sleep(3000);
            chromeDriver.findElement(By.xpath("//button[@class='place-order-btn']")).click();

            Thread.sleep(8000);

            Assert.assertTrue(chromeDriver.getPageSource().contains("Your order is placed successfully."));
            System.out.println("Part Ordered Successfully!");

            chromeDriver.findElement(By.xpath("//div/button[text()=\"View Order\"]")).click();
            Thread.sleep(4000);

            chromeDriver.findElement(By.xpath("//div[@class=\"order-cart-card\"]/descendant::li[1]")).click();
            Thread.sleep(3000);

            orderId = chromeDriver.findElement(By.xpath("//label[text()=\"Order No. \"]/following-sibling::span")).getText().trim();

            System.out.println("Order No.: "+chromeDriver.findElement(By.xpath("//label[text()=\"Order No. \"]/following-sibling::span")).getText());
            System.out.println("Transaction ID: "+chromeDriver.findElement(By.xpath("//label[text()=\"Transaction Id \"]/following-sibling::span")).getText());

        }

    }

    @Test(priority = 3)
    public void openAdminPortalAndUpdateStatus() throws InterruptedException {
        ((JavascriptExecutor) chromeDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(chromeDriver.getWindowHandles());

        chromeDriver.switchTo().window(tabs.get(1));

        chromeDriver.get("https://atlas-web-stage.azurewebsites.net/login");
        chromeDriver.manage().window().maximize();

        chromeDriver.findElement(By.xpath("//input[@placeholder=\"Enter Email Address/Username\"]")).sendKeys("poem1.atlas@gmail.com");
        chromeDriver.findElement(By.xpath("//input[@placeholder=\"Enter Password\"]")).sendKeys("Admin@123");
        chromeDriver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

        Thread.sleep(7000);

        chromeDriver.findElement(By.xpath("//li/a[text()=\"Catalogs \"]")).click();
        Thread.sleep(5000);

        //Not able to expand the css tags for left side menus

        chromeDriver.findElement(By.xpath("//span[@title=\"Parts Catalog\"]")).click();
        Thread.sleep(5000);
        chromeDriver.findElement(By.xpath("//span[@title=\"Customer Orders\"]")).click();
        Thread.sleep(3000);

        chromeDriver.findElement(By.xpath("//input[@type=\"search\"]")).sendKeys(orderId);
        List<WebElement> headers = chromeDriver.findElements(By.xpath("//div[@id=\"DataTables_Table_0_wrapper\"]/table[@id=\"DataTables_Table_0\"]/thead/tr/th"));
        Thread.sleep(5000);
        int count = 0;
        int index = 0;
        for (WebElement headernames : headers) {
            count++;
            if (headernames.getText().contains("Status")) {
                index = count;
                break;
            }
        }
        System.out.println(chromeDriver.findElement(By.xpath("//div[@id=\"DataTables_Table_0_wrapper\"]/table[@id=\"DataTables_Table_0\"]/tbody/tr/td[" + index + "]")).getText());

        chromeDriver.findElement(By.xpath("//div[@id=\"DataTables_Table_0_wrapper\"]/table[@id=\"DataTables_Table_0\"]/tbody/tr/td[8]/i")).click();
        WebElement viewButton = chromeDriver.findElement(By.xpath("//div[@id=\"DataTables_Table_0_wrapper\"]/table[@id=\"DataTables_Table_0\"]/tbody/tr/td[8]/ul/li/a/i"));

        Actions actions = new Actions(chromeDriver);
        actions.moveToElement(viewButton).click().build().perform();

        Thread.sleep(3000);

        List<WebElement> headerNames = chromeDriver.findElements(By.xpath("//table[@class=\"table table-striped\"]/thead/tr/th"));
        count = headerNames.size();
        System.out.println(count);

        List<WebElement> itemInOrder = chromeDriver.findElements(By.xpath("//table[@class=\"table table-striped\"]/tbody/tr"));
        int itemRowCount = itemInOrder.size();
        System.out.println(itemRowCount);
        int statusCount = count - 1;
        String status;

        //for(WebElement itemRow: itemInOrder){
        status = itemInOrder.get(0).findElement(By.xpath("//table[@class=\"table table-striped\"]/tbody/tr/td[" + statusCount + "]")).getText();
        if (status.equalsIgnoreCase("Order Placed")) {
            itemInOrder.get(0).findElement(By.xpath("//table[@class=\"table table-striped\"]/tbody/tr/td[" + count + "]")).click();
            WebElement statusDropDown = chromeDriver.findElement(By.xpath("//div/select"));
            actions.moveToElement(statusDropDown).click().build().perform();
            Select select = new Select(statusDropDown);
            Thread.sleep(3000);
            select.selectByVisibleText(" Completed ");

            chromeDriver.findElement(By.xpath("//button[text()=\"Update\"]")).click();

            WebElement accept = chromeDriver.findElement(By.xpath("//button[text()=\"Yes\"]"));
            actions.moveToElement(accept).click().build().perform();

            Thread.sleep(10000);
            chromeDriver.findElement(By.xpath("//button[text()=\"Close\"]")).click();

            chromeDriver.navigate().refresh();
            Thread.sleep(3000);

            List<WebElement> itemInOrderUpdated = chromeDriver.findElements(By.xpath("//table[@class=\"table table-striped\"]/tbody/tr"));

            Assert.assertTrue(itemInOrderUpdated.get(0).findElement(By.xpath("//table[@class=\"table table-striped\"]/tbody/tr/td[" + statusCount + "]")).getText().contains("Completed"));


        }
    }


    @Test(priority = 4)
        public void closeBrowser(){
            chromeDriver.quit();
        }

    }
