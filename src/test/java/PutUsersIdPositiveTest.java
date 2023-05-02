/**

import basePage.BaseTestForPatch;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutUsersIdPositiveTest extends BaseTestForPatch {

    @Test
    public void test1() {
        String token1 = getToken(test.get("email").getAsString(), test.get("password").getAsString());

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test1.toString())
                .put("users/"+ id + "/");

        response.then().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "ds@example.com");

        Assert.assertEquals(response.then().extract()
                .jsonPath().getInt("id"), id);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "dd");

        passwordFor = test.get("password").getAsString();

        token = getToken(test1.get("email").getAsString(), test.get("password").getAsString());
    }

    JsonObject test = parser("testCreateUser").getAsJsonObject();
    JsonObject test1 = parser("putUsersIdPositive").getAsJsonObject("user1");
}

 **/
