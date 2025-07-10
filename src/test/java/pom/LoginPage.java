package pom;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    protected String textAreaUsername = "user-name";
    protected String textAreaPassword = "password";
    protected String buttonContinue = "login-button";
    protected String errorInvalidCredential = "//div[@class='error-message-container error']";

    public void verifyLoginButton() {
        validateElementShow(By.id(buttonContinue));
    }

    public void inputUserName(String username) {
        sendText(By.id(textAreaUsername), username);
    }

    public void inputPassword(String password) {
        sendText(By.id(textAreaPassword), password);
    }

    public void clickLoginButton() {
        click(By.id(buttonContinue));
    }

    public void verifyErrorCredential() {
        validateElementShow(By.xpath(errorInvalidCredential));
    }

    public void clearUsername() {
        clearEditTextV2(By.id(textAreaUsername));
    }

    public void clearPassword() {
        clearEditTextV2(By.id(textAreaPassword));
    }
}
