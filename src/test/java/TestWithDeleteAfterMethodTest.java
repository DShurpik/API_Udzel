import basePage.BaseTestWithDelete;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestWithDeleteAfterMethodTest extends BaseTestWithDelete {
    JSONObject user1 = parser("users").getJSONObject("user1"); // Lower case email

    @Test
    public void testWithDeleteAfterMethod() {
        // Examples.Create user

        Response responseCreate = given()
                .contentType(ContentType.JSON)
                .body(user1.toString())
                .post("users/");

        responseCreate.then().statusCode(201);

        Assert.assertEquals(responseCreate.then().extract()
                .response().jsonPath()
                .getString("email"), "ds@example.com");

        Assert.assertEquals(responseCreate.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");

        setPasswordFor(user1.getString("password"));

        setToken(getTokenFor(user1.getString("email"), user1.getString("password")));
    }
}