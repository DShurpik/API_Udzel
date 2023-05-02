import basePage.BaseTestForPatch;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetUsersId extends BaseTestForPatch {

    @Test
    public void getIdTest() {
        String token1 = getTokenFor(test.get("email").toString(), test.get("password").toString());

        Response response = given()
                .header("Authorization" , "Token " + token1)
                .when()
                .get("users/"+ getId() + "/");

        response.then().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("email"), "dzmitry_shurpik@example.com");

        Assert.assertEquals(response.then().extract()
                .jsonPath().getInt("id"), getId());

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("username"), "damavik");

        setPasswordFor(test.get("password").toString());

        setToken(token1);
    }

    JSONObject test = parser("testCreateUser");

}