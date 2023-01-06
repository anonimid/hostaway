package task.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import task.pages.ListingsPage;

public class AllListingSteps {

    ListingsPage listingsPage = new ListingsPage();

    @Given("user goes to all listings page")
    public void userGoesToAllListingsPage() {

        listingsPage.load();
    }

    @Then("verify that the number of all listings is the number shown in the -All- label")
    public void verifyThatTheNumberOfAllListingsIsTheNumberShownInTheAllLabel() {
        listingsPage.verifyNumberOfListingsAreCorrect();
    }
}
