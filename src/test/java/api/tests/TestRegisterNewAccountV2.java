package api.tests;

import api.steps.UserActions;
import api.support.BaseTest;
import org.testng.annotations.Test;

public class TestRegisterNewAccountV2 extends BaseTest {

    @Test
    public void registerNewAccountV2() {
        new UserActions(this)
                .registerNewAccount()
                .receiveResponse()
                .validateResponse();
    }
}
