package masr.face;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SmileDetectorTests {
    @Test
    public void testSmileDetector() {
        assertEquals("hello", SmileDetector.detect("Hello world'"));
    }

}
