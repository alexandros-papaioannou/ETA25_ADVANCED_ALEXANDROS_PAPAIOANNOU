package api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseLogout {

    @JsonProperty("message")
    private String message;

    public String getMessage() {
        return message;
    }
}
