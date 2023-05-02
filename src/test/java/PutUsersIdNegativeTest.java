import basePage.BaseTestForPatch;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutUsersIdNegativeTest extends BaseTestForPatch {

    @Test
    public void test1() {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test1.toString())
                .put("users/"+ getId() + "/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    JSONObject test = parser("testCreateUser");
    JSONObject test1 = parser("putUsersIdNegative").getJSONObject("user1");
}