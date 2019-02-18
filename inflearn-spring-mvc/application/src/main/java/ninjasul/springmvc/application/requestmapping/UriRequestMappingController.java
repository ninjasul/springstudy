package ninjasul.springmvc.application.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UriRequestMappingController {

    public static final String REQUEST_MAPPING_WITH_HELLO = "RequestMapping with hello";
    public static final String REQUEST_MAPPING_WITH_A_QUESTION_MARK = "RequestMapping with a question mark";
    public static final String REQUEST_MAPPING_WITH_A_SUB_PATH = "RequestMapping with a sub path";
    public static final String REQUEST_MAPPING_WITH_SUB_PATHS = "RequestMapping with sub paths";
    public static final String REQUEST_MAPPING_WITH_A_REGULAR_EXPRESSION = "RequestMapping with a regular expression";

    @RequestMapping(value="/hello", method={RequestMethod.GET, RequestMethod.PUT})
    @ResponseBody
    public String hello() {
        return REQUEST_MAPPING_WITH_HELLO;
    }

    @GetMapping(value="/hello?")
    @ResponseBody
    public String helloWithQuestionMark() {
        return REQUEST_MAPPING_WITH_A_QUESTION_MARK;
    }

    @GetMapping(value="/hello/*")
    @ResponseBody
    public String helloWithOneSubPath() {
        return REQUEST_MAPPING_WITH_A_SUB_PATH;
    }

    @GetMapping(value="/welcome/**")
    @ResponseBody
    public String welcomeWithSubPaths() {
        return REQUEST_MAPPING_WITH_SUB_PATHS;
    }

    @GetMapping(value="/greetings/{name:[a-z]+}")
    @ResponseBody
    public String greetingsWithRegularExpression(@PathVariable String name) {
        return REQUEST_MAPPING_WITH_A_REGULAR_EXPRESSION + " " + name;
    }

}