package ninjasul.springmvc.application.requestmapping;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequestMappingExerciseController {

    @GetMapping("/events")
    public String getEvents() {
        return "getEvents";
    }

    @GetMapping("/events/{id}")
    public String getEventsWithId(@PathVariable String id) {
        return "getEvents " + id;
    }

    @PostMapping(
        value="/events",
        consumes= MediaType.APPLICATION_JSON_VALUE,
        produces=MediaType.APPLICATION_JSON_VALUE
    )
    public String postEvents() {
        return "postEvents";
    }

    @DeleteMapping("/events/?")
    public String deleteEvents() {
        return "deleteEvents";
    }

    @PutMapping(
        value="/events/?",
        consumes= MediaType.APPLICATION_JSON_VALUE,
        produces=MediaType.APPLICATION_JSON_VALUE
    )
    public String putEvents() {
        return "putEvents";
    }

}