package masr.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class VideoHelperTest {
    @Test
    public void grabScreenshots() throws Exception {
        Object[] res = VideoHelper.grabScreenshots("C:\\Users\\Jeff\\Pictures\\My Movie.mp4");
        assertArrayEquals(new Object[]{}, res);
    }

}