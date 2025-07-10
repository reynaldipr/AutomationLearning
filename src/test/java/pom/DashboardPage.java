package pom;

import org.openqa.selenium.By;

public class DashboardPage extends BasePage {
    protected String appLogo = "//div[@class='app_logo']";

    public void verifyLoginProcess() {
        validateElementShow(By.xpath(appLogo));
    }
}
