package masr.common;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageHelper {
    public static int[] getImageSizeFromBase64String(String base64) throws IOException {
        BufferedImage image = null;
        byte[] imageByte;

        imageByte = Base64.decodeBase64(base64);
        ByteArrayInputStream bais = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bais);
        bais.close();

        return new int[]{image.getWidth(), image.getHeight()};
    }

    public static String convertToBase64FromPath(String path) throws IOException {
        File imageFile = new File(path);
        BufferedImage bi = ImageIO.read(imageFile);
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        ImageIO.write(bi, "png", os);
        os.flush();
        byte[] bytes = os.toByteArray();
        os.close();

        return Base64.encodeBase64String(bytes);
    }
}
