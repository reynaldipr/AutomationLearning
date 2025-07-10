package testcases.Login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import testcases.BaseTest;

public class LoginTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    protected String username = "standard_user";
    protected String password = "secret_sauce";
    protected String invalidUsername = "AAA";

    @Test(priority = 2)
    public void LoginValid(){
        loginPage.verifyLoginButton();
        loginPage.wait(1);
        loginPage.inputUserName(username);
        loginPage.wait(1);
        loginPage.inputPassword(password);
        loginPage.wait(1);
        loginPage.clickLoginButton();
        dashboardPage.verifyLoginProcess();
        dashboardPage.wait(2);
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
