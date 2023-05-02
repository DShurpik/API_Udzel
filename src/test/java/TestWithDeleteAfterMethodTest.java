/**

import basePage.BaseTestWithDelete;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestWithDeleteAfterMethodTest extends BaseTestWithDelete {
    JsonObject user1 = parser("users").getAsJsonObject("user1"); // Lower case email

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

        passwordFor = user1.get("password").getAsString();

        token = getToken(user1.get("email").getAsString(), user1.get("password").getAsString());
    }
}

 **/