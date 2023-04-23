package basePage;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static config.Config.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BaseTestForPatch {

    protected String passwordFor;
    protected String token;
    protected JsonObject userForTest = parser("testCreateUser").getAsJsonObject();

    @BeforeTest
    public void precondition() {
        baseURI = BASE_URI;
    }

    @BeforeMethod
    public void createUser() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(userForTest.toString())
                .post("users/");

        response.then().log().all().statusCode(201);

        System.out.println("!!!     USER WAS CREATED IN BEFORE METHOD     !!!");
    }

    @AfterMethod
    public void deleteUser() {
        String body = "{\n" +
                "    \"current_password\" : \"" + passwordFor + "\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " + token)
                .body(body)
                .when()
                .delete("users/me/");

        System.out.println("!!!    USER WAS DELETE FROM AFTER METHOD    !!!");

        response.then().statusCode(204);
    }

    public String getToken() {
        String body = "{\n" +
                "  \"email\": \"" + EMAIL + "\",\n" +
                "  \"password\": \"" + PASSWORD + "\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("jwt/create/");

        response.then().statusCode(200);

        return response.then().extract().response().jsonPath().getString("access");
    }

    public String getToken(String email, String password) {
        String body = "{\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("jwt/create/");

        response.then().statusCode(200);

        return response.then().extract().response().jsonPath().getString("access");
    }

    public String getJson(String fileName){
        try {
            return new String(Files.readAllBytes(Paths.get("src/test/resources/" + fileName + ".json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonObject parser(String fileName) {
        JsonObject jsonObject = new JsonParser()
                .parse(getJson(fileName))
                .getAsJsonObject();
        return jsonObject;
    }

    public String getEmail(String fileName) {
        return parser(fileName).get("email").getAsString();
    }

    public String getPassword(String fileName) {
        return parser(fileName).get("password").getAsString();
    }
}
