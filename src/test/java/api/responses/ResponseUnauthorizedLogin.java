package api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseUnauthorizedLogin {

    @JsonProperty("error")
    private String error;

    public String getError() {
        return error;
    }
}
