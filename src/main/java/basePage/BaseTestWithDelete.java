package basePage;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static config.Config.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public abstract class BaseTestWithDelete {

    protected String passwordFor;
    protected String token;

    public String getPasswordFor() {
        return passwordFor;
    }

    public void setPasswordFor(String passwordFor) {
        this.passwordFor = passwordFor;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }

    @BeforeTest
    public void precondition() {
        baseURI = BASE_URI;
        RestAssured.defaultParser = Parser.JSON;
        disableWarning();
    }

    @AfterMethod
    public void deleteUser() {
        String body = "{\n" +
                "    \"current_password\" : \"" + passwordFor + "\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " + getToken())
                .body(body)
                .when()
                .delete("users/me/");

        System.out.println("!!!    USER WAS DELETE FROM AFTER METHOD    !!!");

        response.then().statusCode(204);
    }

    public String getTokenFor(String email, String password) {
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

    public JSONObject parser(String fileName) {
        return new JSONObject(getJson(fileName));
    }

    public String getEmail(String fileName) {
        return parser(fileName).get("email").toString();
    }

    public String getPassword(String fileName) {
        return parser(fileName).get("password").toString();
    }
}
