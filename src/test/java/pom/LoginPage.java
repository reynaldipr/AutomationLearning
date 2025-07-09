package pom;

import org.openqa.selenium.By;

public class LoginPage extends BasePage{
    protected String textAreaUsername = "username";
    protected String textAreaPassword = "password";
    protected String buttonContinue = "action";

    public void inputUserNameAndPassword(String username, String password) {
        sendText(By.id(textAreaUsername), username);
        sendText(By.id(textAreaPassword), password);
        click(By.name(buttonContinue));
    }
}
