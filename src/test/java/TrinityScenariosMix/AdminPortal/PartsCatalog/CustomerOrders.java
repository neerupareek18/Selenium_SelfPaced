package TrinityScenariosMix.AdminPortal.PartsCatalog;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CustomerOrders {
    WebDriver chromeDriver = new ChromeDriver();

    @Test
    public void CustomerOrderStatusCheck() throws InterruptedException {
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

        chromeDriver.findElement(By.xpath("//input[@type=\"search\"]")).sendKeys("2025PC0010");
        List<WebElement> headers = chromeDriver.findElements(By.xpath("//div[@id=\"DataTables_Table_0_wrapper\"]/table[@id=\"DataTables_Table_0\"]/thead/tr/th"));
        Thread.sleep(5000);
        int count=0;
        int index=0;
        for(WebElement headernames: headers){
    count++;
    if(headernames.getText().contains("Status")){
        index = count;
        break;
    }
    }
        System.out.println(chromeDriver.findElement(By.xpath("//div[@id=\"DataTables_Table_0_wrapper\"]/table[@id=\"DataTables_Table_0\"]/tbody/tr/td["+index+"]")).getText());

        chromeDriver.findElement(By.xpath("//div[@id=\"DataTables_Table_0_wrapper\"]/table[@id=\"DataTables_Table_0\"]/tbody/tr/td[8]/i")).click();
        WebElement viewButton = chromeDriver.findElement(By.xpath("//div[@id=\"DataTables_Table_0_wrapper\"]/table[@id=\"DataTables_Table_0\"]/tbody/tr/td[8]/ul/li/a/i"));

        Actions actions = new Actions(chromeDriver);
actions.moveToElement(viewButton).click().build().perform();

Thread.sleep(3000);


    }
@Test
    public void UpdateItemWiseStatus() throws InterruptedException {

        List<WebElement> headerNames = chromeDriver.findElements(By.xpath("//table[@class=\"table table-striped\"]/thead/tr/th"));
        int count = headerNames.size();
    System.out.println(count);

        List<WebElement> itemInOrder = chromeDriver.findElements(By.xpath("//table[@class=\"table table-striped\"]/tbody/tr"));
        int itemRowCount = itemInOrder.size();
    System.out.println(itemRowCount);
    int statusCount = count-1;
    String status;
    Actions actions = new Actions(chromeDriver);

        //for(WebElement itemRow: itemInOrder){
            status =  itemInOrder.get(0).findElement(By.xpath("//table[@class=\"table table-striped\"]/tbody/tr/td["+statusCount+"]")).getText();
            if(status.equalsIgnoreCase("Order Placed")) {
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

                Assert.assertTrue(itemInOrderUpdated.get(0).findElement(By.xpath("//table[@class=\"table table-striped\"]/tbody/tr/td["+statusCount+"]")).getText().contains("Completed"));



            }


       // }
    }

    @Test
    public void tearDown(){
        chromeDriver.quit();
    }
}
