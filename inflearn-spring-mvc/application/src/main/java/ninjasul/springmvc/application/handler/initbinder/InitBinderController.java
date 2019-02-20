package ninjasul.springmvc.application.handler.initbinder;

import lombok.extern.log4j.Log4j2;
import ninjasul.springmvc.application.Event;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/initbinder")
@Log4j2
public class InitBinderController {

    @InitBinder("!event")
    public void initBinder(WebDataBinder webDataBinder) {
        log.info("initBinder invoked");
        webDataBinder.setDisallowedFields("id");
    }

    @InitBinder("event")
    public void initBinder2(WebDataBinder webDataBinder) {
        log.info("initBinder2 invoked");
        webDataBinder.setDisallowedFields("id");
        webDataBinder.addValidators(new InitBinderEventValidator());
    }

    @PostMapping(
        value="/events",
        consumes=MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Event> createEvent(@ModelAttribute @Validated Event event, BindingResult bindingResult) {
        log.info( "event: {}", event);

        if( bindingResult.hasErrors() ) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(event);
    }


}