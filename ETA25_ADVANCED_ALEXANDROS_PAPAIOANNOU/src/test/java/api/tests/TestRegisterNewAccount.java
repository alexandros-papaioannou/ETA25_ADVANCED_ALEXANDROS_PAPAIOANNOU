package api.tests;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.requests.RequestRegisterNewAccount;
import api.responses.ResponseRegisterNewAccount;
import api.support.BaseTest;
import api.support.DataGenerator;
import api.support.JsonReader;

import java.util.Map;

public class TestRegisterNewAccount extends BaseTest {

    @Test
    public void registerNewAccount() {
        RequestRegisterNewAccount requestRegisterNewAccount = JsonReader.fromResource("requestdata/request-register-new-user.json",
                Map.of("email", DataGenerator.randomEmail("apitest")), RequestRegisterNewAccount.class);
//regula de email aplicata per request
//tokenize firstname, lastname in json request
        System.out.println("REQUEST JSON:\n" + JsonReader.toString(requestRegisterNewAccount, true) + "\n");

        Response response = post("users.register", requestRegisterNewAccount);

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
        Assert.assertEquals(requestRegisterNewAccount.getFirstName(),
                responseBody.getFirstName());
        Assert.assertEquals(requestRegisterNewAccount.getLastName(),
                responseBody.getLastName());
        Assert.assertEquals(requestRegisterNewAccount.getPhone(),
                responseBody.getPhone());
        Assert.assertEquals(requestRegisterNewAccount.getDob(),
                responseBody.getDob());
        Assert.assertEquals(requestRegisterNewAccount.getEmail(),
                responseBody.getEmail());
        Assert.assertNotNull(responseBody.getId());
        Assert.assertNotNull(responseBody.getCreatedAt());
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

        System.out.println(responseBody.getId());
        System.out.println(responseBody.getEmail());

    }
}

