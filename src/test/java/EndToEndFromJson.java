import BasePage.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class EndToEndFromJson extends BaseTest {

    @Test(priority = 1)
    public void createUser() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(getJson("testCreateUser"))   // Подставляем в body json from resources
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
    public void userInfoMe() {

        Response response = given()
                .header("Authorization" , "Token "
                        + getToken(getEmail("testCreateUser"), getPassword("testCreateUser")))
                .when()
                .get("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("email"), "ds11@example.com");

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("username"), "damavik");

    }

    @Test(priority = 3)
    public void deleteMe() {

        String body = "{\n" +
                "    \"current_password\" : \"" + getPassword("testCreateUser") + "\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token "
                        + getToken(getEmail("testCreateUser"), getPassword("testCreateUser")))
                .body(body)
                .when()
                .delete("users/me/");

        response.then().log().all().statusCode(204);

    }



}
