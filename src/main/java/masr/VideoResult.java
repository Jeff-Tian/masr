package masr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class VideoResult extends ResourceSupport {
    private final String[] screenShotFilePaths;

    @JsonCreator
    public VideoResult(@JsonProperty("screenShotFilePaths") String[] screenShotFilePaths) {

        this.screenShotFilePaths = screenShotFilePaths;
    }

    public String[] getScreenShotFilePaths() {
        return screenShotFilePaths;
    }
}
