








Feature: Login

  Scenario: Login on system
    Given I have a user
    And with a valid email
    And with a valid password
    When I submit the from
    Then I should be redirect to home page
