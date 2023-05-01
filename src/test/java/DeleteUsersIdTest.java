import basePage.BaseTest;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static config.Config.PASSWORD;
import static io.restassured.RestAssured.given;

public class DeleteUsersIdTest extends BaseTest {


    @BeforeMethod
    public void createUser() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(test.toString())
                .post("users/");

        response.then().log().all().statusCode(201);

        id = response.then().extract().response().jsonPath().getInt("id");

        System.out.println("!!!     USER WAS CREATED IN BEFORE METHOD     !!!");
    }



    @Test
    public void deleteUserId() {
        String token1 = getToken(test.get("email").getAsString(), test.get("password").getAsString());

        String body = "{\n" +
                "    \"current_password\" : \"" + test.get("password").getAsString() + "\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " + token1)
                .body(body)
                .when()
                .delete("users/" + id + "/");

        response.then().statusCode(204);

        System.out.println(" USER WAS DELETED FROM TEST ");

    }

    JsonObject test = parser("testCreateUser").getAsJsonObject();
}
