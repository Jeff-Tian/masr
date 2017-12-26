package masr;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HealthCheck extends ResourceSupport {
    private final String result;
    private final String path;
    private final String nodeEnv;

    @JsonCreator
    public HealthCheck(@JsonProperty("result") String result) {
        this.result = result;
        this.path = HealthCheck.class.getClassLoader().getResource("masr/HealthCheck.class").getPath();
        this.nodeEnv = System.getenv("NODE_ENV");
    }

    public String getResult() {
        return result;
    }

    public String getPath() {
        return path;
    }

    public String getNodeEnv() {
        return nodeEnv;
    }
}
