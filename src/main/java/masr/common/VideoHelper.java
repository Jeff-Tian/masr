package masr.common;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.dct.FfmpegIntDct;
import org.jcodec.common.model.Picture;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;
import org.jcodec.containers.mp4.MP4Util;
import org.jcodec.containers.mp4.boxes.MovieBox;
import org.jcodec.containers.mp4.boxes.TrakBox;

import static org.jcodec.scale.AWTUtil.toBufferedImage;


public class VideoHelper {
    public static String[] grabScreenshots(String videoFilePath, int maxFrames) throws IOException, JCodecException {
        ArrayList<String> a = new ArrayList<String>();

        File videoFile = new File(videoFilePath);

        File videoFileDir = new File(String.valueOf(Paths.get(videoFile.getParent(), FilenameUtils.removeExtension(videoFile.getName()))));

        if (!videoFileDir.exists()) {
            videoFileDir.mkdir();
        }

        long time = System.currentTimeMillis();

        long frameCount = MP4Util.parseMovie(videoFile).getVideoTrack().getFrameCount();
        double step = Math.max(1, Math.floor(frameCount / maxFrames));

        for (int i = (int) Math.ceil(step - 1); i < frameCount; i += step) {
            Picture frame = FrameGrab.getFrameFromFile(videoFile, i);

            Path filePath = Paths.get(videoFileDir.toString(), "cap_" + i + ".jpg");
            ImageIO.write(toBufferedImage(frame), "jpeg", filePath.toFile());
            a.add(a.size(), filePath.toString());
        }

        return a.toArray(new String[a.size()]);
    }

}
