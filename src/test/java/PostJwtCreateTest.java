import basePage.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostJwtCreateTest extends BaseTest {

    JSONObject test = parser("testCreateUser");

    @BeforeTest
    public void createUser() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(test.toString())
                .post("users/");

        response.then().statusCode(201);

        System.out.println("!!!---USER WAS CREATED IN BEFORE METHOD---!!!");
    }

    @AfterTest
    public void deleteUser() {
        String body = "{\n" +
                "    \"current_password\" : \"" + test.getString("password") + "\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " + getToken(test.getString("email"), test.getString("password")))
                .body(body)
                .when()
                .delete("users/me/");

        System.out.println("!!!---USER WAS DELETE FROM AFTER METHOD---!!!");

        response.then().statusCode(204);
    }

    @Test(description = "with valid login + valid password")
    public void testValidEmailValidPass() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(test1.toString())
                .post("jwt/create/");

        response.then().statusCode(200);
        System.out.println("TEST 1 : TEST VALID EMAIL AND VALID PASSWORD");
    }

    @Test(description = "with valid email + invalid password")
    public void testValidEmailInvalidPass() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(test2.toString())
                .post("jwt/create/");

        response.then().statusCode(401);

        Assert.assertEquals(response.then().extract().response().jsonPath().getString("detail"),
                "No active account found with the given credentials");

        System.out.println("TEST 2 : TEST VALID EMAIL AND INVALID PASSWORD");
    }

    @Test(description = "with invalid email + valid password")
    public void testInvalidEmailValidPass() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(test3.toString())
                .post("jwt/create/");

        response.then().statusCode(401);

        Assert.assertEquals(response.then().extract().response().jsonPath().getString("detail"),
                "No active account found with the given credentials");

        System.out.println("TEST 3 : TEST INVALID EMAIL AND VALID PASSWORD");
    }

    @Test(description = "with invalid email + invalid password")
    public void testInvalidEmailInvalidPass() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(test4.toString())
                .post("jwt/create/");

        response.then().statusCode(401);

        Assert.assertEquals(response.then().extract().response().jsonPath().getString("detail"),
                "No active account found with the given credentials");

        System.out.println("TEST 4 : TEST INVALID EMAIL AND INVALID PASSWORD");
    }

    @Test(description = "with empty field email valid password")
    public void testEmptyFieldEmailValidPass() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(test5.toString())
                .post("jwt/create/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract().response().jsonPath().getString("email"),
                "[Это поле не может быть пустым.]");

        System.out.println("TEST 5 : TEST EMPTY FIELD EMAIL AND VALID PASSWORD");
    }

    @Test(description = "with valid email empty field password")
    public void testValidEmailEmptyFieldPass() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(test6.toString())
                .post("jwt/create/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract().response().jsonPath().getString("password"),
                "[Это поле не может быть пустым.]");

        System.out.println("TEST 6 : TEST VALID EMAIL AND EMPTY FIELD PASSWORD");
    }

    @Test(description = "with empty field email empty field password")
    public void testEmptyEmailEmptyPass() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(test7.toString())
                .post("jwt/create/");

        response.then().statusCode(400);

        Assert.assertEquals(response.then().extract().response().jsonPath().getString("email"),
                "[Это поле не может быть пустым.]");

        Assert.assertEquals(response.then().extract().response().jsonPath().getString("password"),
                "[Это поле не может быть пустым.]");

        System.out.println("TEST 7 : TEST EMPTY FIELD EMAIL AND EMPTY FIELD PASSWORD");
    }



    JSONObject test1 = parser("postJwtCreate").getJSONObject("user1");
    JSONObject test2 = parser("postJwtCreate").getJSONObject("user2");
    JSONObject test3 = parser("postJwtCreate").getJSONObject("user3");
    JSONObject test4 = parser("postJwtCreate").getJSONObject("user4");
    JSONObject test5 = parser("postJwtCreate").getJSONObject("user5");
    JSONObject test6 = parser("postJwtCreate").getJSONObject("user6");
    JSONObject test7 = parser("postJwtCreate").getJSONObject("user7");
}