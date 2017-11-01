package hello;

import com.iflytek.msp.cpdb.lfasr.model.Message;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HealthCheck extends ResourceSupport {
    private final String result;

    @JsonCreator
    public HealthCheck(@JsonProperty("result") String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
