import basePage.BaseTestForPatch;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostSetPasswordNegativeTest extends BaseTestForPatch {

    @Test(description = "Use password 7 symbols")
    public void checkPasswordLowercase () {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password1.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("new_password"), "[Введённый пароль слишком короткий. Он должен содержать как минимум 8 символов.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "Use password equal to name + 1")
    public void checkPasswordEqualsToNameWithOne () {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password2.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("new_password"), "[Введённый пароль слишком похож на username.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "Use password equals to email")
    public void checkPasswordEqualsToEmail () {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password3.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("new_password"), "[Введённый пароль слишком похож на email address.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "Use password numbers only >= 8 symbols")
    public void checkPasswordOnlyNumbers () {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password4.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("new_password"), "[Введённый пароль состоит только из цифр.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "Use popular password")
    public void checkPopularPassword () {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password5.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("new_password"), "[Введённый пароль слишком широко распространён.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    JSONObject test = parser("testCreateUser");
    JSONObject password1 = parser("passwordsForPostSetPasswordNegative").getJSONObject("password1");
    JSONObject password2 = parser("passwordsForPostSetPasswordNegative").getJSONObject("password2");
    JSONObject password3 = parser("passwordsForPostSetPasswordNegative").getJSONObject("password3");
    JSONObject password4 = parser("passwordsForPostSetPasswordNegative").getJSONObject("password4");
    JSONObject password5 = parser("passwordsForPostSetPasswordNegative").getJSONObject("password5");
}