import basePage.BaseTestForPatch;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PatchUsersMePositiveTest extends BaseTestForPatch {

    JSONObject test34 = parser("positive_for_patch").getJSONObject("test34"); // lower case email
    JSONObject test35 = parser("positive_for_patch").getJSONObject("test35"); // lower case and upper case
    JSONObject test36 = parser("positive_for_patch").getJSONObject("test36"); // Start from number
    JSONObject test37 = parser("positive_for_patch").getJSONObject("test37"); // With several dots
    JSONObject test38 = parser("positive_for_patch").getJSONObject("test38"); // With several dots in domain parts
    JSONObject test39 = parser("positive_for_patch").getJSONObject("test39"); // With - in name


    JSONObject test44 = parser("positive_for_patch").getJSONObject("test44");
    JSONObject test45 = parser("positive_for_patch").getJSONObject("test45");
    JSONObject test46 = parser("positive_for_patch").getJSONObject("test46");
    JSONObject test47 = parser("positive_for_patch").getJSONObject("test47");
    JSONObject test48 = parser("positive_for_patch").getJSONObject("test48");

    JSONObject test = parser("testCreateUser");

    @Test(description = "lowerCase letters only")
    public void lowerCaseOnly () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test34.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "shurpik_dzmitry@example.com");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }

    @Test(description = "lowerCase and uppercase letters only")
    public void lowerCaseAndUpperCase () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test35.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "SHURPIK_dzmitry@example.com");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }

    @Test(description = "starting with number")
    public void startsWithNumber () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test36.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "1SHURPIK_dzmitry@example.com");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }



    @Test(description = "with several dots in name part, not in a row")
    public void withDots () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test37.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "SHURPIK.dzmitr.y@example.com");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }

    @Test(description = "with several dots in domain part, not in a row")
    public void withDotsInDomain () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test38.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "SHURPIK_dzmitry@exa.mp.le.com");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }

    @Test(description = "with - in name part")
    public void withDashInName () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test39.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("email"), "SHURPIK-dzmitry@exa.mp.le.com");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }

    @Test(description = "Uppercase and Lowercase letters  Name")
    public void uppAndLowCaseInName () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test44.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "SHURPIKdzmitry");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }

    @Test(description = "Cyrillic letters  Name" , enabled = false)
    public void cyrillic () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test45.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "ШурпикДмитрий");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }

    @Test(description = "Contains numbers  Name")
    public void containsNumbers () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test46.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "SHURPIKdzmitry1234");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }

    @Test(description = "Contains @+.-_  Name")
    public void containsSpecialSymbols () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test47.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "SHURPIK+_-dzmitry.");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }

    @Test(description = "Equal to email")
    public void equalEmail () {
        String token1 = getTokenFor(test.getString("email"), test.getString("password"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(test48.toString())
                .patch("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath().getString("username"), "dzmitry_shurpik@example.com");

        setPasswordFor(test.getString("password"));

        setToken(getTokenFor(response.then().extract().response().jsonPath().getString("email"),
                test.getString("password")));
    }
}