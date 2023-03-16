import BasePage.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get_info_me extends BaseTest {

    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Test
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

    @Test
    public void user_info_me() {
        System.out.println(getAccess_token());

        Response response = given()
                .header("Authorization" , "Token " + getAccess_token())
                .when()
                .get("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("email"), "ds11@example.com");

        Assert.assertEquals(response.then().extract()
                .jsonPath().getInt("id"), 67);

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("username"), "damavik");

    }
}
