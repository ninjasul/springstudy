package me.ninjasul.databinding.converter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventConverterController {

    @GetMapping("/event/converter/{event}")
    public String getEvent(@PathVariable ConverterEvent event) {
        System.out.println(event);
        return event.getId().toString();
    }
}