package ninjasul.springmvc.application.handler.sessionattribute;

import lombok.extern.log4j.Log4j2;
import ninjasul.springmvc.application.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/sessionattributes")
@SessionAttributes("event")
@Log4j2
public class SessionAttributesController {

    @GetMapping("/withSessionAttributes/withModel")
    public ResponseEntity<Event> createEventWithSessionAttributesAndModel( Model model ) {
        Event event = Event.builder().limit(100).build();
        model.addAttribute("event", event );

        return ResponseEntity.ok(event);
    }

    @GetMapping("/withSessionAttributes/withoutModel")
    @ResponseBody
    public ResponseEntity<Event> createEventWithSessionAttributes() {
        Event event = Event.builder().limit(100).build();
        return ResponseEntity.ok(event);
    }

    @GetMapping("/withSessionAttributes/withModel/withSessionStatus")
    public ResponseEntity<Event> createEventWithSessionAttributesAndModel( Model model, SessionStatus sessionStatus ) {
        Event event = Event.builder().limit(100).build();
        model.addAttribute("event", event );
        sessionStatus.setComplete();

        return ResponseEntity.ok(event);
    }
}