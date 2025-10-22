package api.tests;

import api.requests.RequestLogin;
import api.requests.RequestRegisterNewAccount;
import api.responses.ResponseLogin;
import api.responses.ResponseRegisterNewAccount;
import api.responses.ResponseRetrieveCurrentCustomerInfo;
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

public class TestRegisterNewAccountGetCustomerInfo extends BaseTest {

    @Test
    public void registerNewAccountGetCustomerInfo() {

        RequestRegisterNewAccount requestRegisterNewAccount = JsonReader.fromResource("requestdata/request-register-new-user.json",
                Map.of(
                        "email", DataGenerator.randomEmail("api"),
                        "first_name", DataGenerator.randomFirstName(),
                        "last_name", DataGenerator.randomLastName()
                ),
                RequestRegisterNewAccount.class);

        System.out.println("REGISTER NEW USER REQUEST JSON:\n" + JsonReader.toString(requestRegisterNewAccount, true) + "\n");
        LoggerUtility.infoLog("POST request for new account creation was sent." + "\n");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "POST request for new account creation was sent.");


        Response responseRegisterNewAccount = post("users.register", requestRegisterNewAccount);

        System.out.println("Protocol and responseRegisterNewAccount: " + responseRegisterNewAccount.getStatusLine());
        System.out.println("Status code: " + responseRegisterNewAccount.getStatusCode() + "\n");
        LoggerUtility.infoLog("POST responseRegisterNewAccount for new account creation was received." + "\n");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "POST responseRegisterNewAccount for new account creation was received.");


        try {
            RequestRegisterNewAccount echoed = responseRegisterNewAccount.as(RequestRegisterNewAccount.class);
            System.out.println("RESPONSE mapped to Account:\n" + JsonReader.toString(echoed, true));
        } catch (Exception ex) {
            JsonNode node = responseRegisterNewAccount.as(JsonNode.class);
            System.out.println("REGISTER NEW USER RESPONSE JSON:\n" + node.toPrettyString() + "\n");
        }

        ResponseRegisterNewAccount responseBodyRegisterNewAccount = responseRegisterNewAccount.as(ResponseRegisterNewAccount.class);

        Assert.assertEquals(responseRegisterNewAccount.getStatusCode(), 201);
        LoggerUtility.infoLog("Expected response code 201 received.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Expected response code 201 received.");
        Assert.assertEquals(requestRegisterNewAccount.getFirstName(),
                responseBodyRegisterNewAccount.getFirstName());
        LoggerUtility.infoLog("First name from request matches first name from response.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "First name from request matches first name from response.");
        Assert.assertEquals(requestRegisterNewAccount.getLastName(),
                responseBodyRegisterNewAccount.getLastName());
        LoggerUtility.infoLog("Last name from request matches last name from response.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Last name from request matches last name from response.");
        Assert.assertEquals(requestRegisterNewAccount.getPhone(),
                responseBodyRegisterNewAccount.getPhone());
        LoggerUtility.infoLog("Phone number from request matches phone number from response.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Phone number from request matches phone number from response.");
        Assert.assertEquals(requestRegisterNewAccount.getDob(),
                responseBodyRegisterNewAccount.getDob());
        LoggerUtility.infoLog("Date of birth from request matches date of birth from response.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Date of birth from request matches date of birth from response.");
        Assert.assertEquals(requestRegisterNewAccount.getEmail(),
                responseBodyRegisterNewAccount.getEmail());
        LoggerUtility.infoLog("Email from request matches email from response.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Email from request matches email from response.");
        Assert.assertNotNull(responseBodyRegisterNewAccount.getId());
        LoggerUtility.infoLog("Response contains an id.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Response contains an id.");
        Assert.assertNotNull(responseBodyRegisterNewAccount.getCreatedAt());
        LoggerUtility.infoLog("Response contains a creation date and time.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Response contains a creation date and time.");
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
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Response contains a full address and matches with the request.");

        RequestLogin requestLogin = JsonReader.fromResource("requestdata/request-login.json",
                Map.of("email", requestRegisterNewAccount.getEmail()), RequestLogin.class);

        System.out.println("LOGIN REQUEST JSON:\n" + JsonReader.toString(requestLogin, true) + "\n");
        LoggerUtility.infoLog("POST request for login was sent." + "\n");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "POST request for login was sent.");

        Response responseLogin = post("users.login", requestLogin);

        System.out.println("Protocol and response: " + responseLogin.getStatusLine());
        System.out.println("Status code: " + responseLogin.getStatusCode() + "\n");
        LoggerUtility.infoLog("POST response for login was received." + "\n");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "POST response for login was received.");

        try {
            RequestLogin echoed = responseLogin.as(RequestLogin.class);
            System.out.println("RESPONSE mapped to Account:\n" + JsonReader.toString(echoed, true));
        } catch (Exception ex) {
            JsonNode node = responseLogin.as(JsonNode.class);
            System.out.println("LOGIN RESPONSE JSON:\n" + node.toPrettyString() + "\n");
        }

        ResponseLogin responseLoginBody = responseLogin.as(ResponseLogin.class);

        Assert.assertEquals(responseLogin.getStatusCode(), 200);
        LoggerUtility.infoLog("Expected response code 200 received.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Expected response code 200 received.");
        Assert.assertNotNull(responseLoginBody.getAccessToken());
        LoggerUtility.infoLog("Response contains a token.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Response contains a token.");
        Assert.assertEquals(responseLoginBody.getTokenType(), "bearer");
        LoggerUtility.infoLog("Token type is bearer.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Token type is bearer.");
        Assert.assertEquals(responseLoginBody.getExpiresIn(), 300);
        LoggerUtility.infoLog("Token expires in 300s.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Token expires in 300s.");

        Map<String, String> headers = Map.of("Authorization", "Bearer" + responseLoginBody.getAccessToken());

        LoggerUtility.infoLog("GET request for retrieving customer info was sent." + "\n");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "GET request for retrieving customer info was sent.");

        Response responseGetCustomerInfo = get("users.me", headers);

        System.out.println("Protocol and response: " + responseGetCustomerInfo.getStatusLine());
        System.out.println("Status code: " + responseGetCustomerInfo.getStatusCode() + "\n");
        LoggerUtility.infoLog("GET response for retrieving customer info was received." + "\n");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "GET response for retrieving customer info was received.");

        JsonNode node = responseGetCustomerInfo.as(JsonNode.class);
        System.out.println("GET CUSTOMER INFO RESPONSE JSON:\n" + node.toPrettyString() + "\n");

        ResponseRetrieveCurrentCustomerInfo responseBodyGetCustomerInfo = responseGetCustomerInfo.as(ResponseRetrieveCurrentCustomerInfo.class);

        Assert.assertEquals(responseLogin.getStatusCode(), 200);
        LoggerUtility.infoLog("Expected response code 200 received.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Expected response code 200 received.");
        Assert.assertEquals(responseBodyGetCustomerInfo.getId(), responseBodyRegisterNewAccount.getId());
        LoggerUtility.infoLog("Customer id matches with the registered one.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Customer id matches with the registered one.");
        Assert.assertNull(responseBodyGetCustomerInfo.getProvider());
        LoggerUtility.infoLog("Customer provider is null.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Customer provider is null.");
        Assert.assertEquals(responseBodyGetCustomerInfo.getFirstName(), responseBodyRegisterNewAccount.getFirstName());
        LoggerUtility.infoLog("Customer first name matches with the registered one.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Customer first name matches with the registered one.");
        Assert.assertEquals(responseBodyGetCustomerInfo.getLastName(), responseBodyRegisterNewAccount.getLastName());
        LoggerUtility.infoLog("Customer last name matches with the registered one.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Customer last name matches with the registered one.");
        Assert.assertEquals(responseBodyGetCustomerInfo.getPhone(), responseBodyRegisterNewAccount.getPhone());
        LoggerUtility.infoLog("Customer phone matches with the registered one.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Customer phone matches with the registered one.");
        Assert.assertEquals(responseBodyGetCustomerInfo.getDob(), responseBodyRegisterNewAccount.getDob());
        LoggerUtility.infoLog("Customer date of birth matches with the registered one.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Customer date of birth matches with the registered one.");
        Assert.assertEquals(responseBodyGetCustomerInfo.getEmail(), responseBodyRegisterNewAccount.getEmail());
        LoggerUtility.infoLog("Customer email matches with the registered one.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Customer email matches with the registered one.");
        Assert.assertFalse(responseBodyGetCustomerInfo.isEnabled());
        LoggerUtility.infoLog("Customer totp is disabled.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Customer totp is disabled.");
        Assert.assertEquals(responseBodyGetCustomerInfo.getCreatedAt(), responseBodyRegisterNewAccount.getCreatedAt());
        LoggerUtility.infoLog("Customer creation date and time match with the registration date and time.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Customer creation date and time match with the registration date and time.");
        Assert.assertEquals(responseBodyGetCustomerInfo.getAddress().getStreet(), responseBodyRegisterNewAccount.getAddress().getStreet());
        Assert.assertEquals(responseBodyGetCustomerInfo.getAddress().getCity(), responseBodyRegisterNewAccount.getAddress().getCity());
        Assert.assertEquals(responseBodyGetCustomerInfo.getAddress().getState(), responseBodyRegisterNewAccount.getAddress().getState());
        Assert.assertEquals(responseBodyGetCustomerInfo.getAddress().getCountry(), responseBodyRegisterNewAccount.getAddress().getCountry());
        Assert.assertEquals(responseBodyGetCustomerInfo.getAddress().getPostalCode(), responseBodyRegisterNewAccount.getAddress().getPostalCode());
        LoggerUtility.infoLog("Customer address matches with the registered one.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Customer address matches with the registered one.");
    }
}
