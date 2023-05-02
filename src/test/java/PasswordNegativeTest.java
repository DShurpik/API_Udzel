import basePage.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PasswordNegativeTest extends BaseTest {

    JSONObject user1 = parser("users_password_negative").getJSONObject("user1"); // 7 symbols
    JSONObject user2 = parser("users_password_negative").getJSONObject("user2"); // name + 1
    JSONObject user3 = parser("users_password_negative").getJSONObject("user3"); // equal to email
    JSONObject user4 = parser("users_password_negative").getJSONObject("user4"); // numbers only >= 8 symbols
    JSONObject user5 = parser("users_password_negative").getJSONObject("user5"); // qwerty

    @Test(description = "7 symbols")
    public void createUserWithSymbols() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user1.toString())
                .post("users/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("password"), "[Введённый пароль слишком короткий. Он должен содержать как минимум 8 символов.]");
    }

    @Test(description = "name + 1")
    public void createUserWithNamePlusOne() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user2.toString())
                .post("users/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("password"), "[Введённый пароль слишком похож на username.]");
    }

    @Test(description = "equal email")
    public void createUserWithNameEqualEmail() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user3.toString())
                .post("users/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("password"), "[Введённый пароль слишком похож на email address.]");
    }

    @Test(description = "Only numbers")
    public void createUserWithOnlyNumbers() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user4.toString())
                .post("users/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("password"), "[Введённый пароль состоит только из цифр.]");
    }

    @Test(description = "Popular pass qwerty")
    public void createUserWithPopularPassword() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user5.toString())
                .post("users/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("password"), "[Введённый пароль слишком широко распространён.]");
    }
}