Feature: Pokemon SOAP Service

  Scenario: Get Pokemon abilities
    Given a pokemon name "pikachu"
    When I request the pokemon abilities
    Then I should receive a list of abilities
    And the request should be logged in database

  Scenario: Get Pokemon base experience
    Given a pokemon name "pikachu"
    When I request the pokemon base experience
    Then I should receive a base experience value
    And the request should be logged in database

  Scenario: Get Pokemon held items
    Given a pokemon name "pikachu"
    When I request the pokemon held items
    Then I should receive a list of held items
    And the request should be logged in database

  Scenario: Get Pokemon ID
    Given a pokemon name "pikachu"
    When I request the pokemon ID
    Then I should receive a valid ID
    And the request should be logged in database

  Scenario: Get Pokemon name
    Given a pokemon name "pikachu"
    When I request the pokemon name
    Then I should receive the normalized name
    And the request should be logged in database

  Scenario: Get Pokemon location encounters
    Given a pokemon name "pikachu"
    When I request the pokemon location encounters
    Then I should receive the location encounters URL
    And the request should be logged in database

  Scenario: Verify request logging
      Given a pokemon name "pikachu"
      When I make any pokemon request
      Then the following data should be logged in database:
        | Field       | Value    |
        | IP          | NotEmpty |
        | RequestDate | NotEmpty |
        | Method      | NotEmpty |
        | Duration    | NotEmpty |
        | Request     | NotEmpty |
        | Response    | NotEmpty |