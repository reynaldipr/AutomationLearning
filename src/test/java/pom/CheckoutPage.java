package pom;

import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {
    protected String cartTitle = "//span[@class='title']";
    protected String buttonContinueShopping = "//button[@id='continue-shopping']";
    protected String buttonCheckout = "//button[@id='checkout']";


    public void verifyCartTitle() {
        validateElementShow(By.xpath(cartTitle));
    }

    public void clickButtonContinueShopping() {
        click(By.xpath(buttonContinueShopping));
    }

}
