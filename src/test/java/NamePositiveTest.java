import basePage.BaseTestWithDelete;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class NamePositiveTest extends BaseTestWithDelete {

    JSONObject user1 = parser("users_name_positive").getJSONObject("user1"); // Uppercase and Lowercase letters
    JSONObject user2 = parser("users_name_positive").getJSONObject("user2"); // Cyrillic letters
    JSONObject user3 = parser("users_name_positive").getJSONObject("user3"); // Contains numbers
    JSONObject user4 = parser("users_name_positive").getJSONObject("user4"); // Contains "@+.-_"
    JSONObject user5 = parser("users_name_positive").getJSONObject("user5"); // Equal to email

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

        setPasswordFor(user1.get("password").toString());

        setToken(getTokenFor(user1.get("email").toString(), user1.get("password").toString()));
    }

    @Test(description = "Cyrillic letters. Doesn't work correctly when use JSONObject and start from mvn clean test", enabled = true)
    public void createUserWithCyrillicletters() {

        String body = "{\n" +
                "    \"email\": \"dzmitryshurpik@example.com\",\n" +
                "    \"username\": \"дмитрий\",\n" +
                "    \"password\": \"d2918363\"\n" +
                "  }";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .post("users/");

        response.then().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "dzmitryshurpik@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "дмитрий");

        setPasswordFor(user2.get("password").toString());

        setToken(getTokenFor(user2.get("email").toString(), user2.get("password").toString()));
    }

    @Test(description = "Contains numbers", enabled = true)
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

        setPasswordFor(user3.get("password").toString());

        setToken(getTokenFor(user3.get("email").toString(), user3.get("password").toString()));
    }

    @Test(description = "Contains special symbols", enabled = true)
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

        setPasswordFor(user4.get("password").toString());

        setToken(getTokenFor(user4.get("email").toString(), user4.get("password").toString()));
    }

    @Test(description = "Equal to email", enabled = true)
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

        setPasswordFor(user5.get("password").toString());

        setToken(getTokenFor(user5.get("email").toString(), user5.get("password").toString()));
    }
}