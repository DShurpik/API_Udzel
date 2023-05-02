import basePage.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUsersIdTest extends BaseTest {


    @BeforeMethod
    public void createUser() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(test.toString())
                .post("users/");

        response.then().statusCode(201);

        setId(response.then().extract().response().jsonPath().getInt("id"));

        System.out.println("!!!     USER WAS CREATED IN BEFORE METHOD     !!!");
    }



    @Test
    public void deleteUserId() {
        String token1 = getToken(test.getString("email"), test.getString("password"));

        String body = "{\n" +
                "    \"current_password\" : \"" + test.getString("password") + "\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " + token1)
                .body(body)
                .when()
                .delete("users/" + getId() + "/");

        response.then().statusCode(204);

        System.out.println(" USER WAS DELETED FROM TEST ");

    }

    JSONObject test = parser("testCreateUser");
}

