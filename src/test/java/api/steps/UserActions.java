package api.steps;

import api.requests.RequestRegisterNewAccount;
import api.responses.ResponseRegisterNewAccount;
import api.support.BaseTest;
import api.support.DataGenerator;
import api.support.JsonReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import support.assertions.ApiAssertions;
import support.logging.TestLog;

import java.util.Map;

public class UserActions {

    private final BaseTest ctx;

    private RequestRegisterNewAccount request;
    private Response rawResponse;
    private ResponseRegisterNewAccount mappedResponseBody;

    public UserActions(BaseTest ctx) {
        this.ctx = ctx;
    }

    public UserActions registerNewAccount() {
        request = JsonReader.fromResource("requestdata/request-register-new-user.json",
                Map.of(
                        "email", DataGenerator.randomEmail("api"),
                        "first_name", DataGenerator.randomFirstName(),
                        "last_name", DataGenerator.randomLastName()
                ),
                RequestRegisterNewAccount.class);
        TestLog.info("REGISTER NEW USER REQUEST JSON:\n" + JsonReader.toString(request, true));
        return this;
    }

    public UserActions receiveResponse() {
        TestLog.info("POST request for new account creation was sent.");
        rawResponse = ctx.post("users.register", request);
        TestLog.info("Protocol and response: " + rawResponse.getStatusLine());
        TestLog.info("Status code: " + rawResponse.getStatusCode());
        TestLog.info("POST response for new account creation was received.");

        try {
            mappedResponseBody = rawResponse.as(ResponseRegisterNewAccount.class);
            TestLog.info("REGISTER NEW USER RESPONSE (mapped):\n"
                    + JsonReader.toString(mappedResponseBody, true));
        } catch (Exception ex) {
            final String raw = rawResponse.asString();
            try {
                ObjectMapper om = new ObjectMapper();
                String pretty = om.readTree(raw).toPrettyString();
                TestLog.error("Failed to deserialize to ResponseRegisterNewAccount: " + ex.getMessage()
                        + "\nRaw response (pretty):\n" + pretty);
            } catch (Exception ignore) {
                TestLog.error("Failed to deserialize to ResponseRegisterNewAccount: " + ex.getMessage()
                        + "\nRaw response:\n" + raw);
            }
            Assert.fail("Deserialization to ResponseRegisterNewAccount failed", ex);
        }
        return this;
    }

    public void validateResponse() {
        Assert.assertEquals(rawResponse.getStatusCode(), 201, "Expected response code 201 received.");
        TestLog.pass("Expected response code 201 received.");
        ApiAssertions.assertUserMatches(request, mappedResponseBody);
        TestLog.pass("Response body matches the request.");
    }

}
