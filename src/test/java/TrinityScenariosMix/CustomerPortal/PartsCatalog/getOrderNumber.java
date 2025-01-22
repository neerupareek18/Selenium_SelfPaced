package TrinityScenariosMix.CustomerPortal.PartsCatalog;

import org.testng.annotations.Test;

import java.util.List;

public class getOrderNumber {

    @Test
    public void getOrderNumber(){
        String orderText = "Order No.: 2025PC0006";
        List orderNumber = List.of(orderText.split(":"));
        System.out.println(orderNumber.size());
        System.out.println(orderNumber.get(0));
        System.out.println(orderNumber.get(1));

        String number = (String) orderNumber.get(1);
        String number1 = number.substring(1,number.length());

        System.out.println(number1);



    }
}
