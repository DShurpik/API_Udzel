import basePage.BaseTestForPatch;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PatchUsersMePositive extends BaseTestForPatch {

    JsonObject test34 = parser("positive_for_patch").getAsJsonObject("test34"); // lower case email
    JsonObject test35 = parser("positive_for_patch").getAsJsonObject("test35"); // lower case and upper case
    JsonObject test36 = parser("positive_for_patch").getAsJsonObject("test36"); // Start from number
    JsonObject test37 = parser("positive_for_patch").getAsJsonObject("test37"); // With several dots
    JsonObject test38 = parser("positive_for_patch").getAsJsonObject("test38"); // With several dots in domain parts
    JsonObject test39 = parser("positive_for_patch").getAsJsonObject("test39"); // With - in name


    JsonObject test44 = parser("positive_for_patch").getAsJsonObject("test44");
    JsonObject test45 = parser("positive_for_patch").getAsJsonObject("test45");
    JsonObject test46 = parser("positive_for_patch").getAsJsonObject("test46");
    JsonObject test47 = parser("positive_for_patch").getAsJsonObject("test47");
    JsonObject test48 = parser("positive_for_patch").getAsJsonObject("test48");

    JsonObject tesst = parser("testCreateUser").getAsJsonObject();

    @Test(description = "lowerCase letters only")
    public void lowerCaseOnly () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test34.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "shurpik_dzmitry@example.com");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                tesst.get("password").getAsString());
    }

    @Test(description = "lowerCase and uppercase letters only")
    public void lowerCaseAndUpperCase () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test35.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "SHURPIK_dzmitry@example.com");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                tesst.get("password").getAsString());
    }

    @Test(description = "starting with number")
    public void startsWithNumber () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test36.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "1SHURPIK_dzmitry@example.com");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                tesst.get("password").getAsString());
    }


    /** Баг, при  SHURPIK.dzmitr.y@ все ок, при SHURPIK.dzmitry.@ баг**/
    @Test(description = "with several dots in name part, not in a row", enabled = true)
    public void withDots () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test37.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "SHURPIK.dzmitr.y@example.com");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                tesst.get("password").getAsString());
    }

    @Test(description = "with several dots in domain part, not in a row")
    public void withDotsInDomain () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test38.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "SHURPIK_dzmitry@exa.mp.le.com");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                tesst.get("password").getAsString());
    }

    @Test(description = "with - in name part")
    public void withDashInName () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test39.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "SHURPIK-dzmitry@example.com");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                tesst.get("password").getAsString());
    }

    @Test(description = "Uppercase and Lowercase letters  Name")
    public void uppAndLowCaseInName () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test44.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "SHURPIKdzmitry");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                tesst.get("password").getAsString());
    }

    @Test(description = "Cyrillic letters  Name")
    public void cyrillic () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test45.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "ШурпикДмитрий");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                tesst.get("password").getAsString());
    }

    @Test(description = "Contains numbers  Name")
    public void containsNumbers () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test46.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "SHURPIKdzmitry1234");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                tesst.get("password").getAsString());
    }

    @Test(description = "Contains @+.-_  Name")
    public void containsSpecialSymbols () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test47.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "SHURPIK+_-dzmitry.");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                tesst.get("password").getAsString());
    }

    @Test(description = "Equal to email")
    public void equalEmail () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test48.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "dzmitry_shurpik@example.com");

        passwordFor = tesst.get("password").getAsString();

        token = getToken(response.then().extract().response().jsonPath().getString("email"),
                tesst.get("password").getAsString());
    }



}
