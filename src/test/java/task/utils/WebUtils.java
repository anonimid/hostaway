package task.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static task.utils.Driver.driver;

public class WebUtils {

    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickability(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitAndClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static WebElement waitForClickability(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(driver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForMilliSeconds(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", element);
    }

    public static void scrollToTop() {
        ((JavascriptExecutor) driver()).executeScript(
                "window.scrollTo({" +
                        "top : 0," +
                        "left: 0," +
                        "behavior : 'smooth'" +
                        "})");
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver()).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'end', inline: 'nearest'});", element);
    }

    public static void scrollBy(int yPixels, WebElement... source) {
        if (source.length > 0) {
            ((JavascriptExecutor) driver()).executeScript("arguments[0].scrollBy({ 'top' : arguments[1], 'left' : 0, 'behavior' :'smooth'});", source[0], yPixels);
        } else {
            ((JavascriptExecutor) driver()).executeScript("window.scrollBy({ 'top' : arguments[0], 'left' : 0, 'behavior' :'smooth'});", yPixels);
        }
    }

    public static void hover(WebElement element) {
        Actions actions = new Actions(driver());
        actions.moveToElement(element).perform();
    }

    public static boolean isImageDisplayed(WebDriver driver, WebElement imageElement) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        return (boolean) javascriptExecutor.executeScript("return arguments[0].complete "
                + "&& typeof arguments[0].naturalWidth != 'undefined'"
                + "&& arguments[0].naturalWidth > 0", imageElement);
    }

    public static void scrollToBottom() {
        ((JavascriptExecutor) driver()).executeScript(
                "window.scrollTo({" +
                        "top : document.body.scrollHeight," +
                        "left: 0," +
                        "behavior : 'smooth'" +
                        "})");
    }

}
