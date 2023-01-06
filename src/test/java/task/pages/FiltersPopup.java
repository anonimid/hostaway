package task.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import task.utils.WebUtils;

import java.util.List;

import static task.utils.WebUtils.waitAndClick;

public class FiltersPopup extends BasePage {


    @FindBy(css = "[placeholder='From']")
    private WebElement fromInput;

    @FindBy(css = "[placeholder='To']")
    private WebElement toInput;

    @FindBy(xpath = "//div[text()='Amenities']/following-sibling::div//label")
    private List<WebElement> checkboxLabelsUnderAmenities;

    @FindBy(xpath = "//div[text()='Amenities']/following-sibling::div//input")
    private List<WebElement> checkboxesUnderAmenities;

    @FindBy(xpath = "//b[text()='Clear all']")
    private WebElement clearAllBtn;

    @FindBy(xpath = "//div[@class='__modal']//div[text()='Filters']")
    private WebElement formTitle;

    public void verifyFormIsDisplayed() {
        Assert.assertTrue(formTitle.isDisplayed());
        Assert.assertTrue(clearAllBtn.isDisplayed());
    }

    public void verifyFromToInputsAreClickable() {
        try {
            fromInput.click();
        } catch (Throwable t) {
            Assert.fail("From input is not present or interactable!");
        }
        fromInput.sendKeys("100");
        Assert.assertEquals("100", fromInput.getAttribute("value"));

        Assert.assertTrue(toInput.isDisplayed());
        Assert.assertTrue(toInput.isEnabled());
        toInput.sendKeys("100");
        Assert.assertEquals("100", toInput.getAttribute("value"));
    }

    public void verifyThatAmenitiesCheckboxesAreClickable() {
        checkboxLabelsUnderAmenities.forEach(WebElement::click);
        checkboxesUnderAmenities.forEach((element) -> Assert.assertTrue(element.isSelected()));
    }

    public void clickClearAllBtn() {
        waitAndClick(clearAllBtn);
    }

    public void fillOutFromToInputs() {
        fromInput.sendKeys("100");
        Assert.assertEquals("100", fromInput.getAttribute("value"));
        toInput.sendKeys("200");
        Assert.assertEquals("200", toInput.getAttribute("value"));
    }


    public void selectAllCheckboxes() {
        checkboxLabelsUnderAmenities.forEach(WebUtils::waitAndClick);
    }

    public void verifyInputsAreEmptyOrUnselected() {
        checkboxesUnderAmenities.forEach((checkbox) -> Assert.assertFalse(checkbox.isSelected()));
    }
}
