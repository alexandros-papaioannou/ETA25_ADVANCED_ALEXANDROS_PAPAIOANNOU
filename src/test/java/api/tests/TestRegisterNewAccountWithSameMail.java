package api.tests;

import api.requests.RequestRegisterNewAccount;
import api.responses.ResponseRegisterNewAccount;
import api.responses.ResponseRegisterNewAccountWithSameMail;
import api.support.BaseTest;
import api.support.DataGenerator;
import api.support.JsonReader;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.support.utils.LoggerUtility;
import ui.support.utils.extent.ExtentUtility;
import ui.support.utils.extent.ReportStep;

import java.util.Map;

public class TestRegisterNewAccountWithSameMail extends BaseTest {

    @Test
    public void registerNewAccountWithSameMail() {
        RequestRegisterNewAccount requestRegisterNewAccount = JsonReader.fromResource("requestdata/request-register-new-user.json",
                Map.of(
                        "email", DataGenerator.randomEmail("api"),
                        "first_name", DataGenerator.randomFirstName(),
                        "last_name", DataGenerator.randomLastName()
                ),
                RequestRegisterNewAccount.class);

        System.out.println("REGISTER NEW ACCOUNT REQUEST JSON:\n" + JsonReader.toString(requestRegisterNewAccount, true) + "\n");
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
            System.out.println("REGISTER NEW ACCOUNT RESPONSE JSON:\n" + node.toPrettyString() + "\n");
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

        System.out.println("REGISTER NEW ACCOUNT WITH THE SAME EMAIL REQUEST JSON:\n" + JsonReader.toString(requestRegisterNewAccount, true) + "\n");
        LoggerUtility.infoLog("POST request for new account creation with the same email was sent." + "\n");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"POST request for new account creation with the same email was sent.");

        Response responseRegisterNewAccountSameMail = post("users.register", requestRegisterNewAccount);

        System.out.println("Protocol and response: " + responseRegisterNewAccountSameMail.getStatusLine());
        System.out.println("Status code: " + responseRegisterNewAccountSameMail.getStatusCode() + "\n");
        LoggerUtility.infoLog("POST response for new account creation with the same email was received.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"POST response for new account creation with the same email was received.");

        try {
            RequestRegisterNewAccount echoed = responseRegisterNewAccountSameMail.as(RequestRegisterNewAccount.class);
            System.out.println("RESPONSE mapped to Account:\n" + JsonReader.toString(echoed, true));
        } catch (Exception ex) {
            JsonNode node = responseRegisterNewAccountSameMail.as(JsonNode.class);
            System.out.println("REGISTER NEW ACCOUNT WITH THE SAME EMAIL RESPONSE JSON:\n" + node.toPrettyString() + "\n");
        }

        ResponseRegisterNewAccountWithSameMail responseBodyRegisterNewAccountWithSameMail = responseRegisterNewAccountSameMail.as(ResponseRegisterNewAccountWithSameMail.class);
        Assert.assertEquals(responseRegisterNewAccountSameMail.getStatusCode(),422);
        LoggerUtility.infoLog("Expected response code 422 received.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Expected response code 422 received.");
        Assert.assertNotNull(responseBodyRegisterNewAccountWithSameMail.getResponseDuplicateMailMessage());
        LoggerUtility.infoLog("Response for account creation with the same email is not empty.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Response for account creation with the same email is not empty.");
        Assert.assertEquals(responseBodyRegisterNewAccountWithSameMail.getResponseDuplicateMailMessage().getFirst(),"A customer with this email address already exists.");
        LoggerUtility.infoLog("Response for account creation with the same email matches the expected one.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Response for account creation with the same email matches the expected one.");
    }
}

