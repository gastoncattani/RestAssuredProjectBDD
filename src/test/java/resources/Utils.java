package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;

    public RequestSpecification requestSpecification() throws IOException {
        if (req == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log)) // Agrega todo lo que hace el request en logging.txt
                    .addFilter(ResponseLoggingFilter.logResponseTo(log)) // Agrega todo lo que hace el response en logging.txt
                    .addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
        }

        return req;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/java/resources/global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

    public String getJsonPath(Response response, String key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.getString(key);
    }
}
