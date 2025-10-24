package api.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.requests.RequestRegisterNewAccount;
import api.responses.ResponseRegisterNewAccount;
import api.support.BaseTest;
import api.support.DataGenerator;
import api.support.JsonReader;
import support.assertions.ApiAssertions;
import support.logging.TestLog;

import java.util.Map;

public class TestRegisterNewAccount extends BaseTest {

    @Test
    public void registerNewAccount() {
        RequestRegisterNewAccount requestRegisterNewAccount = JsonReader.fromResource("requestdata/request-register-new-user.json",
                Map.of(
                        "email", DataGenerator.randomEmail("api"),
                        "first_name", DataGenerator.randomFirstName(),
                        "last_name", DataGenerator.randomLastName()
                ),
                RequestRegisterNewAccount.class);

        TestLog.info("REGISTER NEW USER REQUEST JSON:\n" + JsonReader.toString(requestRegisterNewAccount, true));
        TestLog.info("POST request for new account creation was sent.");

        Response responseRegisterNewAccount = post("users.register", requestRegisterNewAccount);

        TestLog.info("Protocol and response: " + responseRegisterNewAccount.getStatusLine());
        TestLog.info("Status code: " + responseRegisterNewAccount.getStatusCode());
        TestLog.info("POST response for new account creation was received.");

        ResponseRegisterNewAccount responseBodyRegisterNewAccount;
        try {
            responseBodyRegisterNewAccount = responseRegisterNewAccount.as(ResponseRegisterNewAccount.class);
            // Optional: pretty print the mapped body for diagnostics
            TestLog.info("REGISTER NEW USER RESPONSE (mapped):\n" + JsonReader.toString(responseBodyRegisterNewAccount, true));
        } catch (Exception ex) {
            // Log a readable JSON snapshot, then fail fast
            final String raw = responseRegisterNewAccount.asString();
            try {
                // pretty-print if JSON
                ObjectMapper om = new ObjectMapper();
                String pretty = om.readTree(raw).toPrettyString();
                TestLog.error("Failed to deserialize to ResponseRegisterNewAccount: " + ex.getMessage()
                        + "\nRaw response (pretty):\n" + pretty);
            } catch (Exception ignore) {
                // not valid JSON or pretty-print failed, log raw
                TestLog.error("Failed to deserialize to ResponseRegisterNewAccount: " + ex.getMessage()
                        + "\nRaw response:\n" + raw);
            }
            Assert.fail("Deserialization to ResponseRegisterNewAccount failed", ex);
            return; // keeps compiler happy; test already failed
        }

        Assert.assertEquals(responseRegisterNewAccount.getStatusCode(), 201, "Expected response code 201 received.");
        TestLog.pass("Expected response code 201 received.");

        ApiAssertions.assertUserMatches(requestRegisterNewAccount, responseBodyRegisterNewAccount);
        TestLog.pass("Response body matches the request.");
    }
}

