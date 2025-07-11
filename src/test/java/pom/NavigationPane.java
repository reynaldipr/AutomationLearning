package pom;

import org.openqa.selenium.By;

public class NavigationPane extends BasePage {
    protected String menuButton = "//button[@id = 'react-burger-menu-btn']";
    protected String inventoryButton = "//a[@id='inventory_sidebar_link']";
    protected String aboutButton = "//a[@id='about_sidebar_link']";
    protected String logoutButton = "//a[@id='logout_sidebar_link']";

    public void clickMenuButton() {
        click(By.xpath(menuButton));
    }

    public void clickInventoryButton() {
        click(By.xpath(inventoryButton));
    }

    public void clickAboutButton() {
        click(By.xpath(aboutButton));
    }

    public void clickLogoutButton() {
        click(By.xpath(logoutButton));
    }
}
