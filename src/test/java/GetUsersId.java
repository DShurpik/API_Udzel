import basePage.BaseTestForPatch;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetUsersId extends BaseTestForPatch {

    @Test
    public void getId() {
        String token1 = getToken(test.get("email").getAsString(), test.get("password").getAsString());

        Response response = given()
                .header("Authorization" , "Token " + token1)
                .when()
                .get("users/"+ id + "/");

        response.then().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("email"), "dzmitry_shurpik@example.com");

        Assert.assertEquals(response.then().extract()
                .jsonPath().getInt("id"), id);

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("username"), "damavik");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    JsonObject test = parser("testCreateUser").getAsJsonObject();

}
