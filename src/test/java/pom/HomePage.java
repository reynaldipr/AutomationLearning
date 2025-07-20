package pom;

import org.openqa.selenium.By;

public class HomePage extends BasePage {
    protected String appLogo = "//div[@class='app_logo']";
    protected String cartIcon = "//a[@class='shopping_cart_link']";
    //Array untuk button Add to cart di homepage
    protected String buttonAddToCart1 = "(//button[@class='btn btn_primary btn_small btn_inventory '])[1]";

    public void verifyLoginProcess() {
        validateElementShow(By.xpath(appLogo));
    }

    public void verifyCartIcon() {
        validateElementShow(By.xpath(cartIcon));
    }

    public void clickCartIcon() {
        click(By.xpath(cartIcon));
    }

    public void clickButtonAddToCartBackpack() {
        click(By.xpath(buttonAddToCart1));
    }
}
