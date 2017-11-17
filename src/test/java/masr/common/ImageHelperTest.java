package masr.common;

import masr.shared.TestObjects;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ImageHelperTest {
    @Test
    public void getImageSize() throws IOException {
        assertArrayEquals(new int[]{400, 422}, ImageHelper.getImageSizeFromBase64String(TestObjects.base64Image));
    }
}
