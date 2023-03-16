import BasePage.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Delete extends BaseTest {

    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Test(priority = 1)
    public void get_token() {
        String body = "{\n" +
                "  \"email\": \"ds11@example.com\",\n" +
                "  \"password\": \"d2918363\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("jwt/create/");

        response.then().log().all().statusCode(200);

        setAccess_token(response.then().extract().response().jsonPath().getString("access"));
    }

    @Test(priority = 2)
    public void delete_me() {
        String body = "{\n" +
                "    \"current_password\" : \"d2918363\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " + getAccess_token())
                .body(body)
                .when()
                .delete("users/me/");

        response.then().log().all().statusCode(204);

    }
}
