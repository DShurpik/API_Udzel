package BasePage;

import org.testng.annotations.BeforeTest;

import static Config.Config.*;
import static io.restassured.RestAssured.baseURI;

public class BaseTest {

    @BeforeTest
    public void precondition() {
        baseURI = BASE_URI;
    }
}
