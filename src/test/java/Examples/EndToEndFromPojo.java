package Examples;

import basePage.BaseTest;
import entity.request.UserRequestForCreate;
import entity.request.UserRequestForInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class EndToEndFromPojo extends BaseTest {

    @Test(priority = 1)
    public void createUser() {

        UserRequestForCreate newUser = new UserRequestForCreate(){{
            setEmail("ds11@example.com");
            setUsername("damavik");
            setPassword("d2918363");
        }};

        Response response = given()
                .contentType(ContentType.JSON)
                .body(newUser)   // Подставляем в body объект класса User_request_for_create
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

        UserRequestForInfo newUserForInfo = new UserRequestForInfo(){{
            setEmail("ds11@example.com");
            setPassword("d2918363");
        }};

        System.out.println(newUserForInfo.getEmail());
        System.out.println(newUserForInfo.getPassword());

        Response response = given()
                .header("Authorization" , "Token "
                        + getToken(newUserForInfo.getEmail(),
                        newUserForInfo.getPassword()))
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

        UserRequestForCreate newUserForDelete = new UserRequestForCreate(){{
            setPassword("d2918363");
        }};

        UserRequestForCreate newUserForToken = new UserRequestForCreate(){{
            setEmail("ds11@example.com");
            setPassword("d2918363");
        }};

        String body = "{\n" +
                "    \"current_password\" : \"" + newUserForDelete.getPassword() + "\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token "
                        + getToken(newUserForToken.getEmail(),
                        newUserForToken.getPassword()))
                .body(body)
                .when()
                .delete("users/me/");

        response.then().log().all().statusCode(204);

    }



}
