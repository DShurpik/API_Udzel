import BasePage.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get_info_me extends BaseTest {

    @Test
    public void user_info_me() {
        System.out.println(getToken());

        Response response = given()
                .header("Authorization" , "Token " + getToken())
                .when()
                .get("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("email"), "ds@example.com");

        Assert.assertEquals(response.then().extract()
                .jsonPath().getInt("id"), 53);

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("username"), "damavik");

    }
}
