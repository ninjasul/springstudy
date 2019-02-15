package ninjasul.springmvc.application;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mediatype")
public class MediaTypeRequestMappingController {

    public static final String MEDIATYPE = "/mediatype";
    public static final String CONSUMES = "/consumes/";
    public static final String PRODUCES = "/produces/";
    public static final String JSON_VALUE = "json";
    public static final String JSON_UTF8_VALUE = "json/utf8";
    public static final String TEXT_PLAIN_VALUE = "text/plain";
    public static final String AND = "/and/";

    @GetMapping(
            value=CONSUMES + JSON_VALUE,
            consumes=MediaType.APPLICATION_JSON_VALUE
    )
    public String consumesJson() {
        return CONSUMES + JSON_VALUE;
    }

    @GetMapping(
            value=CONSUMES + JSON_UTF8_VALUE,
            consumes=MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public String consumesJsonUtf8() {
        return CONSUMES + JSON_UTF8_VALUE;
    }

    @GetMapping(
            value=CONSUMES + JSON_UTF8_VALUE + AND + PRODUCES + TEXT_PLAIN_VALUE,
            consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces=MediaType.TEXT_PLAIN_VALUE
    )
    public String consumesJsonUtf8AndProducesPlainText() {
        return CONSUMES + JSON_VALUE + AND + PRODUCES + TEXT_PLAIN_VALUE;
    }



}