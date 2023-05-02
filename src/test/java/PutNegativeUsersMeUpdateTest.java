import basePage.BaseTestForPatch;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutNegativeUsersMeUpdateTest extends BaseTestForPatch {

    JSONObject test = parser("testCreateUser");
    JSONObject test1 = parser("usersForPutUsersMeNegative").getJSONObject("user1");
    JSONObject test2 = parser("usersForPutUsersMeNegative").getJSONObject("user2");
    JSONObject test3 = parser("usersForPutUsersMeNegative").getJSONObject("user3");
    JSONObject test4 = parser("usersForPutUsersMeNegative").getJSONObject("user4");
    JSONObject test5 = parser("usersForPutUsersMeNegative").getJSONObject("user5");
    JSONObject test6 = parser("usersForPutUsersMeNegative").getJSONObject("user6");
    JSONObject test7 = parser("usersForPutUsersMeNegative").getJSONObject("user7");
    JSONObject test8 = parser("usersForPutUsersMeNegative").getJSONObject("user8");
    JSONObject test9 = parser("usersForPutUsersMeNegative").getJSONObject("user9");
    JSONObject test10 = parser("usersForPutUsersMeNegative").getJSONObject("user10");

    @Test(description = "email is with empty field, name is valid")
    public void checkEmptyEmailValidName() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test1.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Это поле не может быть пустым.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "email has 255 symbols, valid name")
    public void checkEmailWithALotOfSymbolsValidName() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test2.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "email doesn't have @, name is valid")
    public void checkEmailWithoutDogValidName() {
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

    @Test(description = "email has '..' , name is valid")
    public void checkEmailWitsDotsValidName() {
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

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "email starts from '.' , name is valid")
    public void checkEmailStartsFromDotValidName() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test5.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "domain part in email starts from '.' , name is valid")
    public void checkDomainPartStartsFromDotValidName() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test6.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "name part in email ends on '.' , name is valid")
    public void checkNamePartEndsOnDotValidName() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test7.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "[Введите правильный адрес электронной почты.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "email is valid, name is empty")
    public void checkValidEmailNameIsEmpty() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test8.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Это поле не может быть пустым.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "email is valid, name has 151 symbols")
    public void checkValidEmailNameWithALotOfSymbols() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test9.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Убедитесь, что это значение содержит не более 150 символов.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }

    @Test(description = "email is valid, name has special symbols")
    public void checkValidEmailNameHasSpecialSymbols() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test10.toString())
                .put("users/me/");

        response.then().log().all().statusCode(400);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "[Введите правильное имя пользователя. Оно может содержать только буквы, цифры и знаки @/./+/-/_.]");

        setPasswordFor(test.getString("password"));

        setToken(token1);
    }
}