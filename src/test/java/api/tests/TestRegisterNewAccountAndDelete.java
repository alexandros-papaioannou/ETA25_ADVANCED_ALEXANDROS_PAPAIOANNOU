package api.tests;

import api.requests.RequestLogin;
import api.requests.RequestRegisterNewAccount;
import api.responses.ResponseLogin;
import api.responses.ResponseLogout;
import api.responses.ResponseRegisterNewAccount;
import api.responses.ResponseUnauthorizedLogin;
import api.support.BaseTest;
import api.support.DataGenerator;
import api.support.JsonReader;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import support.logging.LoggerUtility;
import ui.support.utils.extent.ExtentUtility;
import ui.support.utils.extent.ReportStep;

import java.util.Map;

public class TestRegisterNewAccountAndDelete extends BaseTest {

    @Test
    public void registerNewAccountAndDelete() {
        RequestRegisterNewAccount requestRegisterNewAccount = JsonReader.fromResource("requestdata/request-register-new-user.json",
                Map.of(
                        "email", DataGenerator.randomEmail("api"),
                        "first_name", DataGenerator.randomFirstName(),
                        "last_name", DataGenerator.randomLastName()
                ),
                RequestRegisterNewAccount.class);
        System.out.println("REGISTER NEW USER REQUEST JSON:\n" + JsonReader.toString(requestRegisterNewAccount, true) + "\n");
        LoggerUtility.infoLog("POST request for new account creation was sent." + "\n");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"POST request for new account creation was sent.");


        Response responseRegisterNewAccount = post("users.register", requestRegisterNewAccount);

