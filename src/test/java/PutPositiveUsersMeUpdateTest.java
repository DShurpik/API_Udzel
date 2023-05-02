import basePage.BaseTestForPatch;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutPositiveUsersMeUpdateTest extends BaseTestForPatch {

    JSONObject test = parser("testCreateUser");

    JSONObject test1 = parser("usersForPutUsersMePositive").getJSONObject("user1"); // email lowercase, name uppercase and lowercase
    JSONObject test2 = parser("usersForPutUsersMePositive").getJSONObject("user2"); // email lowercase and uppercase, name lowercase
    JSONObject test3 = parser("usersForPutUsersMePositive").getJSONObject("user3"); // email starts number, name сyrillic
    JSONObject test4 = parser("usersForPutUsersMePositive").getJSONObject("user4"); // email with several dots, name has digit
    JSONObject test5 = parser("usersForPutUsersMePositive").getJSONObject("user5"); // email has dots in domain, name has special symbols
    JSONObject test6 = parser("usersForPutUsersMePositive").getJSONObject("user6"); // email with "-" in name part, name equals email

    @Test(description = "Email with lower case letters, name with upper case letters")
    public void checkLowerEmailUpperName() {
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
                .response().jsonPath().getString("username"), "SHURdzm");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }

    @Test(description = "email with upper case letters, name with lower case letters")
    public void checkLowerUpperEmailLowerName() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test2.toString())
                .put("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "Ds@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "shurdzmitr");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }

    @Test(description = "email starts number, name has сyrillic letters", enabled = false)
    public void checkEmailStartsNumberNameCyrillic() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test3.toString())
                .put("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "1s@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "дмитрий");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }

    @Test(description = "dots in email name .*. , name contains digit")
    public void checkEmailWithDotsNameWithDigit() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test4.toString())
                .put("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "d.d.s@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "damavik12345");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }

    @Test(description = "name has dots in domain part .*. , name contains special symbols")
    public void checkEmailDomainWithDotsNameWithSymbols() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test5.toString())
                .put("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "ds@example.ru.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "damavik@+.-_");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }

    @Test(description = "email has in name '-' , name equals email")
    public void checkEmailWithDashNameEqualsEmail() {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test6.toString())
                .put("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "dzmitry-shurpik@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "dzmitry-shurpik");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }
}