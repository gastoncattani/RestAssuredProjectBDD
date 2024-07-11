Feature: Validating Place API's 2
  @AddPlace
  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "ADDPLACEAPI" with "<httpMethod>" http request
    Then The API call is success with status code 400
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "GETPLACEAPI"

    Examples:
      |name   |language  |address  |httpMethod|
      |Gian   | English  |Address 1|Post      |
#      |Gaston | Spanish  |Address 1|Post      |
  @DeletePlace
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When User calls "DELETEPLACEAPI" with "POST" http request
    Then The API call is success with status code 200
    And "status" in response body is "OK"
    And PlaceId is removed