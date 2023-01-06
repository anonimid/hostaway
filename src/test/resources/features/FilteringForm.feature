@all @filters
Feature: Filters Form test scenarios

  Background:
    Given user goes to homepage

  Scenario: Verify that user can interact with Filters Form
    When user selects specific check-in check-out dates
    And clicks search button
    And clicks filter button
    Then verify that Filters form should be displayed
    And checkboxes under Amenities are clickable

  Scenario: Verify that 'clear all' button clears all kinds of inputs in the Filters form
    When user selects specific check-in check-out dates
    And clicks search button
    And clicks filter button
    And selects all checkboxes
    And clicks clear all button
    Then verify that all checkboxes are unselected