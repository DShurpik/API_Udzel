import BasePage.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Config.Config.*;
import static Config.Get_token.getToken;
import static io.restassured.RestAssured.given;

public class Delete extends BaseTest {

    @Test
    public void delete_me() {
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
