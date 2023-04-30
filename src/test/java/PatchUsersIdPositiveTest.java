import basePage.BaseTestForPatch;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

public class PatchUsersIdPositiveTest extends BaseTestForPatch {

    @Test
    public void test1 () {

    }

    JsonObject test = parser("testCreateUser").getAsJsonObject();
}
