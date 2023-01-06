package task.pages;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

import static java.time.temporal.ChronoUnit.DAYS;
import static task.utils.Driver.driver;
import static task.utils.WebUtils.*;

public class SearchPage extends BasePage {

    Random random = new Random();
    DateTimeFormatter toMonthYear = DateTimeFormatter.ofPattern("MMMM yyyy"); // January 2023
    DateTimeFormatter toDay = DateTimeFormatter.ofPattern("d"); // 6
    DateTimeFormatter fullDate = DateTimeFormatter.ofPattern("d MMMM yyyy");// 6 January 2023
    DateTimeFormatter month_day = DateTimeFormatter.ofPattern("MMM d"); // Jan 6
    String dateSelector = "(//div[text()='%s']/following-sibling::div)[1]/div[contains(@class,'CalendarDay') and text()='%s']";
    String expectedSelectedDateUnderCheckInField;
    String expectedSelectedDateUnderCheckOutField;
    String expectedCheckInDateInExpandedCalendar;
    String expectedCheckOutDateInExpandedCalendar;
    LocalDate checkoutDate;
    @FindBy(xpath = "//div[text()='Location']/../..")
    private WebElement location;
    @FindBy(xpath = "//span[text()='Search']/parent::button")
    private WebElement searchBtn;
    @FindBy(xpath = "//div[text()='Check-in']/../..")
    private WebElement checkIn;
    @FindBy(xpath = "//div[text()='Check-in']/following-sibling::div")
    private WebElement checkInDateText;
    @FindBy(xpath = "//div[text()='Check-out']/../..")
    private WebElement checkOut;
    @FindBy(xpath = "//div[text()='Check-out']/following-sibling::div")
    private WebElement checkOutDateText;
    @FindBy(css = "._Popover--show")
    private WebElement dateSelection;
    @FindBy(xpath = "((//div[@disabled])[last()]/following-sibling::div)[1]")
    private WebElement today;
    @FindBy(xpath = "(//div[contains(@class,'CalendarDay')][last()])[2]")
    private WebElement lastDayOfNextMonth;
    @FindBy(xpath = "//span[text()='Clear dates']/parent::button")
    private WebElement clearDatesBtn;
    @FindBy(xpath = "//div[text()='Select check-in and check-out dates']")
    private WebElement selectionInfoElement;
    @FindBy(xpath = "//div[contains(text(),'Check-in:')]")
    private WebElement dateContainerInExpandedCalendar;
    @FindBy(css = "._Popover--show button")
    private WebElement leftArrowBtn;

    public void selectTodayAsCheckInDate() {
        waitAndClick(today);

        LocalDate today = LocalDate.now();
        expectedSelectedDateUnderCheckInField = today.format(fullDate); // 6 January 2023
        expectedCheckInDateInExpandedCalendar = today.format(month_day); // Jan 6
    }

    public void selectRandomDateAfterTodayAsCheckOutDate() {

        LocalDate today = LocalDate.now();
        int todayAsInt = Integer.parseInt(today.format(toDay)); // 6
        int lengthOfCurrentMonth = today.lengthOfMonth(); // 31
        int lengthOfNextMonth = today.plusMonths(1).lengthOfMonth(); // 28
        int daysLater = random.nextInt(lengthOfCurrentMonth + lengthOfNextMonth - todayAsInt - 1) + 1;
        checkoutDate = today.plus(daysLater, DAYS);
        String monthYear = checkoutDate.format(toMonthYear); //January 2023
        String day = checkoutDate.format(toDay);
        expectedSelectedDateUnderCheckOutField = checkoutDate.format(fullDate); // 19 January 2023
        expectedCheckOutDateInExpandedCalendar = checkoutDate.format(month_day); // Jan 19
        String locator = String.format(dateSelector, monthYear, day);
        WebElement checkoutDay = driver().findElement(By.xpath(locator));
        checkoutDay.click();
    }

    public void selectNextMonthLastDayAsCheckoutDate() {
        waitAndClick(lastDayOfNextMonth);
    }

