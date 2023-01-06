package task.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import task.utils.ConfigReader;

import static task.utils.Driver.driver;

public class BasePage {

    protected String url = ConfigReader.get("url");
    protected JavascriptExecutor js = (JavascriptExecutor) driver();

    @FindBy(xpath = "//a[text()='Home']")
    protected WebElement home;

    @FindBy(xpath = "//a[text()='Listings']")
    protected WebElement listings;

    public BasePage() {
        PageFactory.initElements(driver(), this);
    }

    public void load() {
        driver().get(url);
    }


}
