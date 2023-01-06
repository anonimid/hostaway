@all @all_listings
Feature: All Listings page test scenarios

  Scenario: Check that the label in Listings page shows correct number of listings
    Given user goes to all listings page
    Then verify that the number of all listings is the number shown in the -All- label