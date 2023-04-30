import basePage.BaseTestForPatch;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostSetEmailsPositiveTest extends BaseTestForPatch {

    @Test(description = "Set email ")
    public void checkSetEmail () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(email1.toString())
                .post("users/set_email/");

        response.then().statusCode(204);

        passwordFor = email1.get("current_password").getAsString();

        token = getToken(email1.get("new_email").getAsString(), email1.get("current_password").getAsString());
    }

    JsonObject email1 = parser("emailsForPostSetEmailPositive").getAsJsonObject("email1");

}
