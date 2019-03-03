package ninjasul.springmvc.bootconfiguration;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class HelloController {

    @Autowired
    PersonRepository personRepository;

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

    @GetMapping(
        value = "/message",
        consumes = MediaType.TEXT_PLAIN_VALUE,
        produces = MediaType.TEXT_PLAIN_VALUE
    )
    public String message(@RequestBody String name ) {
        return "hello " + name;
    }

    @GetMapping(
            value = "/jsonMessage",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Person jsonMessage(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);
        return savedPerson;
    }

    @GetMapping(
            value = "/xmlMessage",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public Person xmlMessage(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);
        return savedPerson;
    }
}