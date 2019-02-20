package ninjasul.springmvc.application.handler.form;

import lombok.extern.log4j.Log4j2;
import ninjasul.springmvc.application.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/form")
@Log4j2
public class FormSubmitController {

    @GetMapping("/events/create")
    public String createEvent( Model model ) {
        model.addAttribute("event", Event.builder().limit(100).build() );

        return "/events/form";
    }

    @PostMapping("/events/create/noBindingResult")
    @ResponseBody
    public ResponseEntity<Event> createEventWithoutBindingResult(@ModelAttribute Event event ) {
        log.info("event: {}", event);
        return ResponseEntity.ok(event);
    }

    @PostMapping("/events/create/withBindingResult")
    @ResponseBody
    public ResponseEntity<Event> createEventWithBindingResult(@ModelAttribute Event event, BindingResult bindingResult ) {

        if( bindingResult.hasErrors() ) {
            return handleBadRequest(bindingResult);
        }

        log.info("event: {}", event);
        return ResponseEntity.ok(event);
    }

    @PostMapping("/events/create/withBindingResult/withValidation")
    @ResponseBody
    public ResponseEntity<Event> createEventWithBindingResultAndValidation(@ModelAttribute @Valid Event event, BindingResult bindingResult ) {

        if( bindingResult.hasErrors() ) {
            return handleBadRequest(bindingResult);
        }

        log.info("event: {}", event);
        return ResponseEntity.ok(event);
    }

    private ResponseEntity<Event> handleBadRequest(BindingResult bindingResult) {
        logBindingResultError(bindingResult);
        return ResponseEntity.badRequest().build();
    }

    private void logBindingResultError(BindingResult bindingResult) {
        for (ObjectError error : bindingResult.getAllErrors()) {
            log.info("error: {}", error);
        }
    }
}