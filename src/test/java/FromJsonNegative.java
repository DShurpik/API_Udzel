import basePage.BaseTest;
import basePage.BaseTestWithDelete;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FromJsonNegative extends BaseTest {

    JsonObject user1 = parser("users_negative").getAsJsonObject("user1"); // empty field
    JsonObject user2 = parser("users_negative").getAsJsonObject("user2"); // 255 symbols
    JsonObject user3 = parser("users_negative").getAsJsonObject("user3"); // without @
    JsonObject user4 = parser("users_negative").getAsJsonObject("user4"); // With ..
    JsonObject user5 = parser("users_negative").getAsJsonObject("user5"); // Start from .
    JsonObject user6 = parser("users_negative").getAsJsonObject("user6"); // Domain name starts from .

    @Test(description = "with empty field email")
    public void createUserWithEmptyFieldEmail() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user1.toString())
                .post("users/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "[Это поле не может быть пустым.]");


    }

    @Test(description = "use 255 symbols in email")
    public void createUserWithLimitSymbols() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user2.toString())   // Подставляем в body json from resources
                .post("users/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "[Убедитесь, что это значение содержит не более 254 символов.]");

    }

    @Test(description = "Without @")
    public void createUserWithoutDog() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user3.toString())   // Подставляем в body json from resources
                .post("users/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "[Введите правильный адрес электронной почты.]");

    }

    @Test(description = "With ..")
    public void createUserWithTwoDots() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user4.toString())   // Подставляем в body json from resources
                .post("users/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "[Введите правильный адрес электронной почты.]");
    }

    @Test(description = "Email starts from .")
    public void createUserNameStartsFromDot() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user5.toString())   // Подставляем в body json from resources
                .post("users/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "[Введите правильный адрес электронной почты.]");
    }

    @Test(description = "domain name starts from .")
    public void createUserWith_InNamePart() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user6.toString())   // Подставляем в body json from resources
                .post("users/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "[Введите правильный адрес электронной почты.]");
    }
}
