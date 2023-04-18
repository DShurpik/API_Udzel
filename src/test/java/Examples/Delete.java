package Examples;

import basePage.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static config.Config.*;
import static io.restassured.RestAssured.given;

public class Delete extends BaseTest {

    @Test
    public void deleteMe() {
        String body = "{\n" +
                "    \"current_password\" : \"" + PASSWORD + "\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " + getToken())
                .body(body)
                .when()
                .delete("users/me/");

        response.then().log().all().statusCode(204);

    }
}
