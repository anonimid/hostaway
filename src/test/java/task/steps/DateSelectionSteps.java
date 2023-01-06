package task.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import task.pages.SearchPage;

import static task.utils.WebUtils.waitFor;

public class DateSelectionSteps {

    SearchPage searchPage = new SearchPage();

    @When("user clicks check-in field to see dates")
    public void userClicksCheckInFieldToSeeDates() {
        searchPage.clickCheckInField();

    }


    @Then("-Select check-in and check-out dates- should be displayed")
    public void selectCheckInAndCheckOutDatesShouldBeDisplayed() {
        searchPage.verifySelectionInfoElementDisplayed();
        waitFor(2);
    }

    @And("clicks clear dates button")
    public void clicksClearDatesButton() {
        searchPage.clickClearDatesBtn();
    }

    @When("user selects today as check-in date")
    public void userSelectsTodayAsCheckInDate() {
        searchPage.selectTodayAsCheckInDate();
    }

    @And("user selects one week later as check-out date")
    public void userSelectsOneWeekLaterAsCheckOutDate() {
        searchPage.selectOneWeekLaterAsCheckoutDate();
    }

    @And("user selects any date later than today as check-out date")
    public void userSelectsAnyDateLaterThanTodayAsCheckOutDate() {
        searchPage.selectRandomDateAfterTodayAsCheckOutDate();
    }

    @Then("verify that selected dates are correctly displayed under check-in check-out fields")
    public void verifyThatSelectedDatesAreCorrectlyDisplayedUnderCheckInCheckOutFields() {
        searchPage.verifyDatesDisplayedUnderRelatedFields();
    }

    @Then("verify that selected dates are correctly displayed in the expanded calendar")
    public void verifyThatSelectedDatesAreCorrectlyDisplayedInTheExpandedCalendar() {
        searchPage.verifyDatesDisplayedInExpandedCalendar();
    }


    @Then("current month and next month should be displayed on top of expanded calendar")
    public void currentMonthAndNextMonthShouldBeDisplayedOnTopOfExpandedCalendar() {
        searchPage.verifyCurrentAndNextMonthNameShouldBeDisplayed();
    }

    @When("verify that user can not select a date before today")
    public void verifyThatUserCannotSelectADateBeforeToday() {
        searchPage.verifyUserCannotSelectDateInThePast();
    }

    @Then("verify that selected dates and dates in between have background color different from unselected dates")
    public void verifyThatSelectedDatesAndDatesInBetweenHaveBackgroundColorDifferentFromUnselectedDates() {
        searchPage.verifySelectedRangeOfDatesHaveDifferentBackgroundColor();
    }
}
