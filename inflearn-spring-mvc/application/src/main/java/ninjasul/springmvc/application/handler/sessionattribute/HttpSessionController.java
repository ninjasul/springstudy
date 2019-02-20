package ninjasul.springmvc.application.handler.sessionattribute;

import lombok.extern.log4j.Log4j2;
import ninjasul.springmvc.application.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/sessionattributes")
@Log4j2
public class HttpSessionController {

    @GetMapping("/withHttpSession")
    public String createEvent( Model model, HttpSession httpSession ) {
        Event event = Event.builder().limit(100).build();
        model.addAttribute("event", event );
        httpSession.setAttribute("event", event );
        log.info( "event session: {}", httpSession.getAttribute("event"));
        return "/events/form";
    }

}