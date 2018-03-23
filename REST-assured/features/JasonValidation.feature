@run
Feature: Validate CEP from the API

  @Test1
  Scenario: Verify email Address
    When I input a valid postid "1" for comments
    Then I should have the status code "200"
    And the body response content should be matched
      | key   | value              |
      | email | Eliseo@gardner.biz |

  @Test2
  Scenario: Verify total users
    When I request to get the list of users
    Then I should have the status code "200"
    And the body response content should have "10" users