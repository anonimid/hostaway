package task.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import task.utils.ConfigReader;
import task.utils.WebUtils;

import static task.utils.Driver.driver;

public class ListingsPage extends BasePage {

    @FindBy(xpath = "//span[text()='All']/span")
    private WebElement labelAll;

    private By all_listings = By.xpath("//a[contains(@href,'/listings/')]");

    public void load() {
        driver().get(ConfigReader.get("listings-url"));
        WebUtils.waitForVisibility(labelAll, 10);
    }

    public void verifyNumberOfListingsAreCorrect() {

        int expectedCount = Integer.parseInt(js.executeScript("return arguments[0].childNodes[1].textContent;", labelAll).toString());
        int timeout = expectedCount / 6 + 1;
        for (int counter = 0; counter < timeout; counter++) {
            driver().findElements(all_listings).forEach(element -> WebUtils.waitForClickability(element, 5));
            WebUtils.scrollToBottom();
        }
        Assert.assertEquals(expectedCount, driver().findElements(all_listings).size());

    }
}
