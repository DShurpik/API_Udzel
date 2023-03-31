import basePage.BaseTestWithDelete;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class NamePositive extends BaseTestWithDelete {

    JsonObject user1 = parser("users_name_positive").getAsJsonObject("user1"); // Uppercase and Lowercase letters
    JsonObject user2 = parser("users_name_positive").getAsJsonObject("user2"); // Cyrillic letters
    JsonObject user3 = parser("users_name_positive").getAsJsonObject("user3"); // Contains numbers
    JsonObject user4 = parser("users_name_positive").getAsJsonObject("user4"); // Contains "@+.-_"
    JsonObject user5 = parser("users_name_positive").getAsJsonObject("user5"); // Equal to email

    @Test(description = "Uppercase and Lowercase letters")
    public void createUserWithLowerUpperCase() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user1.toString())
                .post("users/");

        response.then().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "dzmitryshurpik@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "SHURdzm");

        passwordFor = user1.get("password").getAsString();

        token = getToken(user1.get("email").getAsString(), user1.get("password").getAsString());
    }

    @Test(description = "Cyrillic letters")
    public void createUserWithCyrillicletters() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user2.toString())
                .post("users/");

        response.then().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "dzmitryshurpik@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "дмитрий");

        passwordFor = user2.get("password").getAsString();

        token = getToken(user2.get("email").getAsString(), user2.get("password").getAsString());
    }

    @Test(description = "Contains numbers")
    public void createUserWithNumbers() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user3.toString())
                .post("users/");

        response.then().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "dzmitryshurpik@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik12345");

        passwordFor = user3.get("password").getAsString();

        token = getToken(user3.get("email").getAsString(), user3.get("password").getAsString());
    }

    @Test(description = "Contains special symbols")
    public void createUserWithSpecialSymbols() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user4.toString())
                .post("users/");

        response.then().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "dzmitryshurpik@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik@+.-_");

        passwordFor = user4.get("password").getAsString();

        token = getToken(user4.get("email").getAsString(), user4.get("password").getAsString());
    }

    @Test(description = "Equal to email")
    public void createUserWithEmailEqualsName() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user5.toString())
                .post("users/");

        response.then().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "dzmitryshurpik@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "dzmitryshurpik");

        passwordFor = user5.get("password").getAsString();

        token = getToken(user5.get("email").getAsString(), user5.get("password").getAsString());
    }
}
