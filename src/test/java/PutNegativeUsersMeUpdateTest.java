import basePage.BaseTest;
import basePage.BaseTestForPatch;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutNegativeUsersMeUpdateTest extends BaseTestForPatch {

    JsonObject test = parser("testCreateUser").getAsJsonObject();
    JsonObject test1 = parser("usersForPutUsersMeNegative").getAsJsonObject("user1");
    JsonObject test2 = parser("usersForPutUsersMeNegative").getAsJsonObject("user2");
    JsonObject test3 = parser("usersForPutUsersMeNegative").getAsJsonObject("user3");
    JsonObject test4 = parser("usersForPutUsersMeNegative").getAsJsonObject("user4");
    JsonObject test5 = parser("usersForPutUsersMeNegative").getAsJsonObject("user5");
    JsonObject test6 = parser("usersForPutUsersMeNegative").getAsJsonObject("user6");
    JsonObject test7 = parser("usersForPutUsersMeNegative").getAsJsonObject("user7");
    JsonObject test8 = parser("usersForPutUsersMeNegative").getAsJsonObject("user8");
    JsonObject test9 = parser("usersForPutUsersMeNegative").getAsJsonObject("user9");
    JsonObject test10 = parser("usersForPutUsersMeNegative").getAsJsonObject("user10");

    @Test(description = "email is with empty field, name is valid")
    public void checkEmptyEmailValidName() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test1.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Это поле не может быть пустым.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    @Test(description = "email has 255 symbols, valid name")
    public void checkEmailWithALotOfSymbolsValidName() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test2.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    @Test(description = "email doesn't have @, name is valid")
    public void checkEmailWithoutDogValidName() {
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

    @Test(description = "email has '..' , name is valid")
    public void checkEmailWitsDotsValidName() {
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

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    @Test(description = "email starts from '.' , name is valid")
    public void checkEmailStartsFromDotValidName() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test5.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    @Test(description = "domain part in email starts from '.' , name is valid")
    public void checkDomainPartStartsFromDotValidName() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test6.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    @Test(description = "name part in email ends on '.' , name is valid")
    public void checkNamePartEndsOnDotValidName() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test7.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    @Test(description = "email is valid, name is empty")
    public void checkValidEmailNameIsEmpty() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test8.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Это поле не может быть пустым.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    @Test(description = "email is valid, name has 151 symbols")
    public void checkValidEmailNameWithALotOfSymbols() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test9.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Убедитесь, что это значение содержит не более 150 символов.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }

    @Test(description = "email is valid, name has special symbols")
    public void checkValidEmailNameHasSpecialSymbols() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test10.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Введите правильное имя пользователя. Оно может содержать только буквы, цифры и знаки @/./+/-/_.]");

        passwordFor = test.get("password").getAsString();

        token = getToken(test.get("email").getAsString(), test.get("password").getAsString());
    }
}
