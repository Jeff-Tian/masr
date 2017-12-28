package masr;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import masr.idempotency.AudioToText;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
public class AsrController {

    @RequestMapping("/asr")
    public HttpEntity<String> asr(
            @RequestParam(value = "audioFile", required = true) String audioFile) throws IOException, NoSuchAlgorithmException {

        return new ResponseEntity<String>(AudioToText.convert(audioFile), HttpStatus.OK);
    }
}
