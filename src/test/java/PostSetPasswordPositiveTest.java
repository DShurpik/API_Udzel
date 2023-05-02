import basePage.BaseTestForPatch;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostSetPasswordPositiveTest extends BaseTestForPatch {

    @Test(description = "Use password lowerCase letters only >=8 symbols")
    public void checkPasswordLowercase () {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password1.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(204);

        setPasswordFor(password1.getString("new_password"));

        setToken(token1);
    }

    @Test(description = "Use password UpperCase letters only >=8 symbols")
    public void checkPasswordUppercase () {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password2.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(204);

        setPasswordFor(password2.getString("new_password"));

        setToken(token1);
    }

    @Test(description = "Use password lowerCase and UpperCase letters only >=8 symbols")
    public void checkPasswordWithLowercaseAndUppercase () {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password3.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(204);

        setPasswordFor(password3.getString("new_password"));

        setToken(token1);
    }

    @Test(description = "Use password letters and numbers >=8 symbols")
    public void checkPasswordWithLettersAndNumbers () {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password4.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(204);

        setPasswordFor(password4.getString("new_password"));

        setToken(token1);
    }

    @Test(description = "Use password special symbols >= 8: ~ ! ? @ # $ % ^ & * _ - + ( ) [ ] { } > < /  |  ' . , : ;")
    public void checkPasswordWithSpecialSymbols () {
        String token1 = getTokenFor(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password5.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(204);

        setPasswordFor(password5.getString("new_password"));

        setToken(token1);
    }


    JSONObject test = parser("testCreateUser");
    JSONObject password1 = parser("passwordsForPostSetPasswordPositive").getJSONObject("password1");
    JSONObject password2 = parser("passwordsForPostSetPasswordPositive").getJSONObject("password2");
    JSONObject password3 = parser("passwordsForPostSetPasswordPositive").getJSONObject("password3");
    JSONObject password4 = parser("passwordsForPostSetPasswordPositive").getJSONObject("password4");
    JSONObject password5 = parser("passwordsForPostSetPasswordPositive").getJSONObject("password5");
}
