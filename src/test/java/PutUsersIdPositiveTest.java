import basePage.BaseTestForPatch;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutUsersIdPositiveTest extends BaseTestForPatch {

    @Test
    public void test1() {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test1.toString())
                .put("users/"+ getId() + "/");

        response.then().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "ds@example.com");

        Assert.assertEquals(response.then().extract()
                .jsonPath().getInt("id"), getId());

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "dd");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    JSONObject test = parser("testCreateUser");
    JSONObject test1 = parser("putUsersIdPositive").getJSONObject("user1");
}