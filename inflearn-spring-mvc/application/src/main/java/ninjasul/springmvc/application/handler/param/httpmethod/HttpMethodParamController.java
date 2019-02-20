package ninjasul.springmvc.application.handler.param.httpmethod;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/param")
@Log4j2
public class HttpMethodParamController {

    @RequestMapping("/httpmethod")
    public ResponseEntity handleHttpMethod( HttpMethod httpMethod ) {

        log.info( "httpMethod: {}", httpMethod.toString() );

        return ResponseEntity.ok()
                    .body(httpMethod.toString());
    }
}