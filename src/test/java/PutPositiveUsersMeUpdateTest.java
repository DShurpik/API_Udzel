import basePage.BaseTestForPatch;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutPositiveUsersMeUpdateTest extends BaseTestForPatch {

    JsonObject test = parser("testCreateUser").getAsJsonObject();

    JsonObject test1 = parser("usersForPutUsersMePositive").getAsJsonObject("user1"); // email lowercase, name uppercase and lowercase
    JsonObject test2 = parser("usersForPutUsersMePositive").getAsJsonObject("user2"); // email lowercase and uppercase, name lowercase
    JsonObject test3 = parser("usersForPutUsersMePositive").getAsJsonObject("user3"); // email starts number, name сyrillic
    JsonObject test4 = parser("usersForPutUsersMePositive").getAsJsonObject("user4"); // email with several dots, name has digit
    JsonObject test5 = parser("usersForPutUsersMePositive").getAsJsonObject("user5"); // email has dots in domain, name has special symbols
    JsonObject test6 = parser("usersForPutUsersMePositive").getAsJsonObject("user6"); // email with "-" in name part, name equals email

    @Test(description = "Email with lower case letters, name with upper case letters")
    public void checkLowerEmailUpperName() {
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
                .response().jsonPath().getString("username"), "SHURdzm");

        passwordFor = test.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                test.get("password").getAsString());
    }

    @Test(description = "email with upper case letters, name with lower case letters")
    public void checkLowerUpperEmailLowerName() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

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

        passwordFor = test.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                test.get("password").getAsString());
    }

    @Test(description = "email starts number, name has сyrillic letters")
    public void checkEmailStartsNumberNameCyrillic() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

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

        passwordFor = test.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                test.get("password").getAsString());
    }

    @Test(description = "dots in email name .*. , name contains digit")
    public void checkEmailWithDotsNameWithDigit() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

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

        passwordFor = test.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                test.get("password").getAsString());
    }

    @Test(description = "name has dots in domain part .*. , name contains special symbols")
    public void checkEmailDomainWithDotsNameWithSymbols() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

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

        passwordFor = test.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                test.get("password").getAsString());
    }

    @Test(description = "email has in name '-' , name equals email")
    public void checkEmailWithDashNameEqualsEmail() {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

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

        passwordFor = test.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                test.get("password").getAsString());
    }

}
