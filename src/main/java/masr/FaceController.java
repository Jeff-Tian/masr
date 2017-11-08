package masr;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class FaceController {
    @RequestMapping("/face_behavior")
    public HttpEntity<FaceResult> faceBehavior(@RequestParam(value = "videoFile", required = true) String videoFile) {
        FaceResult faceResult = new FaceResult(1.0, 1.0, 1.0, 1.0);
        faceResult.add(linkTo(methodOn(FaceController.class).faceBehavior(videoFile)).withSelfRel());

        return new ResponseEntity<FaceResult>(faceResult, HttpStatus.OK);
    }
}
