package Examples;

import basePage.BaseTest;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

public class getInfoFromUSers extends BaseTest {

    JsonObject user1 = parser("users").getAsJsonObject("user2");

    @Test
    public void test1() {
       //System.out.println(user1.get("email"));
       //System.out.println(user1.get("username"));
       //System.out.println(user1.get("password"));
       //System.out.println(user1.getAsJsonObject());
    }

}
