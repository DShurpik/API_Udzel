import basePage.BaseTest;
import entity.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetListUsersTest extends BaseTest {

    @Test
    public void getUsersList(){

        Response response = given()
                //.header("Authorization", "Token " + getToken("elena.kuzheleva.exlab@gmail.com", "qwertyQ1_"))
                .queryParam(getToken("elena.kuzheleva.exlab@gmail.com", "qwertyQ1_"))
                //.queryParam()
                .when()
                .get("users/");

        response.then().statusCode(200);

        List<?> users = response.then().extract().jsonPath().getList(".", User.class);

        Assert.assertTrue(users.size() > 0,
                "Don't get users list");
    }
}
