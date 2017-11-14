package masr;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import Lfasr.Lfasr;

@RestController
public class AsrController {

    @RequestMapping("/asr")
    public HttpEntity<AsrResult> asr(
            @RequestParam(value = "audioFile", required = true) String audioFile) {

        AsrResult asrResult = new AsrResult(Lfasr.convertAudioToText(audioFile));
        asrResult.add(linkTo(methodOn(AsrController.class).asr(audioFile)).withSelfRel());

        //http://www.xfyun.cn/doccenter/lfasr#go_operate_guid
        return new ResponseEntity<AsrResult>(asrResult, HttpStatus.OK);
    }
}
