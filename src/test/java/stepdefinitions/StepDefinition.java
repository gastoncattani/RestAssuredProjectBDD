package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
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
        assertEquals(response.getStatusCode(), statusCode.toString());
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        // Write code here that turns the phrase above into concrete actions
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        assertEquals(js.getString(keyValue), expectedValue);
    }
}
