import basePage.BaseTestForPatch;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutPairwaiseTest extends BaseTestForPatch {

    @Test(description = "valid email, valid name")
    public void testValidEmailValidName() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test1.toString())
                .put("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "ds@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "Dmitry");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }

    @Test(description = "valid email, invalid name")
    public void testValidEmailInvalidName() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test2.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Введите правильное имя пользователя. Оно может содержать только буквы, цифры и знаки @/./+/-/_.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "invalid email, valid name")
    public void testInvalidEmailValidName() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test3.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "invalid email, invalid name")
    public void testInvalidEmailInvalidName() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test4.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Введите правильное имя пользователя. Оно может содержать только буквы, цифры и знаки @/./+/-/_.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "invalid email, empty field name")
    public void testInvalidEmailEmptyName() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test5.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Это поле не может быть пустым.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "invalid email, empty field name")
    public void testValidNameEmptyEmail() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test6.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Это поле не может быть пустым.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    JSONObject test = parser("testCreateUser");
    JSONObject test1 = parser("putPairwaise").getJSONObject("user1");
    JSONObject test2 = parser("putPairwaise").getJSONObject("user2");
    JSONObject test3 = parser("putPairwaise").getJSONObject("user3");
    JSONObject test4 = parser("putPairwaise").getJSONObject("user4");
    JSONObject test5 = parser("putPairwaise").getJSONObject("user5");
    JSONObject test6 = parser("putPairwaise").getJSONObject("user6");

}