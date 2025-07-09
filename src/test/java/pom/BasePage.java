package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import testcases.BaseTest;
import utilities.PrintUtilities;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class BasePage extends BaseTest {
    public void click(By by) {
        waitVisibility(by);

        driver.findElement(by).click();
    }

    public WebElement waitAndFindElement(WebElement webElement) {
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public WebElement waitAndFindElement(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public List<WebElement> waitAndFindElements(By by) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public void waitVisibility(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void wait(int second) {
        sleep(second * 1000);
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean checkElementExistByVisibilityOfElementLocated(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean checkElementExistByVisibilityOfElementLocated(By by, int second) {
        WebDriverWait wait = new WebDriverWait(driver, second);

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public void assertEquals(String actual, String expected) {
        Assert.assertEquals(actual, expected, "Two texts are not equal!" + "Actual: " + actual + " Expected: " + expected);
    }

    public void assertEquals(int actual, int expected) {
        Assert.assertEquals(actual, expected, "Two texts are not equal!" + "Actual: " + actual + " Expected: " + expected);
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public int getTotalElements(By by) {
        return driver.findElements(by).size();
    }

    public void assertTrue(boolean flag) {
        Assert.assertTrue(flag);
    }

    public void assertFalse(boolean flag) {
        Assert.assertFalse(flag);
    }

    public void sendText(By by, String text) {
        waitAndFindElement(by).sendKeys(text);
    }

    public void selectByIndex(By by, int index) {
        Select select = new Select(waitAndFindElement(by));

        select.selectByIndex(index);
    }

    public void clearEditText(By by) {
        waitAndFindElement(by).clear();
    }

    public void clearEditTextV2(By by) {
        WebElement element = waitAndFindElement(by);
        while (!element.getAttribute("value").equals("")) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public String getText(By by) {
        return waitAndFindElement(by).getText();
    }

    public String[] getTexts(By by) {
        List<String> temp = new ArrayList();
        List<WebElement> element = waitAndFindElements(by);

        // Last data is always null
        for (int i = 0; i < element.size(); i++) {
            temp.add(element.get(i).getText());
        }

        return temp.toArray(new String[0]);
    }

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month - 1];
    }


    public void validateElementShow(By by) {
        boolean elementShowStatus = checkElementExistByVisibilityOfElementLocated(by, 10);

        PrintUtilities.printAssertLog(elementShowStatus ? "true" : "false", "true");

        if (elementShowStatus == true)
            System.out.println("Message = " + getText(by));

        assertTrue(elementShowStatus);
    }

    public void checkElementShowOrSkip(By by) {
        boolean elementShowStatus = checkElementExistByVisibilityOfElementLocated(by, 10);

        PrintUtilities.printAssertLog(elementShowStatus ? "true" : "false", "true");

        if (elementShowStatus == false) {
            System.out.println("Element " + by.toString() + " not show, test skipped");
            throw new SkipException("Element " + by.toString() + " not show, test skipped");
        }
    }

    public void validateText(By by, String expectedText) {
        String tempActualText = getText(by);
        String tempExpectedText = expectedText;

        PrintUtilities.printAssertLog(tempActualText, tempExpectedText);
        assertEquals(tempActualText, tempExpectedText);
    }

    public void validateTextContains(By by, String expectedText) {
        String tempActualText = getText(by);
        String tempExpectedText = expectedText;

        PrintUtilities.printAssertLog(tempActualText, tempExpectedText);
        assertTrue(tempActualText.contains(tempExpectedText));
    }

    public void deviceBackButton() {
        driver.navigate().back();
    }

    public void validateTextNotEmpty(By by) {
        int stringLength = waitAndFindElement(by).getText().length();

        if (stringLength > 0) {
            System.out.println("Element Text : " + getText(by));
        }

        assertTrue(stringLength > 0);
    }

    public void acceptBrowserAlert() {
        driver.switchTo().alert().accept();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void validateUrl(String expectedUrl) {
        String tempActualText = getCurrentUrl();
        String tempExpectedText = expectedUrl;

        PrintUtilities.printAssertLog(tempActualText, tempExpectedText);
        assertTrue(tempActualText.toLowerCase().contains(tempExpectedText.toLowerCase()));
    }

    public void clickByOrder(String xpathString, String order) {
        click(By.xpath("(" + xpathString + ")[" + order + "]"));
    }

    public WebElement getWebElement(By by) {
        return waitAndFindElement(by);
    }

    public void scrollToElement(By by) {
        executor.executeScript("arguments[0].scrollIntoView(false)", driver.findElement(by));
    }

    public void clickByText(String input) {
        click(By.xpath("//*[text()= \"" + input + "\"]"));
    }
}
