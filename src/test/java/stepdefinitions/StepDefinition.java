package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefinition extends Utils {

    RequestSpecification request;
    ResponseSpecification resSpec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_id;

    @Given("Add Place Payload with {string} {string} {string}")
    public void addPlacePayloadWith(String name, String language, String address) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        request = given().spec(requestSpecification()).log().all()
                .body(data.addPlacePayload(name, language, address));
    }

    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String httpMethod) {
        // Write code here that turns the phrase above into concrete actions
        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if (httpMethod.equalsIgnoreCase("POST"))
            response = request.when().post(resourceAPI.getResource());
        else if (httpMethod.equalsIgnoreCase("GET"))
            response = request.when().get(resourceAPI.getResource());
    }

    @Then("The API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer statusCode) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(String.valueOf(response.getStatusCode()), statusCode.toString());
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(getJsonPath(response, keyValue), expectedValue);

    }

    @And("verify place_Id created maps to {string} using {string}")
    public void verifyPlace_IdCreatedMapsToUsing(String expectedName, String resource) throws IOException {
        // Request Spec
        place_id = getJsonPath(response, "place_id");
        request = given().spec(requestSpecification()).queryParam("place_id", place_id);
        // Call get API
        user_calls_with_http_request(resource, "GET");
        // Get actual name
        String actualName = getJsonPath(response, "name");
        // Validate name
        assertEquals(actualName, expectedName);
    }

    @Given("DeletePlace Payload")
    public void deleteplacePayload() throws IOException {
        request = given().spec(requestSpecification()).body(data.DeletePlacePayload(place_id));
    }
}
