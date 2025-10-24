package support.assertions;

import api.requests.RequestRegisterNewAccount;
import api.responses.ResponseRegisterNewAccount;
import org.testng.Assert;

public final class ApiAssertions {

    private ApiAssertions() {

    }

    public static void assertUserMatches(RequestRegisterNewAccount expected,
                                         ResponseRegisterNewAccount actual) {
        Assert.assertNotNull(actual, "Response body should not be null");
        Assert.assertNotNull(actual.getId(), "Id must be present.");
        Assert.assertTrue(!String.valueOf(actual.getId()).isBlank(), "Id must not be blank.");
        Assert.assertNotNull(actual.getCreatedAt(), "Date and time creation must be present.");
        Assert.assertEquals(actual.getFirstName(), expected.getFirstName(), "First name mismatch.");
        Assert.assertEquals(actual.getLastName(), expected.getLastName(), "Last name mismatch.");
        Assert.assertEquals(actual.getPhone(), expected.getPhone(), "Phone mismatch.");
        Assert.assertEquals(actual.getDob(), expected.getDob(), "Date of birth mismatch.");
        Assert.assertEquals(actual.getEmail(), expected.getEmail(), "Email mismatch.");
        Assert.assertNotNull(actual.getAddress(), "Address must be present.");
        Assert.assertEquals(actual.getAddress().getStreet(), expected.getAddress().getStreet(), "Street mismatch.");
        Assert.assertEquals(actual.getAddress().getCity(), expected.getAddress().getCity(), "City mismatch.");
        Assert.assertEquals(actual.getAddress().getState(), expected.getAddress().getState(), "State mismatch.");
        Assert.assertEquals(actual.getAddress().getCountry(), expected.getAddress().getCountry(), "Country mismatch.");
        Assert.assertEquals(actual.getAddress().getPostalCode(), expected.getAddress().getPostalCode(), "Postal code mismatch.");

    }
}
