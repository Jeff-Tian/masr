package masr;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HealthCheckController {
    @RequestMapping("/healthcheck")
    public HttpEntity<HealthCheck> healthcheck() {
        HealthCheck healthCheck = new HealthCheck("everything is ok");
        healthCheck.add(linkTo(methodOn(HealthCheckController.class).healthcheck()).withSelfRel());

        return new ResponseEntity<HealthCheck>(healthCheck, HttpStatus.OK);
    }
}
