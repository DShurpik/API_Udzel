/*import basePage.BaseTestForPatch;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostSetPasswordPositiveTest extends BaseTestForPatch {

    @Test(description = "Use password lowerCase letters only >=8 symbols")
    public void checkPasswordLowercase () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password1.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(204);

        passwordFor = password1.get("new_password").getAsString();

        token = getToken(test.get("email").getAsString(), password1.get("new_password").getAsString());
    }

    @Test(description = "Use password UpperCase letters only >=8 symbols")
    public void checkPasswordUppercase () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password2.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(204);

        passwordFor = password2.get("new_password").getAsString();

        token = getToken(test.get("email").getAsString(), password2.get("new_password").getAsString());
    }

    @Test(description = "Use password lowerCase and UpperCase letters only >=8 symbols")
    public void checkPasswordWithLowercaseAndUppercase () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password3.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(204);

        passwordFor = password3.get("new_password").getAsString();

        token = getToken(test.get("email").getAsString(), password3.get("new_password").getAsString());
    }

    @Test(description = "Use password letters and numbers >=8 symbols")
    public void checkPasswordWithLettersAndNumbers () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password4.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(204);

        passwordFor = password4.get("new_password").getAsString();

        token = getToken(test.get("email").getAsString(), password4.get("new_password").getAsString());
    }

    @Test(description = "Use password special symbols >= 8: ~ ! ? @ # $ % ^ & * _ - + ( ) [ ] { } > < /  |  ' . , : ;")
    public void checkPasswordWithSpecialSymbols () {
        String token1 = getToken(getEmail("testCreateUser"), getPassword("testCreateUser"));

        Response response = given()
                .when()
                .header("Authorization" , "Token " + token1)
                .contentType(ContentType.JSON)
                .body(password5.toString())
                .post("users/set_password/");

        response.then().log().all().statusCode(204);

        passwordFor = password5.get("new_password").getAsString();

        token = getToken(test.get("email").getAsString(), password5.get("new_password").getAsString());
    }


    JsonObject test = parser("testCreateUser").getAsJsonObject();
    JsonObject password1 = parser("passwordsForPostSetPasswordPositive").getAsJsonObject("password1");
    JsonObject password2 = parser("passwordsForPostSetPasswordPositive").getAsJsonObject("password2");
    JsonObject password3 = parser("passwordsForPostSetPasswordPositive").getAsJsonObject("password3");
    JsonObject password4 = parser("passwordsForPostSetPasswordPositive").getAsJsonObject("password4");
    JsonObject password5 = parser("passwordsForPostSetPasswordPositive").getAsJsonObject("password5");
}

 */
