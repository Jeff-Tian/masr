package masr.common;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.Picture;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.jcodec.scale.AWTUtil.toBufferedImage;


public class VideoHelper {
    public static Object[] grabScreenshots(String videoFilePath) throws IOException, JCodecException {
        ArrayList<String> a = new ArrayList<String>();

        long time = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            Picture frame = FrameGrab.getFrameFromFile(new File(videoFilePath), i);
            String filePath = "cap_" + i + ".bmp";
            ImageIO.write(toBufferedImage(frame), "bmp", new File(filePath));
            a.add(filePath);
        }

        return a.toArray();
    }
}
