package ninjasul.springmvc.application.handler.validated;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/validate")
@Log4j2
public class ValidatedController {

    @PostMapping("/name")
    @ResponseBody
    public ResponseEntity<ValidatedEvent> validateName(@ModelAttribute @Validated(ValidatedEvent.ValidateName.class) ValidatedEvent event, BindingResult bindingResult ) {
        if( bindingResult.hasErrors() ) {
            return handleBadRequest(bindingResult);
        }

        log.info("event: {}", event);
        return ResponseEntity.ok(event);
    }

    @PostMapping("/limit")
    @ResponseBody
    public ResponseEntity<ValidatedEvent> validateLimit(@ModelAttribute @Validated(ValidatedEvent.ValidateLimit.class) ValidatedEvent event, BindingResult bindingResult ) {
        if( bindingResult.hasErrors() ) {
            return handleBadRequest(bindingResult);
        }

        log.info("event: {}", event);
        return ResponseEntity.ok(event);
    }

    private ResponseEntity<ValidatedEvent> handleBadRequest(BindingResult bindingResult) {
        logBindingResultError(bindingResult);
        return ResponseEntity.badRequest().build();
    }

    private void logBindingResultError(BindingResult bindingResult) {
        for (ObjectError error : bindingResult.getAllErrors()) {
            log.info("error: {}", error);
        }
    }
}