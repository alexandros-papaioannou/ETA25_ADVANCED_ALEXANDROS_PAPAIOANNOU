package api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseRegisterNewAccount {

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

    @JsonProperty("id")
    private String id;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("address")
    private ResponseRegisterNewAccountAddress address;

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

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public ResponseRegisterNewAccountAddress getAddress() {
        return address;
    }
}