package masr;

import masr.common.VideoHelper;
import org.jcodec.api.JCodecException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class VideoController {
    @RequestMapping(value = "/capture_video_screens", method = RequestMethod.GET)
    public HttpEntity<VideoResult> captureVideoScreens(@RequestParam(value = "videoFile", required = true) String videoFile) throws IOException, JCodecException {
        VideoResult vr = new VideoResult(VideoHelper.grabScreenshots(videoFile, 20));
        vr.add(linkTo(methodOn(VideoController.class).captureVideoScreens(videoFile)).withSelfRel());

        return new ResponseEntity<VideoResult>(vr, HttpStatus.OK);
    }
}
