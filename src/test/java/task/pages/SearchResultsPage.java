package task.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static task.utils.WebUtils.waitAndClick;

public class SearchResultsPage extends SearchPage {

    @FindBy(xpath = "//span[text()='Filter']/parent::button")
    private WebElement filterBtn;

    public void clickFilterBtn() {
        waitAndClick(filterBtn);
    }

}
