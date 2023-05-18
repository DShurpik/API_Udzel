import basePage.BaseTestWithDelete;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class EmailPositiveTest extends BaseTestWithDelete {

    JSONObject user1 = parser("users_email_positive").getJSONObject("user1"); // Lower case email
    JSONObject user2 = parser("users_email_positive").getJSONObject("user2"); // Lower and Upper case email
    JSONObject user3 = parser("users_email_positive").getJSONObject("user3"); // Starting with number
    JSONObject user4 = parser("users_email_positive").getJSONObject("user4"); // With several dots, not in a row
    JSONObject user5 = parser("users_email_positive").getJSONObject("user5"); // With several dots in domain part
    JSONObject user6 = parser("users_email_positive").getJSONObject("user6"); // With "-" in name part

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

        setPasswordFor(user1.getString("password"));

        setToken(getTokenFor(user1.getString("email"), user1.getString("password")));

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

        setPasswordFor(user2.getString("password"));

        setToken(getTokenFor(user2.getString("email"), user2.getString("password")));
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

        setPasswordFor(user3.getString("password"));

        setToken(getTokenFor(user3.getString("email"), user3.getString("password")));
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

        setPasswordFor(user4.getString("password"));

        setToken(getTokenFor(user4.getString("email"), user4.getString("password")));
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

        setPasswordFor(user5.getString("password"));

        setToken(getTokenFor(user5.getString("email"), user5.getString("password")));
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

        setPasswordFor(user6.getString("password"));

        setToken(getTokenFor(user6.getString("email"), user6.getString("password")));
    }
}