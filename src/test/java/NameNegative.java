import basePage.BaseTest;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class NameNegative extends BaseTest {

    JsonObject user1 = parser("users_name_negative").getAsJsonObject("user1"); // empty field
    JsonObject user2 = parser("users_name_negative").getAsJsonObject("user2"); // 151 symbol
    JsonObject user3 = parser("users_name_negative").getAsJsonObject("user3"); // contains "*/#"
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

    @Test(description = "contains */#")
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
