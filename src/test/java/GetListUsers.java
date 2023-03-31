import basePage.BaseTest;
import entity.User;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetListUsers extends BaseTest {

    @Test
    public void getUsersList(){
        List<?> users = given()
                .header("Authorization", "Token " + getToken("elena.kuzheleva.exlab@gmail.com", "qwertyQ1_"))
                .when()
                .contentType(ContentType.JSON)
                .get("users/")
                .then().log().all().extract().jsonPath().getList("." , User.class);

        Assert.assertTrue(users.size() > 0,
                "Don't get users list");
    }

}
