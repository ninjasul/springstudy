package ninjasul.springmvc.application.handler.redirectattribute;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.log4j.Log4j2;
import ninjasul.springmvc.application.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/redirectattributes")
@SessionAttributes("event")
@Log4j2
public class RedirectAttributesController {

    @PostMapping("/setRedirectAttributes/toSameModelAttributeName")
    public String setRedirectAttributesAndRedirectToSameModelAttribute(
            @Validated @ModelAttribute("newEvent") Event event,
            RedirectAttributes attributes,
            SessionStatus sessionStatus ) {

        attributes.addAttribute("name", event.getName());
        attributes.addAttribute("limit", event.getLimit());

        sessionStatus.setComplete();

        return "redirect:/redirectattributes/redirected/withSameModelAttribute";
    }

    @PostMapping("/setRedirectAttributes/toNewModelAttributeName")
    public String setRedirectAttributesAndRedirectToNewModelAttribute(
            @Validated @ModelAttribute("newEvent") Event event,
            RedirectAttributes attributes,
            SessionStatus sessionStatus ) {

        attributes.addAttribute("name", event.getName());
        attributes.addAttribute("limit", event.getLimit());

        sessionStatus.setComplete();

        return "redirect:/redirectattributes/redirected/withNewModelAttribute";
    }

    @RequestMapping("/redirected/withSameModelAttribute")
    public ResponseEntity redirectedWithSameModelAttribute(@ModelAttribute Event event, BindingResult bindingResult ) {

        if( bindingResult.hasErrors() ) {
            handleBadRequest( bindingResult );
        }

        return ResponseEntity.ok(event);
    }

    @RequestMapping("/redirected/withNewModelAttribute")
    public ResponseEntity redirectedWithNewModelAttribute(@ModelAttribute("newEvent") Event event, BindingResult bindingResult ) {

        if( bindingResult.hasErrors() ) {
            handleBadRequest( bindingResult );
        }

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