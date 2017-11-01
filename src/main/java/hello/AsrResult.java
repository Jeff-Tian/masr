package hello;

import com.iflytek.msp.cpdb.lfasr.model.Message;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AsrResult extends ResourceSupport {
    private final Message content;

    @JsonCreator
    public AsrResult(@JsonProperty("content") Message content) {
        this.content = content;
    }

    public Message getContent() {
        return content;
    }
}
