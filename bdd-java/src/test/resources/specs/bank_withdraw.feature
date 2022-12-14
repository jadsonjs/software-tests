
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

  Scenario: Withdraw money from account.
    Given I have a balance in my account.
    When I request a amount smaller than the balance.
    Then The withdraw should be done


  Scenario: Attempt withdrawal negative balance
    Given I have a balance in my account.
    When I request an amount greater than the balance
    Then An error message should be shown.