import basePage.BaseTest;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PasswordNegative extends BaseTest {

    JsonObject user1 = parser("users_password_negative").getAsJsonObject("user1"); // 7 symbols
    JsonObject user2 = parser("users_password_negative").getAsJsonObject("user2"); // name + 1
    JsonObject user3 = parser("users_password_negative").getAsJsonObject("user3"); // equal to email
    JsonObject user4 = parser("users_password_negative").getAsJsonObject("user4"); // numbers only >= 8 symbols
    JsonObject user5 = parser("users_password_negative").getAsJsonObject("user5"); // qwerty

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
