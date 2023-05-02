import basePage.BaseTestForPatch;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PatchUsersMeNegativeTest extends BaseTestForPatch {

    JSONObject test34 = parser("negative_for_patch").getJSONObject("test34");
    JSONObject test35 = parser("negative_for_patch").getJSONObject("test35");
    JSONObject test36 = parser("negative_for_patch").getJSONObject("test36");
    JSONObject test37 = parser("negative_for_patch").getJSONObject("test37");
    JSONObject test38 = parser("negative_for_patch").getJSONObject("test38");
    JSONObject test39 = parser("negative_for_patch").getJSONObject("test39");


    JSONObject test44 = parser("negative_for_patch").getJSONObject("test44");
    JSONObject test45 = parser("negative_for_patch").getJSONObject("test45");
    JSONObject test46 = parser("negative_for_patch").getJSONObject("test46");

    JSONObject test = parser("testCreateUser");

    @Test(description = "empty field")
    public void emptyField () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test34.toString())
                .patch("users/me/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Это поле не может быть пустым.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "255 symbols")
    public void moreSymbols () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test35.toString())
                .patch("users/me/");

        response.then().statusCode(500);

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "without @")
    public void withoutDog () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test36.toString())
                .patch("users/me/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "contains ..")
    public void containsTwoDots () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test37.toString())
                .patch("users/me/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "starts with .")
    public void startWithDot () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test38.toString())
                .patch("users/me/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "domain part starts with .")
    public void domainStartsFromDot () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test39.toString())
                .patch("users/me/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "empty field  Name")
    public void emptyFieldInName () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test44.toString())
                .patch("users/me/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Это поле не может быть пустым.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "151 symbol")
    public void moreSymbolsThan151 () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test45.toString())
                .patch("users/me/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Убедитесь, что это значение содержит не более 150 символов.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "contains *#  Name")
    public void containsNumbers () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization", "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test46.toString())
                .patch("users/me/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Введите правильное имя пользователя. Оно может содержать только буквы, цифры и знаки @/./+/-/_.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }
}