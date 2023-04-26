
@IntegrationTest

Feature: Open Accounts in the Bank

  Scenario: Open a new account
    Given I make a initial deposit
    When I request "/account/open" to open a new account
    Then A new account should be created
    And the response will return status 200

