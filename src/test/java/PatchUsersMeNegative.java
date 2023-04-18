import basePage.BaseTestForPatch;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PatchUsersMeNegative extends BaseTestForPatch {

    JsonObject test34 = parser("negative_for_patch").getAsJsonObject("test34");
    JsonObject test35 = parser("negative_for_patch").getAsJsonObject("test35");
    JsonObject test36 = parser("negative_for_patch").getAsJsonObject("test36");
    JsonObject test37 = parser("negative_for_patch").getAsJsonObject("test37");
    JsonObject test38 = parser("negative_for_patch").getAsJsonObject("test38");
    JsonObject test39 = parser("negative_for_patch").getAsJsonObject("test39");


    JsonObject test44 = parser("negative_for_patch").getAsJsonObject("test44");
    JsonObject test45 = parser("negative_for_patch").getAsJsonObject("test45");
    JsonObject test46 = parser("negative_for_patch").getAsJsonObject("test46");

    JsonObject tesst = parser("testCreateUser").getAsJsonObject();

    @Test(description = "empty field")
    public void emptyField () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test34.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Это поле не может быть пустым.]");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(tesst.get("email").getAsString(), tesst.get("password").getAsString());
    }

    @Test(description = "255 symbols")
    public void moreSymbols () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test35.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(500);

        passwordFor = tesst.get("password").getAsString();

        token = getToken(tesst.get("email").getAsString(), tesst.get("password").getAsString());
    }

    @Test(description = "without @")
    public void withoutDog () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test36.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(tesst.get("email").getAsString(), tesst.get("password").getAsString());
    }

    @Test(description = "contains ..")
    public void containsTwoDots () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test37.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(tesst.get("email").getAsString(), tesst.get("password").getAsString());
    }

    @Test(description = "starts with .")
    public void startWithDot () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test38.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(tesst.get("email").getAsString(), tesst.get("password").getAsString());
    }

    @Test(description = "domain part starts with .")
    public void domainStartsFromDot () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test39.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(tesst.get("email").getAsString(), tesst.get("password").getAsString());
    }

    @Test(description = "empty field  Name")
    public void emptyFieldInName () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test44.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Это поле не может быть пустым.]");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(tesst.get("email").getAsString(), tesst.get("password").getAsString());
    }

    @Test(description = "151 symbol")
    public void moreSymbolsThan151 () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test45.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Убедитесь, что это значение содержит не более 150 символов.]");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(tesst.get("email").getAsString(), tesst.get("password").getAsString());
    }

    @Test(description = "contains */#  Name")
    public void containsNumbers () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization", "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test46.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Введите правильное имя пользователя. Оно может содержать только буквы, цифры и знаки @/./+/-/_.]");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(tesst.get("email").getAsString(), tesst.get("password").getAsString());
    }
}
