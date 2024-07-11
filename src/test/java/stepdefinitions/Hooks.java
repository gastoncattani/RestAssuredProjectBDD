package stepdefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        // Write a code that will give you place it|
        // Execute this code only when place id is null

        StepDefinition m = new StepDefinition();

        if(StepDefinition.place_id == null || StepDefinition.place_id.isEmpty()) {
            m.addPlacePayloadWith("Noelia", "Italian", "Argentina");
            m.user_calls_with_http_request("ADDPLACEAPI", "POST");
            m.verifyPlace_IdCreatedMapsToUsing("Noelia", "GETPLACEAPI");
        }
    }
}
