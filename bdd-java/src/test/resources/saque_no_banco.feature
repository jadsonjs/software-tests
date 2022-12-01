
#
# We specify the expected behaviour by defining features and scenarios.
# The feature describes (part of) a feature of your application, and the
# scenarios describe different ways users can use this feature.
#

# The plain text steps are defined in the Gherkin language.
# Gherkin allows developers and business stakeholders to describe and share the
# expected behaviour of the application. It should not describe the implementation




Feature: Withdraw in a Bank
  This feature manger how withdraw works

  Scenario: Withdraw money from account.
    Given I have $100 in my account.
    When I request $20.
    Then The account should stay with $80


  Scenario: Attempt withdrawal using invalid card
    Given I have $100 in my account.
    But  my card is invalid.
    When I request $20.
    Then my card should not be returned.
    And An error message should be shown.