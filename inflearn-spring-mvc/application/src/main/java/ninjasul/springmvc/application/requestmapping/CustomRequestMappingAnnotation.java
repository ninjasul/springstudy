package ninjasul.springmvc.application.requestmapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(value="/requestmapping/annotation", method= RequestMethod.GET)
@interface CustomRequestMappingAnnotation {
}