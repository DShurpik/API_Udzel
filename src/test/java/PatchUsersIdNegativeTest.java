import basePage.BaseTestForPatch;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PatchUsersIdNegativeTest extends BaseTestForPatch {

    @Test
    public void test1() {

        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test1.toString())
                .patch("users/"+ getId() +"/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Введите правильное имя пользователя. Оно может содержать только буквы, цифры и знаки @/./+/-/_.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    JSONObject test = parser("testCreateUser");
    JSONObject test1 = parser("patchUsersIdNegative").getJSONObject("user1");
}