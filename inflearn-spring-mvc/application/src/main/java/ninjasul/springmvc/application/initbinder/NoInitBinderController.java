package ninjasul.springmvc.application.initbinder;

import lombok.extern.log4j.Log4j2;
import ninjasul.springmvc.application.Event;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noinitbinder")
@Log4j2
public class NoInitBinderController {

    @PostMapping(
        value="/events",
        consumes=MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Event createEvent(@ModelAttribute Event event) {
        log.info( "event: {}", event);
        return event;
    }

}