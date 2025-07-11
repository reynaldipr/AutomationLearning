package testcases.Login;

import org.testng.annotations.Test;
import testcases.BaseTest;

public class LogoutTest extends BaseTest {
    protected String username = "standard_user";
    protected String password = "secret_sauce";

    @Test
    public void Logout(){
        loginPage.verifyLoginButton();
        loginPage.wait(1);
        loginPage.inputUserName(username);
        loginPage.wait(1);
        loginPage.inputPassword(password);
        loginPage.wait(1);
        loginPage.clickLoginButton();
        dashboardPage.verifyLoginProcess();
        dashboardPage.wait(1);
        navigationPane.clickMenuButton();
        navigationPane.wait(1);
        navigationPane.clickLogoutButton();
        loginPage.verifyLoginButton();
        loginPage.wait(1);
    }
}
