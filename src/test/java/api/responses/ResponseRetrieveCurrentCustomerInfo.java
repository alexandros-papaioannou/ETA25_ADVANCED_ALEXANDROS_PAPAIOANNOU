package api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseRetrieveCurrentCustomerInfo {

    @JsonProperty("id")
    private String id;

    @JsonProperty("provider")
    private String provider;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("dob")
    private String dob;

    @JsonProperty("email")
    private String email;

    @JsonProperty("totp_enabled")
    private boolean enabled;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("address")
    private ResponseRetrieveCurrentCustomerAddressInfo address;

    public String getId() {
        return id;
    }

    public String getProvider() {
        return provider;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public ResponseRetrieveCurrentCustomerAddressInfo getAddress() {
        return address;
    }
}
