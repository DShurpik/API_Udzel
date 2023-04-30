import basePage.BaseTestForPatch;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutUsersIdNegativeTest extends BaseTestForPatch {

    @Test
    public void test1() {
        String token1 = getToken(test.get("email").getAsString(), test.get("password").getAsString());

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test1.toString())
                .put("users/"+ id + "/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    JsonObject test = parser("testCreateUser").getAsJsonObject();
    JsonObject test1 = parser("putUsersIdNegative").getAsJsonObject("user1");
}
