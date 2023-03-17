import BasePage.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class End_to_end_from_json extends BaseTest {

    @Test(priority = 1)
    public void create_user() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(getJson("1_test_create"))   // Подставляем в body json from resources
                .post("users/");

        response.then().log().all().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "ds11@example.com");
        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");
    }

    @Test(priority = 2)
    public void user_info_me() {

        String email = parser("1_test_create").get("email").getAsString();
        String password = parser("1_test_create").get("password").getAsString();

        Response response = given()
                .header("Authorization" , "Token " + getToken(email, password))
                .when()
                .get("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("email"), "ds11@example.com");

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("username"), "damavik");

    }

    @Test(priority = 3)
    public void delete_me() {

        String email = parser("1_test_create").get("email").getAsString();
        String password = parser("1_test_create").get("password").getAsString();

        String body = "{\n" +
                "    \"current_password\" : \"" + password + "\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " + getToken(email, password))
                .body(body)
                .when()
                .delete("users/me/");

        response.then().log().all().statusCode(204);

    }



}
