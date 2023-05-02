import basePage.BaseTestWithDelete;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PasswordPositiveTest extends BaseTestWithDelete {

    JSONObject user1 = parser("users_password_positive").getJSONObject("user1"); // lowerCase letters only >=8 symbols
    JSONObject user2 = parser("users_password_positive").getJSONObject("user2"); // UpperCase letters only >=8 symbols
    JSONObject user3 = parser("users_password_positive").getJSONObject("user3"); // lowerCase and UpperCase letters only >=8 symbols
    JSONObject user4 = parser("users_password_positive").getJSONObject("user4"); // letters and numbers >=8 symbols
    JSONObject user5 = parser("users_password_positive").getJSONObject("user5"); // special symbols >= 8: ~ ! ? @ # $ % ^ & * _ - + ( ) [ ] { } > < / \ | " ' . , : ;

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

        setPasswordFor(user1.getString("password"));

        setToken(getTokenFor(user1.getString("email"), user1.getString("password")));

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

        setPasswordFor(user2.getString("password"));

        setToken(getTokenFor(user2.getString("email"), user2.getString("password")));

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

        setPasswordFor(user3.getString("password"));

        setToken(getTokenFor(user3.getString("email"), user3.getString("password")));

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

        setPasswordFor(user4.getString("password"));

        setToken(getTokenFor(user4.getString("email"), user4.getString("password")));

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

        setPasswordFor(user5.getString("password"));

        setToken(getTokenFor(user5.getString("email"), user5.getString("password")));

    }
}