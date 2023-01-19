
#
# We specify the expected behaviour by defining features and scenarios.
# The feature describes (part of) a feature of your application, and the
# scenarios describe different ways users can use this feature.
#

# The plain text steps are defined in the Gherkin language.
# Gherkin allows developers and business stakeholders to describe and share the
# expected behaviour of the application. It should not describe the implementation


@AccountTest

Feature: Withdraw in a Bank
  This feature manger how withdraw works


  Background:
    Given all bank accounts
    | Ferenc Puskas               | 111    | 1000    |
    | Edson Arantes do Nascimento | 222    | 2000    |
    | Manoel Francisco dos Santos | 333    | 5000    |


  Scenario: Withdraw money from account
    Given I have a balance in my account
    When I request a amount smaller than the balance
    Then The withdraw should be done


#  Scenario: Withdraw money from account
#    Given I have a balance of 5000.0 in my account with id 1000 for user "example@example.com"
#    When I request a amount smaller than the balance
#    Then The withdraw should be done


  Scenario: Attempt withdrawal negative balance
    Given I have a balance in my account
    When I request an amount greater than the balance
    Then An error message should be shown




  Scenario Template: Withdraw money from account with examples
    Given I have <balance> in my account
    When I request <amount>
    Then I should stay with <result>

  Examples:
  | balance   | amount   | result   |
  |  2000.0   |  500.0   |   1500.0 |
  |  5000.0   | 1000.0   |   4000.0 |






  Scenario: Withdraw money at automatic teller machine (ATM)
    Given the account balance is $1000
    And My credit card is valid
    And The ATM contains enough money
    But It is not after midnight
    When I request $200
    Then The ATM should dispense $200
    And The account balance should be $800
    And The card should be returned
    But The ATM cannot stay empty



