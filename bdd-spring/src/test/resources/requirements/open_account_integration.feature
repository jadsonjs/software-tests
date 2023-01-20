

Feature: Bank Accounts 2

  Scenario: Open a new account integration test
    Given I make a initial deposit
    When I request to open a new account
    Then A new account should be created
    And the response will return status 200