import basePage.BaseTestForPatch;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostSetEmailsNegativeTest extends BaseTestForPatch {

    @Test(description = "Set email with negative new email")
    public void checkNegativeEmail () {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(email1.toString())
                .post("users/set_email/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("new_email"), "[Введите правильный адрес электронной почты.]");

        setPasswordFor(userForTest.getString("password"));

        setToken(token1);
    }

    JSONObject email1 = parser("emailsForPostSetEmailNegative").getJSONObject("email1");
}