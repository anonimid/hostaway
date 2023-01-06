@all @date_selection
Feature: Check-in/ Check-out date selection test scenarios

  Background:
    Given user goes to homepage

  Scenario: Verify that 'Select check-in and check-out dates' text is displayed when dates are not selected or when clear dates button is clicked
    When user clicks check-in field to see dates
    Then -Select check-in and check-out dates- should be displayed
    When user selects today as check-in date
    And user selects one week later as check-out date
    And user clicks check-in field to see dates
    And clicks clear dates button
    Then -Select check-in and check-out dates- should be displayed

  Scenario: Verify that selected dates are shown in expanded calendar and in Check-in, Check-out fields.
    When user clicks check-in field to see dates
    Then -Select check-in and check-out dates- should be displayed
    When user selects today as check-in date
    And user selects any date later than today as check-out date
    Then verify that selected dates are correctly displayed under check-in check-out fields
    And user clicks check-in field to see dates
    Then verify that selected dates are correctly displayed in the expanded calendar

  Scenario: Verify that current month and next month is displayed when a date is not selected
    When user clicks check-in field to see dates
    Then current month and next month should be displayed on top of expanded calendar

  Scenario: Verify that a date earlier than today can not be selected
    When user clicks check-in field to see dates
    Then verify that user can not select a date before today

  Scenario: Verify that the selected date range has different background color compared to the dates outside the range
    When user clicks check-in field to see dates
    When user selects today as check-in date
    And user selects any date later than today as check-out date
    And user clicks check-in field to see dates
    Then verify that selected dates and dates in between have background color different from unselected dates