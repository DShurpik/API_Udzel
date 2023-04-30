import basePage.BaseTestForPatch;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostSetEmailsNegativeTest extends BaseTestForPatch {

    @Test(description = "Set email with negative new email")
    public void checkNegativeEmail () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(email1.toString())
                .post("users/set_email/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("new_email"), "[Введите правильный адрес электронной почты.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    JsonObject test = parser("testCreateUser").getAsJsonObject();
    JsonObject email1 = parser("emailsForPostSetEmailNegative").getAsJsonObject("email1");

}
