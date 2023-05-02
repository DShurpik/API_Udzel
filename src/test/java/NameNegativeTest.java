import basePage.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class NameNegativeTest extends BaseTest {

    JSONObject user1 = parser("users_name_negative").getJSONObject("user1"); // empty field
    JSONObject user2 = parser("users_name_negative").getJSONObject("user2"); // 151 symbol
    JSONObject user3 = parser("users_name_negative").getJSONObject("user3"); // contains "#"

    @Test(description = "Empty field")
    public void createUserWithEmptyField() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user1.toString())
                .post("users/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "[Это поле не может быть пустым.]");
    }

    @Test(description = "151 symbols")
    public void createUserWithALotOfSymbols() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user2.toString())
                .post("users/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "[Убедитесь, что это значение содержит не более 150 символов.]");
    }

    @Test(description = "contains *")
    public void createUserWithSpecialSymbols() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user3.toString())
                .post("users/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "[Введите правильное имя пользователя. Оно может содержать только буквы, цифры и знаки @/./+/-/_.]");
    }
}