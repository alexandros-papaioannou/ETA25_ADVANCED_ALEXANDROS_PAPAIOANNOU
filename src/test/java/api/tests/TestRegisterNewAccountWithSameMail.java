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

        Response response = post("users.register", requestRegisterNewAccount);
        System.out.println("REQUEST JSON:\n" + JsonReader.toString(requestRegisterNewAccount, true) + "\n");
        LoggerUtility.infoLog("POST request for new account creation was sent.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"POST request for new account creation was sent.");


        //Response response = post("users.register", requestRegisterNewAccount);


        LoggerUtility.infoLog("POST response for new account creation was received.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"POST response for new account creation was received.");
        System.out.println("Protocol and response: " + response.getStatusLine());
        System.out.println("Status code: " + response.getStatusCode() + "\n");


        try {
            RequestRegisterNewAccount echoed = response.as(RequestRegisterNewAccount.class);
            System.out.println("RESPONSE mapped to Account:\n" + JsonReader.toString(echoed, true));
        } catch (Exception ex) {
            JsonNode node = response.as(JsonNode.class);
            System.out.println("RESPONSE JSON:\n" + node.toPrettyString());
        }

        ResponseRegisterNewAccount responseBody = response.as(ResponseRegisterNewAccount.class);

        Assert.assertEquals(response.getStatusCode(),201);
        LoggerUtility.infoLog("Expected response code 201 received.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Expected response code 201 received.");
        Assert.assertEquals(requestRegisterNewAccount.getFirstName(),
                responseBody.getFirstName());
        LoggerUtility.infoLog("First name from request matches first name from response.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"First name from request matches first name from response.");
        Assert.assertEquals(requestRegisterNewAccount.getLastName(),
                responseBody.getLastName());
        LoggerUtility.infoLog("Last name from request matches last name from response.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Last name from request matches last name from response.");
        Assert.assertEquals(requestRegisterNewAccount.getPhone(),
                responseBody.getPhone());
        LoggerUtility.infoLog("Phone number from request matches phone number from response.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Phone number from request matches phone number from response.");
        Assert.assertEquals(requestRegisterNewAccount.getDob(),
                responseBody.getDob());
        LoggerUtility.infoLog("Date of birth from request matches date of birth from response.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Date of birth from request matches date of birth from response.");
        Assert.assertEquals(requestRegisterNewAccount.getEmail(),
                responseBody.getEmail());
        LoggerUtility.infoLog("Email from request matches email from response.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Email from request matches email from response.");
        Assert.assertNotNull(responseBody.getId());
        LoggerUtility.infoLog("Response contains an id.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Response contains an id.");
        Assert.assertNotNull(responseBody.getCreatedAt());
        LoggerUtility.infoLog("Response contains a creation date and time.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Response contains a creation date and time.");
        Assert.assertEquals(requestRegisterNewAccount.getAddress().getStreet(),
                responseBody.getAddress().getStreet());
        Assert.assertEquals(requestRegisterNewAccount.getAddress().getCity(),
                responseBody.getAddress().getCity());
        Assert.assertEquals(requestRegisterNewAccount.getAddress().getState(),
                responseBody.getAddress().getState());
        Assert.assertEquals(requestRegisterNewAccount.getAddress().getCountry(),
                responseBody.getAddress().getCountry());
        Assert.assertEquals(requestRegisterNewAccount.getAddress().getPostalCode(),
                responseBody.getAddress().getPostalCode());
        LoggerUtility.infoLog("Response contains a full address and matches with the request.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Response contains a full address and matches with the request.");

        Response responseOnDuplicateMail = post("users.register", requestRegisterNewAccount);
        System.out.println("REQUEST JSON:\n" + JsonReader.toString(requestRegisterNewAccount, true) + "\n");
        LoggerUtility.infoLog("POST request for new account creation with the same email was sent.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"POST request for new account creation with the same email was sent.");



        LoggerUtility.infoLog("POST response for new account creation with the same email was received.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"POST response for new account creation with the same email was received.");

        System.out.println("Protocol and response: " + responseOnDuplicateMail.getStatusLine());
        System.out.println("Status code: " + responseOnDuplicateMail.getStatusCode() + "\n");

        try {
            RequestRegisterNewAccount echoed = responseOnDuplicateMail.as(RequestRegisterNewAccount.class);
            System.out.println("RESPONSE mapped to Account:\n" + JsonReader.toString(echoed, true));
        } catch (Exception ex) {
            JsonNode node = responseOnDuplicateMail.as(JsonNode.class);
            System.out.println("RESPONSE JSON:\n" + node.toPrettyString());
        }

        ResponseRegisterNewAccountWithSameMail responseBodyRegisterNewAccountWithSameMail = responseOnDuplicateMail.as(ResponseRegisterNewAccountWithSameMail.class);
        Assert.assertEquals(responseOnDuplicateMail.getStatusCode(),422);
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