        System.out.println("Protocol and response: " + responseRegisterNewAccount.getStatusLine());
        System.out.println("Status code: " + responseRegisterNewAccount.getStatusCode() + "\n");
        LoggerUtility.infoLog("POST response for new account creation was received." + "\n");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"POST response for new account creation was received.");


        try {
            RequestRegisterNewAccount echoed = responseRegisterNewAccount.as(RequestRegisterNewAccount.class);
            System.out.println("RESPONSE mapped to Account:\n" + JsonReader.toString(echoed, true));
        } catch (Exception ex) {
            JsonNode node = responseRegisterNewAccount.as(JsonNode.class);
            System.out.println("REGISTER NEW USER RESPONSE JSON:\n" + node.toPrettyString() + "\n");
        }

        ResponseRegisterNewAccount responseBodyRegisterNewAccount = responseRegisterNewAccount.as(ResponseRegisterNewAccount.class);

        Assert.assertEquals(responseRegisterNewAccount.getStatusCode(),201);
        LoggerUtility.infoLog("Expected response code 201 received.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Expected response code 201 received.");
        Assert.assertEquals(requestRegisterNewAccount.getFirstName(),
                responseBodyRegisterNewAccount.getFirstName());
        LoggerUtility.infoLog("First name from request matches first name from response.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"First name from request matches first name from response.");
        Assert.assertEquals(requestRegisterNewAccount.getLastName(),
                responseBodyRegisterNewAccount.getLastName());
        LoggerUtility.infoLog("Last name from request matches last name from response.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Last name from request matches last name from response.");
        Assert.assertEquals(requestRegisterNewAccount.getPhone(),
                responseBodyRegisterNewAccount.getPhone());
        LoggerUtility.infoLog("Phone number from request matches phone number from response.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Phone number from request matches phone number from response.");
        Assert.assertEquals(requestRegisterNewAccount.getDob(),
                responseBodyRegisterNewAccount.getDob());
        LoggerUtility.infoLog("Date of birth from request matches date of birth from response.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Date of birth from request matches date of birth from response.");
        Assert.assertEquals(requestRegisterNewAccount.getEmail(),
                responseBodyRegisterNewAccount.getEmail());
        LoggerUtility.infoLog("Email from request matches email from response.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Email from request matches email from response.");
        Assert.assertNotNull(responseBodyRegisterNewAccount.getId());
        LoggerUtility.infoLog("Response contains an id.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Response contains an id.");
        Assert.assertNotNull(responseBodyRegisterNewAccount.getCreatedAt());
        LoggerUtility.infoLog("Response contains a creation date and time.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Response contains a creation date and time.");
        Assert.assertEquals(requestRegisterNewAccount.getAddress().getStreet(),
                responseBodyRegisterNewAccount.getAddress().getStreet());
        Assert.assertEquals(requestRegisterNewAccount.getAddress().getCity(),
                responseBodyRegisterNewAccount.getAddress().getCity());
        Assert.assertEquals(requestRegisterNewAccount.getAddress().getState(),
                responseBodyRegisterNewAccount.getAddress().getState());
        Assert.assertEquals(requestRegisterNewAccount.getAddress().getCountry(),
                responseBodyRegisterNewAccount.getAddress().getCountry());
        Assert.assertEquals(requestRegisterNewAccount.getAddress().getPostalCode(),
                responseBodyRegisterNewAccount.getAddress().getPostalCode());
        LoggerUtility.infoLog("Response contains a full address and matches with the request.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Response contains a full address and matches with the request.");

        RequestLogin requestLoginAsAdmin = JsonReader.fromResource("requestdata/request-login-admin.json", RequestLogin.class);

        System.out.println("LOGIN AS ADMIN REQUEST JSON:\n" + JsonReader.toString(requestLoginAsAdmin, true) + "\n");
        LoggerUtility.infoLog("POST request for login as admin was sent." + "\n");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "POST request for login as admin was sent.");

        Response responseLoginAsAdmin = post("users.login", requestLoginAsAdmin);

        System.out.println("Protocol and response: " + responseLoginAsAdmin.getStatusLine());
        System.out.println("Status code: " + responseLoginAsAdmin.getStatusCode() + "\n");
        LoggerUtility.infoLog("POST response for login as admin was received." + "\n");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "POST response for login as admin was received.");

        try {
            RequestLogin echoed = responseLoginAsAdmin.as(RequestLogin.class);
            System.out.println("RESPONSE mapped to Account:\n" + JsonReader.toString(echoed, true));
        } catch (Exception ex) {
            JsonNode node = responseLoginAsAdmin.as(JsonNode.class);
            System.out.println("LOGIN AS ADMIN RESPONSE JSON:\n" + node.toPrettyString() + "\n");
        }

        ResponseLogin responseLoginAsAdminBody = responseLoginAsAdmin.as(ResponseLogin.class);

        Assert.assertEquals(responseLoginAsAdmin.getStatusCode(), 200);
        LoggerUtility.infoLog("Expected response code 200 received.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Expected response code 200 received.");
        Assert.assertNotNull(responseLoginAsAdminBody.getAccessToken());
        LoggerUtility.infoLog("Response contains a token.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Response contains a token.");
        Assert.assertEquals(responseLoginAsAdminBody.getTokenType(), "bearer");
        LoggerUtility.infoLog("Token type is bearer.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Token type is bearer.");
        Assert.assertEquals(responseLoginAsAdminBody.getExpiresIn(), 300);
        LoggerUtility.infoLog("Token expires in 300s.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Token expires in 300s.");

        Map<String, String> headers = Map.of("Authorization", "Bearer" + responseLoginAsAdminBody.getAccessToken());

        LoggerUtility.infoLog("DELETE request for deleting customer was sent." + "\n");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "DELETE request for deleting customer was sent.");

        Response deleteCustomer = delete("users.delete", headers, Map.of("userId", responseBodyRegisterNewAccount.getId()));

        System.out.println("Protocol and response: " + deleteCustomer.getStatusLine());
        System.out.println("Status code: " + deleteCustomer.getStatusCode() + "\n");
        LoggerUtility.infoLog("DELETE response for deleting customer was received." + "\n");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "DELETE response for deleting customer was received.");

        Assert.assertEquals(deleteCustomer.getStatusCode(), 204);
        LoggerUtility.infoLog("Expected response code 204 received.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Expected response code 204 received.");

        LoggerUtility.infoLog("GET request for logging out as admin was sent." + "\n");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "GET request for logging out as admin was sent.");

        Response responseLogoutAsAdmin = get("users.logout", headers);

        System.out.println("Protocol and response: " + responseLogoutAsAdmin.getStatusLine());
        System.out.println("Status code: " + responseLogoutAsAdmin.getStatusCode() + "\n");
        LoggerUtility.infoLog("GET response for logging out as admin was received. Admin user successfully logged out." + "\n");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "GET response for logging out as admin was received. Admin user successfully logged out.");

        JsonNode node = responseLogoutAsAdmin.as(JsonNode.class);
        System.out.println("LOGOUT AS ADMIN RESPONSE JSON:\n" + node.toPrettyString() + "\n");

        ResponseLogout responseBodyLogoutAsAdmin = responseLogoutAsAdmin.as(ResponseLogout.class);

        Assert.assertEquals(responseLogoutAsAdmin.getStatusCode(), 200);
        LoggerUtility.infoLog("Expected response code 200 received.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Expected response code 200 received.");
        Assert.assertEquals(responseBodyLogoutAsAdmin.getMessage(), "Successfully logged out");
        LoggerUtility.infoLog("Logout message successfully received.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Logout message successfully received.");

        RequestLogin requestLogin = JsonReader.fromResource("requestdata/request-login.json",
                Map.of("email", requestRegisterNewAccount.getEmail()), RequestLogin.class);

        System.out.println("LOGIN REQUEST WITH DELETED USER JSON:\n" + JsonReader.toString(requestLogin, true) + "\n");
        LoggerUtility.infoLog("POST request for login with deleted user was sent." + "\n");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "POST request for login with deleted user was sent.");

        Response responseLogin = post("users.login", requestLogin);

        System.out.println("Protocol and response: " + responseLogin.getStatusLine());
        System.out.println("Status code: " + responseLogin.getStatusCode() + "\n");
        LoggerUtility.infoLog("POST response for login with deleted user was received." + "\n");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "POST response for login with deleted user was received.");

        try {
            RequestLogin echoed = responseLogin.as(RequestLogin.class);
            System.out.println("RESPONSE mapped to Account:\n" + JsonReader.toString(echoed, true));
        } catch (Exception ex) {
            JsonNode nodeResponseLogin = responseLogin.as(JsonNode.class);
            System.out.println("LOGIN WITH DELETED USER RESPONSE JSON:\n" + nodeResponseLogin.toPrettyString() + "\n");
        }

        ResponseUnauthorizedLogin responseLoginWithDeletedUserBody = responseLogin.as(ResponseUnauthorizedLogin.class);

        Assert.assertEquals(responseLogin.getStatusCode(), 401);
        LoggerUtility.infoLog("Expected response code 401 received.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Expected response code 401 received.");
        Assert.assertEquals(responseLoginWithDeletedUserBody.getError(), "Unauthorized");
        LoggerUtility.infoLog("Unauthorized message received. User was successfully deleted and can no longer be used.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Unauthorized message received. User was successfully deleted and can no longer be used.");

    }
}
