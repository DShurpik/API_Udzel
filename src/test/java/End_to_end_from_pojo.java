import BasePage.BaseTest;
import Entity.Request.User_request_for_create;
import Entity.Request.User_request_for_info;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class End_to_end_from_pojo extends BaseTest {

    @Test(priority = 1)
    public void create_user() {

        User_request_for_create new_user = new User_request_for_create(){{
            setEmail("ds11@example.com");
            setUsername("damavik");
            setPassword("d2918363");
        }};

        Response response = given()
                .contentType(ContentType.JSON)
                .body(new_user)   // Подставляем в body объект класса User_request_for_create
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

        User_request_for_info new_user_for_info = new User_request_for_info(){{
            setEmail("ds11@example.com");
            setPassword("d2918363");
        }};

        System.out.println(new_user_for_info.getEmail());
        System.out.println(new_user_for_info.getPassword());

        Response response = given()
                .header("Authorization" , "Token "
                        + getToken(new_user_for_info.getEmail(),
                        new_user_for_info.getPassword()))
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

        User_request_for_create new_user_for_delete = new User_request_for_create(){{
            setPassword("d2918363");
        }};

        User_request_for_create new_user_for_token = new User_request_for_create(){{
            setEmail("ds11@example.com");
            setPassword("d2918363");
        }};

        String body = "{\n" +
                "    \"current_password\" : \"" + new_user_for_delete.getPassword() + "\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token "
                        + getToken(new_user_for_token.getEmail(),
                        new_user_for_token.getPassword()))
                .body(body)
                .when()
                .delete("users/me/");

        response.then().log().all().statusCode(204);

    }



}
