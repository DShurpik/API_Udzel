import BasePage.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Config.Config.*;
import static Config.Get_token.getToken;
import static io.restassured.RestAssured.given;

public class End_to_end extends BaseTest {

    @Test(priority = 1)
    public void create_user() {
        String body = "{\n" +
                "  \"email\": \"" + EMAIL + "\",\n" +
                "  \"username\": \"" + USERNAME + "\",\n" +
                "  \"password\": \"" + PASSWORD + "\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
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
        System.out.println(getToken());

        Response response = given()
                .header("Authorization" , "Token " + getToken())
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
