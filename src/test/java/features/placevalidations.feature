Feature: Validating Place API's

  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "<serviceName>" with "<httpMethod>" http request
    Then The API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

    Examples:
      |name   |language  |address  |serviceName|httpMethod|
      |Gian   | English  |Address 1|addPlaceAPI|Post      |
      |Noelia | Spanish  |Address 2|addPlaceAPI|Post      |
      |Gaston | Italiano |Address 3|addPlaceAPI|Get       |