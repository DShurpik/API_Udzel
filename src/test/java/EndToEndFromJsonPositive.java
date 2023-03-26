import basePage.BaseTest;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class EndToEndFromJsonPositive extends BaseTest {

    JsonObject user1 = parser("users").getAsJsonObject("user1"); // Lower case email
    JsonObject user2 = parser("users").getAsJsonObject("user2"); // Lower and Upper case email
    JsonObject user3 = parser("users").getAsJsonObject("user3"); // Starting with number
    JsonObject user4 = parser("users").getAsJsonObject("user4"); // With several dots, not in a row
    JsonObject user5 = parser("users").getAsJsonObject("user5"); // With several dots in domain part
    JsonObject user6 = parser("users").getAsJsonObject("user6"); // With "-" in name part

    @Test(priority = 1, description = "with lower case email")
    public void createUserWithLowerCase() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user1.toString())   // Подставляем в body json from resources
                .post("users/");

        response.then().log().all().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "ds@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");
    }

    @Test(priority = 2, description = "check info with lower case email")
    public void userInfoMeWithLowerCase() {

        Response response = given()
                .header("Authorization" , "Token "
                        + getToken(user1.get("email").getAsString(), user1.get("password").getAsString()))
                .when()
                .get("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("email"), "ds11@example.com");

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("username"), "damavik");

    }

    @Test(priority = 3, description = "delete user after check info with lower case email")
    public void deleteMeWithLowerCase() {

        String body = "{\n" +
                "    \"current_password\" : \"" + user1.get("password").getAsString() + "\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token "
                        + getToken(user1.get("email").getAsString(), user1.get("password").getAsString()))
                .body(body)
                .when()
                .delete("users/me/");

        response.then().log().all().statusCode(204);
    }

    @Test(priority = 4, description = "lower case and upper case email",enabled = false)
    public void createUserWithLowerCaseAndUpperCase() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user2.toString())   // Подставляем в body json from resources
                .post("users/");

        response.then().log().all().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "ds@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");
    }

    @Test(priority = 5, description = "check info with lower case email", enabled = false)
    public void userInfoMeWithLowerCaseAndUpperCase() {

        Response response = given()
                .header("Authorization" , "Token "
                        + getToken(user2.get("email").getAsString(), user2.get("password").getAsString()))
                .when()
                .get("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("email"), "ds@example.com");

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("username"), "damavik");

    }

    @Test(priority = 6, description = "delete user after check info with lower case email", enabled = false)
    public void deleteMeWithLowerCaseAndUpperCase() {

        String body = "{\n" +
                "    \"current_password\" : \"" + user2.get("password").getAsString() + "\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token "
                        + getToken(user2.get("email").getAsString(), user2.get("password").getAsString()))
                .body(body)
                .when()
                .delete("users/me/");

        response.then().log().all().statusCode(204);
    }

    @Test(priority = 7, description = "With number")
    public void createUserWithNumber() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user3.toString())   // Подставляем в body json from resources
                .post("users/");

        response.then().log().all().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "1s@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");
    }

    @Test(priority = 8, description = "check info starting number")
    public void userInfoMeWithStartingNumber() {

        Response response = given()
                .header("Authorization" , "Token "
                        + getToken(user3.get("email").getAsString(), user3.get("password").getAsString()))
                .when()
                .get("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("email"), "1s@example.com");

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("username"), "damavik");

    }

    @Test(priority = 9, description = "delete user after check info with starting number")
    public void deleteMeWithStartingNumber() {

        String body = "{\n" +
                "    \"current_password\" : \"" + user3.get("password").getAsString() + "\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token "
                        + getToken(user3.get("email").getAsString(), user3.get("password").getAsString()))
                .body(body)
                .when()
                .delete("users/me/");

        response.then().log().all().statusCode(204);
    }

    @Test(priority = 10, description = "With several dots in email name part, not in a row")
    public void createUserWithSeveralDotsInEmailNamePart() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user4.toString())   // Подставляем в body json from resources
                .post("users/");

        response.then().log().all().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "d.d.s@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");
    }

    @Test(priority = 11, description = "check info several dots in email name part")
    public void userInfoMeWithSeveralDotsInEmail() {

        Response response = given()
                .header("Authorization" , "Token "
                        + getToken(user4.get("email").getAsString(), user4.get("password").getAsString()))
                .when()
                .get("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("email"), "d.d.s@example.com");

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("username"), "damavik");

    }

    @Test(priority = 12, description = "delete user after check info with several dots in email name part")
    public void deleteMeWithSeveralDotsInEmail() {

        String body = "{\n" +
                "    \"current_password\" : \"" + user4.get("password").getAsString() + "\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token "
                        + getToken(user4.get("email").getAsString(), user4.get("password").getAsString()))
                .body(body)
                .when()
                .delete("users/me/");

        response.then().log().all().statusCode(204);
    }

    @Test(priority = 13, description = "With several dots in domain part, not in a row")
    public void createUserWithSeveralDotsInDomainPart() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user5.toString())   // Подставляем в body json from resources
                .post("users/");

        response.then().log().all().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "ds@example.ru.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");
    }

    @Test(priority = 14, description = "check info several dots in domain part")
    public void userInfoMeWithSeveralDotsInDomainPart() {

        Response response = given()
                .header("Authorization" , "Token "
                        + getToken(user5.get("email").getAsString(), user5.get("password").getAsString()))
                .when()
                .get("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("email"), "ds@example.ru.com");

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("username"), "damavik");

    }

    @Test(priority = 15, description = "delete user after check info with several dots in domain part")
    public void deleteMeWithSeveralDotsInDomainPart() {

        String body = "{\n" +
                "    \"current_password\" : \"" + user5.get("password").getAsString() + "\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token "
                        + getToken(user5.get("email").getAsString(), user5.get("password").getAsString()))
                .body(body)
                .when()
                .delete("users/me/");

        response.then().log().all().statusCode(204);
    }

    @Test(priority = 16, description = "with '-' in name part")
    public void createUserWith_InNamePart() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user6.toString())   // Подставляем в body json from resources
                .post("users/");

        response.then().log().all().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "d-s@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");
    }

    @Test(priority = 17, description = "check info with '-' in name part")
    public void userInfoMeWith_InName() {

        Response response = given()
                .header("Authorization" , "Token "
                        + getToken(user6.get("email").getAsString(), user6.get("password").getAsString()))
                .when()
                .get("users/me/");

        response.then().log().all().statusCode(200);

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("email"), "d-s@example.com");

        Assert.assertEquals(response.then().extract()
                .jsonPath().getString("username"), "damavik");
    }

    @Test(priority = 18, description = "delete user after check info with '-' in name part")
    public void deleteMeWith_InName() {

        String body = "{\n" +
                "    \"current_password\" : \"" + user6.get("password").getAsString() + "\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token "
                        + getToken(user6.get("email").getAsString(), user6.get("password").getAsString()))
                .body(body)
                .when()
                .delete("users/me/");

        response.then().log().all().statusCode(204);
    }



}
