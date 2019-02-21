package ninjasul.springmvc.application.handler.redirectattribute;

import lombok.extern.log4j.Log4j2;
import ninjasul.springmvc.application.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/redirectattributes")
@SessionAttributes("event")
@Log4j2
public class RedirectAttributesController {

    @RequestMapping("/setRedirectAttributes/toSameModelAttributeName")
    public String setRedirectAttributesAndRedirectToSameModelAttribute(
            @Validated @ModelAttribute("newEvent") Event event,
            RedirectAttributes attributes,
            SessionStatus sessionStatus ) {

        attributes.addAttribute("name", event.getName());
        attributes.addAttribute("limit", event.getLimit());

        sessionStatus.setComplete();

        return "redirect:/redirectattributes/redirected/withSameModelAttribute";
    }

    @RequestMapping("/setRedirectAttributes/toNewModelAttributeName")
    public String setRedirectAttributesAndRedirectToNewModelAttribute(
            @Validated @ModelAttribute("newEvent") Event event,
            RedirectAttributes attributes,
            SessionStatus sessionStatus ) {

        attributes.addAttribute("name", event.getName());
        attributes.addAttribute("limit", event.getLimit());

        sessionStatus.setComplete();

        return "redirect:/redirectattributes/redirected/withNewModelAttribute";
    }

    @RequestMapping("/setFlashAttributes/toModel")
    public String setFlashAttributesAndRedirectToModel(
            @Validated @ModelAttribute("newEvent") Event event,
            RedirectAttributes attributes,
            SessionStatus sessionStatus ) {

        //sessionStatus.setComplete();
        attributes.addFlashAttribute("newEvent", event );

        return "redirect:/redirectattributes/redirected/withModel";
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

    @RequestMapping("/redirected/withModel")
    public ResponseEntity<Event> redirectedWithModel(@ModelAttribute("newEvent") Event modelEvent, Model model ) {

        Event event = (Event)model.asMap().get("newEvent");
        log.info("model event: {}", event);

        if( modelEvent != null ) {
            log.info("model event: {}", modelEvent);
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