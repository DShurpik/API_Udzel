import basePage.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class EmailNegativeTest extends BaseTest {

    JSONObject user1 = parser("users_email_negative").getJSONObject("user1"); // empty field
    JSONObject user2 = parser("users_email_negative").getJSONObject("user2"); // 255 symbols
    JSONObject user3 = parser("users_email_negative").getJSONObject("user3"); // without @
    JSONObject user4 = parser("users_email_negative").getJSONObject("user4"); // With ..
    JSONObject user5 = parser("users_email_negative").getJSONObject("user5"); // Start from .
    JSONObject user6 = parser("users_email_negative").getJSONObject("user6"); // Domain name starts from .

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