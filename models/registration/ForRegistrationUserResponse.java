package models.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForRegistrationUserResponse {
    private String id;
    private String token;
    private String error;
}
