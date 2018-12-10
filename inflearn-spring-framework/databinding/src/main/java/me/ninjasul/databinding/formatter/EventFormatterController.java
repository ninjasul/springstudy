package me.ninjasul.databinding.formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EventFormatterController {

    @GetMapping("/event/formatter/{event}")
    public String getEvent(@PathVariable FormatterEvent event) {
        log.info(event.toString());
        return event.getId().toString();
    }
}