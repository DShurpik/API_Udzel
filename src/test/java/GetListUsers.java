import basePage.BaseTest;
import entity.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetListUsers extends BaseTest {

    @Test
    public void getUsersList(){

        Response response = given()
                .header("Authorization", "Token " + getToken("elena.kuzheleva.exlab@gmail.com", "qwertyQ1_"))
                .when()
                .get("users/");

        response.then().log().all().statusCode(200);

        List<?> users = response.then().extract().jsonPath().getList(".", User.class);

        Assert.assertTrue(users.size() > 0,
                "Don't get users list");
    }

}
