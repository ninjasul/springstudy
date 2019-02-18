package ninjasul.springmvc.application.requestmapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnotatedRequestMappingController {

    @CustomRequestMappingAnnotation
    public String requestMappingWithCustomAnnotation() {
        return "RequestMapping with a customized annotation";
    }
}