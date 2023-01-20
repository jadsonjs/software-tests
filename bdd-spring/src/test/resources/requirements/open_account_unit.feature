

@UnitTest

Feature: Bank Accounts


  Scenario: Open a new account unit test
    Given I make a initial deposit
    When I request to open a new account
    Then A new account should be created