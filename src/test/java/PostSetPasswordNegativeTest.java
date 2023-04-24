import basePage.BaseTestForPatch;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostSetPasswordNegativeTest extends BaseTestForPatch {

    @Test(description = "Use password 7 symbols")
    public void checkPasswordLowercase () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password1.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("new_password"), "[Введённый пароль слишком короткий. Он должен содержать как минимум 8 символов.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    @Test(description = "Use password equal to name + 1")
    public void checkPasswordEqualsToNameWithOne () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password2.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("new_password"), "[Введённый пароль слишком похож на username.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    @Test(description = "Use password equals to email")
    public void checkPasswordEqualsToEmail () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password3.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("new_password"), "[Введённый пароль слишком похож на email address.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    @Test(description = "Use password numbers only >= 8 symbols")
    public void checkPasswordOnlyNumbers () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password4.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("new_password"), "[Введённый пароль состоит только из цифр.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    @Test(description = "Use popular password")
    public void checkPopularPassword () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password5.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("new_password"), "[Введённый пароль слишком широко распространён.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    JsonObject test = parser("testCreateUser").getAsJsonObject();
    JsonObject password1 = parser("passwordsForPostSetPasswordNegative").getAsJsonObject("password1");
    JsonObject password2 = parser("passwordsForPostSetPasswordNegative").getAsJsonObject("password2");
    JsonObject password3 = parser("passwordsForPostSetPasswordNegative").getAsJsonObject("password3");
    JsonObject password4 = parser("passwordsForPostSetPasswordNegative").getAsJsonObject("password4");
    JsonObject password5 = parser("passwordsForPostSetPasswordNegative").getAsJsonObject("password5");
}
