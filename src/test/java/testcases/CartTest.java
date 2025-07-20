package testcases;

import org.testng.annotations.Test;

public class CartTest extends BaseTest{

    protected String username = "standard_user";
    protected String password = "secret_sauce";

    @Test
    public void ClickCart(){
        loginPage.verifyLoginButton();
        loginPage.wait(1);
        loginPage.inputUserName(username);
        loginPage.wait(1);
        loginPage.inputPassword(password);
        loginPage.wait(1);
        loginPage.clickLoginButton();
        homePage.verifyLoginProcess();
        homePage.wait(2);
        homePage.verifyCartIcon();
        homePage.wait(2);
        homePage.clickCartIcon();
        homePage.wait(2);
        checkoutPage.verifyCartTitle();
        checkoutPage.wait(1);
        checkoutPage.clickButtonContinueShopping();
        homePage.verifyLoginProcess();
    }
}
