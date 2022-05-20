package models.singleResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleResourceMain {
    private SingleResourceData data;
    private SingleResourceSupport support;
}
