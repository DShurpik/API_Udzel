package Config;

import io.restassured.response.Response;

import static Config.Config.*;
import static io.restassured.RestAssured.given;

public class Get_token {

    public static String getToken() {
        String body = "{\n" +
                "  \"email\": \"" + EMAIL + "\",\n" +
                "  \"password\": \"" + PASSWORD + "\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("jwt/create/");

        response.then().log().all().statusCode(200);

        return response.then().extract().response().jsonPath().getString("access");
    }

}
