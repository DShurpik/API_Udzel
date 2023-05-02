/*import basePage.BaseTestForPatch;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutPairwaiseTest extends BaseTestForPatch {

    @Test(description = "valid email, valid name")
    public void testValidEmailValidName() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

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

        passwordFor = test.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                test.get("password").getAsString());
    }

    @Test(description = "valid email, invalid name")
    public void testValidEmailInvalidName() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test2.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Введите правильное имя пользователя. Оно может содержать только буквы, цифры и знаки @/./+/-/_.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    @Test(description = "invalid email, valid name")
    public void testInvalidEmailValidName() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test3.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    @Test(description = "invalid email, invalid name")
    public void testInvalidEmailInvalidName() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

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

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    @Test(description = "invalid email, empty field name")
    public void testInvalidEmailEmptyName() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test5.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Это поле не может быть пустым.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    @Test(description = "invalid email, empty field name")
    public void testValidNameEmptyEmail() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test6.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Это поле не может быть пустым.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    JsonObject test = parser("testCreateUser").getAsJsonObject();
    JsonObject test1 = parser("putPairwaise").getAsJsonObject("user1");
    JsonObject test2 = parser("putPairwaise").getAsJsonObject("user2");
    JsonObject test3 = parser("putPairwaise").getAsJsonObject("user3");
    JsonObject test4 = parser("putPairwaise").getAsJsonObject("user4");
    JsonObject test5 = parser("putPairwaise").getAsJsonObject("user5");
    JsonObject test6 = parser("putPairwaise").getAsJsonObject("user6");

}


 */
