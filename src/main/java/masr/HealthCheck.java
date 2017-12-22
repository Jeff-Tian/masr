package masr;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class HealthCheck extends ResourceSupport {
    private final String result;
    private final String path;

    @JsonCreator
    public HealthCheck(@JsonProperty("result") String result) {
        this.result = result;
        this.path = HealthCheck.class.getClassLoader().getResource("masr/HealthCheck.class").getPath();
    }

    public String getResult() {
        return result;
    }

    public String getPath() {
        return path;
    }
}
