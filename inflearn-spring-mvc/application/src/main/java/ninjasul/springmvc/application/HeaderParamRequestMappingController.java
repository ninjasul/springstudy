package ninjasul.springmvc.application;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderParamRequestMappingController {

    @GetMapping(value= "hasHeaders", headers= {HttpHeaders.FROM, HttpHeaders.HOST, "!Age"})
    public String hasHeaders() {
        return "hasHeaders";
    }

    @GetMapping(value= "hasNoHeaders", headers= {"!From", "!Host", "Age"})
    public String hasNoHeaders() {
        return "hasNoHeaders";
    }

    @GetMapping(value= "hasHeaderKeyValues", headers= {"From=127.0.0.1", "Host!=localhost", "Age=100.5"})
    public String hasHeaderKeyValues() {
        return "hasHeaderKeyValues";
    }

    @GetMapping(value= "hasParameters", params= {"id", "pwd", "!no"})
    public String hasParameters() {
        return "hasParameters";
    }

    @GetMapping(value= "hasNoParameters", params= {"!id", "!pwd", "no"})
    public String hasNoParameters() {
        return "hasNoParameters";
    }

    @GetMapping(value= "hasParamKeyValues", params= {"id=id", "pwd!=pwd", "no=123456"})
    public String hasParamKeyValues() {
        return "hasParamKeyValues";
    }
}