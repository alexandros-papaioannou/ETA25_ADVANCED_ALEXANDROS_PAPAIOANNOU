package api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ResponseRegisterNewAccountWithSameMail {

    @JsonProperty("email")
    private ArrayList<String> responseDuplicateMailMessage;

    public ArrayList<String> getResponseDuplicateMailMessage() {
        return responseDuplicateMailMessage;
    }
}