    public void selectOneWeekLaterAsCheckoutDate() {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plus(7, DAYS);
        String monthYear = localDate.format(toMonthYear);
        String oneWeekLater = localDate.format(toDay);
        String locator = String.format(dateSelector, monthYear, oneWeekLater);
        WebElement thatDay = driver().findElement(By.xpath(locator));
        thatDay.click();
    }

    public void clickSearch() {
        waitFor(1);
        waitAndClick(searchBtn);
        waitForPageToLoad(10);
    }

    public void clickClearDatesBtn() {
        waitFor(2);
        waitAndClick(clearDatesBtn);
    }


    public void clickCheckInField() {
        waitAndClick(checkIn);
        waitForVisibility(clearDatesBtn, 5);
    }

    public void verifySelectionInfoElementDisplayed() {
        Assert.assertTrue(waitForVisibility(selectionInfoElement, 5).isDisplayed());
    }

    public void verifyDatesDisplayedUnderRelatedFields() {
        Assert.assertEquals(expectedSelectedDateUnderCheckInField, checkInDateText.getText());
        Assert.assertEquals(expectedSelectedDateUnderCheckOutField, checkOutDateText.getText());
    }


    public void verifyDatesDisplayedInExpandedCalendar() {
        String checkinDateContainer = js.executeScript("return arguments[0].childNodes[1].textContent;", dateContainerInExpandedCalendar).toString();
        String actualCheckInDate = checkinDateContainer.split(": ")[1];
        String checkOutDateContainer = js.executeScript("return arguments[0].childNodes[3].textContent;", dateContainerInExpandedCalendar).toString();
        String actualCheckOutDate = checkOutDateContainer.split(": ")[1];

        Assert.assertEquals(expectedCheckInDateInExpandedCalendar, actualCheckInDate);
        Assert.assertEquals(expectedCheckOutDateInExpandedCalendar, actualCheckOutDate);

    }

    public void verifyCurrentAndNextMonthNameShouldBeDisplayed() {
        LocalDate today = LocalDate.now();

        String thisMonth = today.format(toMonthYear);
        String thisMonthLocator = String.format("//div[text()='%s']", thisMonth);
        WebElement currentMonth = driver().findElement(By.xpath(thisMonthLocator));
        String nextMonthStr = today.plusMonths(1).format(toMonthYear);
        String nextMonthLocator = String.format("//div[text()='%s']", nextMonthStr);
        WebElement nextMonth = driver().findElement(By.xpath(nextMonthLocator));

        Assert.assertTrue(currentMonth.isDisplayed());
        Assert.assertTrue(nextMonth.isDisplayed());
    }

    public void verifyUserCannotSelectDateInThePast() {

        LocalDate oneMonthBefore = LocalDate.now().minusMonths(1);
        String monthYear = oneMonthBefore.format(toMonthYear);
        String day = oneMonthBefore.format(toDay);
        waitAndClick(leftArrowBtn);
        WebElement pastDate = driver().findElement(By.xpath(String.format(dateSelector, monthYear, day)));
        Assert.assertTrue(Objects.nonNull(pastDate.getAttribute("disabled")));
        pastDate.click();
        Assert.assertTrue(selectionInfoElement.isDisplayed());

    }

    public void verifySelectedRangeOfDatesHaveDifferentBackgroundColor() {
        LocalDate date = LocalDate.now();
        String whiteBackground = "rgba(0, 0, 0, 0)";

        do {
            String monthYear = date.format(toMonthYear);
            String day = date.format(toDay);
            WebElement dayElement = driver().findElement(By.xpath(String.format(dateSelector, monthYear, day)));
            String backgroundWhenSelected = js.executeScript("return window.getComputedStyle(arguments[0]).backgroundColor;", dayElement).toString();
            System.out.println("backgroundWhenSelected = " + backgroundWhenSelected);
            System.out.println("date = " + date);
            Assert.assertNotEquals(backgroundWhenSelected, whiteBackground);
            date = date.plusDays(1);
        } while (!date.toString().equals(checkoutDate.plusDays(1).toString()));

    }
}
