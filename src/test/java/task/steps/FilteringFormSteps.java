package task.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import task.pages.FiltersPopup;
import task.pages.SearchPage;
import task.pages.SearchResultsPage;

public class FilteringFormSteps {

    SearchPage searchPage = new SearchPage();
    FiltersPopup filtersPopup = new FiltersPopup();

    @Given("user goes to homepage")
    public void userGoesToHomepage() {
        searchPage.load();
    }

    @When("user selects specific check-in check-out dates")
    public void userEntersSpecificCheckInCheckOutDates() {
        searchPage.clickCheckInField();
        searchPage.selectTodayAsCheckInDate();
        searchPage.selectNextMonthLastDayAsCheckoutDate();
    }

    @And("clicks search button")
    public void clicksSearchButton() {
        searchPage.clickSearch();
    }

    @And("clicks filter button")
    public void clicksFilterButton() {
        SearchResultsPage searchResultsPage = new SearchResultsPage();
        searchResultsPage.clickFilterBtn();
    }

    @Then("verify that Filters form should be displayed")
    public void verifyThatFiltersFormShouldBeDisplayed() {
        filtersPopup.verifyFormIsDisplayed();
    }

    @And("verify that from-to inputs are clickable")
    public void verifyThatFromToInputsAreClickable() {
        filtersPopup.verifyFromToInputsAreClickable();
    }

    @And("checkboxes under Amenities are clickable")
    public void checkboxesUnderAmenitiesAreClickable() {
        filtersPopup.verifyThatAmenitiesCheckboxesAreClickable();
    }

    @And("fills out from-to inputs")
    public void fillsOutFromToInputs() {

        filtersPopup.fillOutFromToInputs();
    }

    @And("selects all checkboxes")
    public void selectsAllCheckboxes() {
        filtersPopup.selectAllCheckboxes();
    }


    @And("clicks clear all button")
    public void clicksClearAllButton() {
        filtersPopup.clickClearAllBtn();
    }

    @Then("verify that all checkboxes are unselected")
    public void verifyThatAllInputsAreEmptyOrUnselected() {
        filtersPopup.verifyInputsAreEmptyOrUnselected();
    }


}
