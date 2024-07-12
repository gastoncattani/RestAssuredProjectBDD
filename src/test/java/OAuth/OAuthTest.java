package OAuth;

import io.restassured.path.json.JsonPath;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.given;

public class OAuthTest {

    @Test
    public void TestOAuth() throws InterruptedException {

        // Access to Google login to get code
        System.setProperty("webdriver.chrome.driver", "src/test/java/OAuth/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://accounts.google.com/v3/signin/identifier?opparams=%253Fauth_url%253Dhttps%25253A%25252F%25252Faccounts.google.com%25252Fo%25252Foauth2%25252Fv2%25252Fauth&dsh=S1879847977%3A1720809958780812&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&ddm=0&o2v=2&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&response_type=code&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&service=lso&flowName=GeneralOAuthFlow&continue=https%3A%2F%2Faccounts.google.com%2Fsignin%2Foauth%2Fconsent%3Fauthuser%3Dunknown%26part%3DAJi8hAPrA2y-rmbBvDa96I6pJ58rYztwfpvr4VobLrmHVarIqvPLxqnzLERL0blCerstOBVM_Ni9LK95S_9KBII9W-75IsljOWKyKD18tZTmvtVC4tWrZGI-awyr6Ale7SqLvrouaGTmpM0sRLzmTZYKZkksP5cEJAFj4lcaaXPZQb9AlwESk5_XUt4lpcHQg01cp2zZRo37_aDIyG8miTmRflgxsUVSuY3ce3xe2hiF0C9rKRaUtD0TlLsWMpcCJYv87EJHE1iO4WkuH1guohBELHoQ7s9hdOzdw0xqP9AwBF6e2VxGZNcz6ZP9bidVCAVX4PAdp-SF6hdobbwrWj4OCadsPpztGNmN_-RAxqNJm5kxdguORPPRIAXrqTS_7lrMWzMF1hj6_hNz5-4DnUmV72QBxXMar5g5e1jj6Ijb3Kf8xRNKRyZYHgagkbM4Pm7BaYnMKu-VWjwbVPXkfUHwMuxqXUY9Uw%26flowName%3DGeneralOAuthFlow%26as%3DS1879847977%253A1720809958780812%26client_id%3D692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com%23&app_domain=https%3A%2F%2Frahulshettyacademy.com&rart=ANgoxccfx_Eh9y-NTLKhWNe-HjnyjOdUh8ypUcND1onSfQKCKt1WrVeLdphWNs-5VtfleJBaw-aKbsmMkXM1JgJemeFraBrui9s1RSacda0MHgh6z1DCPXo");

        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
        Thread.sleep(4000);
        String url =  driver.getCurrentUrl();

        // Extract code from url to send to API call
         String partialCode = url.split("code=")[1];
         String code = partialCode.split("&scope")[0];
         System.out.println(code);

        String accessTokenResponse = given().urlEncodingEnabled(false) // To avoid issues with special characters
                .queryParams("code", code)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secrete", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_rui", "https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type", "authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath js = new JsonPath(accessTokenResponse);
        String accessToken = js.getString("access_token");

        String response = given().queryParam("access_token", accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/getCourse.php").asString();

        System.out.println(response);
    }
}
