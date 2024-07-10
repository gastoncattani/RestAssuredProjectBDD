Feature: Validating Place API's

  Scenario: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload
    When User calls "AddPlaceAPI" with POST http request
    Then The API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"