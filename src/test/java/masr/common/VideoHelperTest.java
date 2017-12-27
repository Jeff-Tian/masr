package masr.common;

import org.jcodec.api.JCodecException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class VideoHelperTest {
    @Test
    public void grabScreenshots() throws Exception {
        String[] res = VideoHelper.grabScreenshots("C:\\Users\\Jeff\\Pictures\\My Movie.mp4", 10);
        assertArrayEquals(new String[]{
                "C:\\Users\\Jeff\\Pictures\\My Movie\\cap_57.jpg",
                "C:\\Users\\Jeff\\Pictures\\My Movie\\cap_115.jpg",
                "C:\\Users\\Jeff\\Pictures\\My Movie\\cap_173.jpg",
                "C:\\Users\\Jeff\\Pictures\\My Movie\\cap_231.jpg",
                "C:\\Users\\Jeff\\Pictures\\My Movie\\cap_289.jpg",
                "C:\\Users\\Jeff\\Pictures\\My Movie\\cap_347.jpg",
                "C:\\Users\\Jeff\\Pictures\\My Movie\\cap_405.jpg",
                "C:\\Users\\Jeff\\Pictures\\My Movie\\cap_463.jpg",
                "C:\\Users\\Jeff\\Pictures\\My Movie\\cap_521.jpg",
                "C:\\Users\\Jeff\\Pictures\\My Movie\\cap_579.jpg"
        }, res);
    }
}