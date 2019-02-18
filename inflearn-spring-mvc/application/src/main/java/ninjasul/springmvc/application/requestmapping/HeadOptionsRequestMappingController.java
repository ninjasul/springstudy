package ninjasul.springmvc.application.requestmapping;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/headoptions")
public class HeadOptionsRequestMappingController {

    @GetMapping(value="/head")
    public String head() {
        return "head";
    }

    @RequestMapping(value="/options", method={RequestMethod.GET, RequestMethod.POST})
    public String options() {
        return "options";
    }
}