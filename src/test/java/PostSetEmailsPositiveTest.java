import basePage.BaseTestForPatch;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostSetEmailsPositiveTest extends BaseTestForPatch {

    @Test(description = "Set email ")
    public void checkSetEmail () {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(email1.toString())
                .post("users/set_email/");

        response.then().statusCode(204);

        setPasswordFor(email1.getString("current_password"));

        setToken(token1);
    }

    JSONObject email1 = parser("emailsForPostSetEmailPositive").getJSONObject("email1");

}
