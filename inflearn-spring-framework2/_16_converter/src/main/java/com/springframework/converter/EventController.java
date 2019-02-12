package com.springframework.converter;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class EventController {

    @GetMapping("/event/{event}")
    public String getEvent(@PathVariable Event event) {
        log.info("event: {}", event );
        return event.getId().toString();
    }
}