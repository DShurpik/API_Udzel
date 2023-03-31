import basePage.BaseTestWithDelete;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FromJsonPositive extends BaseTestWithDelete {

    JsonObject user1 = parser("users").getAsJsonObject("user1"); // Lower case email
    JsonObject user2 = parser("users").getAsJsonObject("user2"); // Lower and Upper case email
    JsonObject user3 = parser("users").getAsJsonObject("user3"); // Starting with number
    JsonObject user4 = parser("users").getAsJsonObject("user4"); // With several dots, not in a row
    JsonObject user5 = parser("users").getAsJsonObject("user5"); // With several dots in domain part
    JsonObject user6 = parser("users").getAsJsonObject("user6"); // With "-" in name part

    @Test(description = "with lower case email")
    public void createUserWithLowerCase() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user1.toString())
                .post("users/");

        response.then().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "ds@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");

        passwordFor = user1.get("password").getAsString();

        token = getToken(user1.get("email").getAsString(), user1.get("password").getAsString());

    }

    @Test(enabled = false) // Bug ??????????
    public void createUserWithLowerCaseAndUpperCase() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user2.toString())   // Подставляем в body json from resources
                .post("users/");

        response.then().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "ds@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");

        passwordFor = user2.get("password").getAsString();

        token = getToken(user2.get("email").getAsString(), user2.get("password").getAsString());
    }

    @Test(description = "With number")
    public void createUserWithNumber() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user3.toString())   // Подставляем в body json from resources
                .post("users/");

        response.then().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "1s@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");

        passwordFor = user3.get("password").getAsString();

        token = getToken(user3.get("email").getAsString(), user3.get("password").getAsString());
    }

    @Test(description = "With several dots in email name part, not in a row")
    public void createUserWithSeveralDotsInEmailNamePart() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user4.toString())   // Подставляем в body json from resources
                .post("users/");

        response.then().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "d.d.s@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");

        passwordFor = user4.get("password").getAsString();

        token = getToken(user4.get("email").getAsString(), user4.get("password").getAsString());
    }

    @Test(description = "With several dots in domain part, not in a row")
    public void createUserWithSeveralDotsInDomainPart() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user5.toString())   // Подставляем в body json from resources
                .post("users/");

        response.then().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "ds@example.ru.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");

        passwordFor = user5.get("password").getAsString();

        token = getToken(user5.get("email").getAsString(), user5.get("password").getAsString());
    }

    @Test(description = "with '-' in name part")
    public void createUserWith_InNamePart() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user6.toString())   // Подставляем в body json from resources
                .post("users/");

        response.then().statusCode(201);

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("email"), "d-s@example.com");

        Assert.assertEquals(response.then().extract()
                .response().jsonPath()
                .getString("username"), "damavik");

        passwordFor = user6.get("password").getAsString();

        token = getToken(user6.get("email").getAsString(), user6.get("password").getAsString());
    }
}
