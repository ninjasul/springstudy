package ninjasul.springmvc.bootconfiguration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/helloPathVariable/{name}")
    public String helloPathVariable(@PathVariable("name") Person person ) {
        return "hello " + person.getName();
    }

    @GetMapping("/helloRequestParam")
    public String helloRequestParam(@RequestParam("name") Person person) {
        return "hello " + person.getName();
    }

    @GetMapping("/helloRequestParamById")
    public String helloRequestParamById(@RequestParam("id") Person person) {
        return "hello " + person.getName();
    }
}