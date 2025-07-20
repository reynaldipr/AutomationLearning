package testcases.Login;

import org.testng.annotations.Test;
import testcases.BaseTest;

public class LoginTest extends BaseTest {
    protected String username = "standard_user";
    protected String password = "secret_sauce";
    protected String invalidUsername = "AAAAAAAAA";

    @Test(priority = 2)
    public void LoginValid(){
        loginPage.verifyLoginButton();
        loginPage.wait(1);
        loginPage.inputUserName(username);
        loginPage.wait(1);
        loginPage.inputPassword(password);
        loginPage.wait(1);
        loginPage.clickLoginButton();
        homePage.verifyLoginProcess();
        homePage.wait(2);
    }

    @Test(priority = 1)
    public void LoginInvalid(){
        loginPage.verifyLoginButton();
        loginPage.wait(1);
        loginPage.inputUserName(invalidUsername);
        loginPage.wait(1);
        loginPage.inputPassword(password);
        loginPage.wait(1);
        loginPage.clickLoginButton();
        loginPage.verifyErrorCredential();
        loginPage.wait(2);
        loginPage.clearUsername();
        loginPage.wait(1);
        loginPage.clearPassword();
    }
}
