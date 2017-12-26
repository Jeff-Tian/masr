package masr;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import masr.idempotency.AudioToText;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import Lfasr.Lfasr;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
public class AsrController {

    @RequestMapping("/asr")
    public HttpEntity<AsrResult> asr(
            @RequestParam(value = "audioFile", required = true) String audioFile) throws IOException, NoSuchAlgorithmException {

        AsrResult asrResult = new AsrResult(AudioToText.convert(audioFile));
        asrResult.add(linkTo(methodOn(AsrController.class).asr(audioFile)).withSelfRel());

        //http://www.xfyun.cn/doccenter/lfasr#go_operate_guid
        return new ResponseEntity<AsrResult>(asrResult, HttpStatus.OK);
    }
}
