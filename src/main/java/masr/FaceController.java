package masr;

import masr.common.ImageHelper;
import masr.common.JSONConverter;
import masr.face.FaceBehavior;
import masr.face.FaceBehaviorAggregator;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class FaceController {
    @RequestMapping(value = "/face_behavior_from_files", method = RequestMethod.POST)
    public HttpEntity<FaceResult> faceBehaviorFromFiles(@RequestBody(required = true) Map<String, Object> data) throws Exception {
        Object[] a = Arrays.stream(((ArrayList) data.get("photos")).toArray()).map(p -> {
            try {
                String base64 = ImageHelper.convertToBase64FromPath(p.toString());

                return FaceBehavior.detectFromImage(base64);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return FaceBehavior.empty();
        }).toArray();

        FaceResult fr = getFaceResult(a);

        fr.add(linkTo(methodOn(FaceController.class).faceBehaviorFromFiles(data)).withSelfRel());

        return new ResponseEntity<FaceResult>(fr, HttpStatus.OK);
    }

    @RequestMapping(value = "/face_behavior", method = RequestMethod.POST)
    public HttpEntity<FaceResult> faceBehavior(@RequestBody(required = true) Map<String, Object> data) throws Exception {
        Object[] a = Arrays.stream(((ArrayList) data.get("photos")).toArray()).map(p -> {
            try {
                return FaceBehavior.detectFromImage((String) p);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return FaceBehavior.empty();
        }).toArray();

        FaceResult faceResult = getFaceResult(a);

        faceResult.add(linkTo(methodOn(FaceController.class).faceBehavior(data)).withSelfRel());

        return new ResponseEntity<FaceResult>(faceResult, HttpStatus.OK);
    }

    private FaceResult getFaceResult(Object[] a) throws Exception {
        FaceBehavior[] b = new FaceBehavior[a.length];

        for (int i = 0; i < a.length; i++) {
            b[i] = (FaceBehavior) a[i];
        }

        return new FaceResult(FaceBehaviorAggregator.from(b));
    }

}
