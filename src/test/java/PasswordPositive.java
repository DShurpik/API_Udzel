import basePage.BaseTestWithDelete;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PasswordPositive extends BaseTestWithDelete {

    JsonObject user1 = parser("users_password_positive").getAsJsonObject("user1"); // lowerCase letters only >=8 symbols
    JsonObject user2 = parser("users_password_positive").getAsJsonObject("user2"); // UpperCase letters only >=8 symbols
    JsonObject user3 = parser("users_password_positive").getAsJsonObject("user3"); // lowerCase and UpperCase letters only >=8 symbols
    JsonObject user4 = parser("users_password_positive").getAsJsonObject("user4"); // letters and numbers >=8 symbols
    JsonObject user5 = parser("users_password_positive").getAsJsonObject("user5"); // special symbols >= 8: ~ ! ? @ # $ % ^ & * _ - + ( ) [ ] { } > < / \ | " ' . , : ;

    @Test(description = "lowerCase letters only >=8 symbols")
    public void createUserWithLowerCase() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user1.toString())
                .post("users/");

        response.then().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "ds@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");

        passwordFor = user1.get("password").getAsString();

        token = getToken(user1.get("email").getAsString(), user1.get("password").getAsString());

    }

    @Test(description = "UpperCase letters only >=8 symbols")
    public void createUserWithUpperCase() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user2.toString())
                .post("users/");

        response.then().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "ds@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");

        passwordFor = user2.get("password").getAsString();

        token = getToken(user2.get("email").getAsString(), user2.get("password").getAsString());

    }

    @Test(description = "lowerCase and uppercase letters only >=8 symbols")
    public void createUserWithUpperLowerCase() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user3.toString())
                .post("users/");

        response.then().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "ds@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");

        passwordFor = user3.get("password").getAsString();

        token = getToken(user3.get("email").getAsString(), user3.get("password").getAsString());

    }

    @Test(description = "letters and numbers >=8 symbols")
    public void createUserWithLettersAndNumbers() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user4.toString())
                .post("users/");

        response.then().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "ds@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");

        passwordFor = user4.get("password").getAsString();

        token = getToken(user4.get("email").getAsString(), user4.get("password").getAsString());

    }

    @Test(description = "Special symbols")
    public void createUserWithSpecialSymbols() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user5.toString())
                .post("users/");

        response.then().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "ds@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");

        passwordFor = user5.get("password").getAsString();

        token = getToken(user5.get("email").getAsString(), user5.get("password").getAsString());

    }
}
