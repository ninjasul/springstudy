package ninjasul.springmvc.application.handler.param.httpmethod;

import lombok.extern.log4j.Log4j2;
import ninjasul.springmvc.application.handler.param.ParamRestController;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@ParamRestController
@Log4j2
public class HttpMethodParamController {

    @RequestMapping("/httpmethod")
    public ResponseEntity handleHttpMethod( HttpMethod httpMethod ) {

        log.info( "httpMethod: {}", httpMethod.toString() );

        return ResponseEntity.ok()
                    .body(httpMethod.toString());
    }
}