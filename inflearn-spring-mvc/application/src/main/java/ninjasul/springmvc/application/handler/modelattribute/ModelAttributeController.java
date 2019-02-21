package ninjasul.springmvc.application.handler.modelattribute;

import ninjasul.springmvc.application.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/modelattribute")
public class ModelAttributeController {

    @ModelAttribute("genres")
    public List<String> genres() {
        return Arrays.asList( new String[] { "action", "fantasy", "horror" });
    }

    @ModelAttribute
    public void colors(Model model) {
        model.addAttribute("colors", Arrays.asList(new String[] { "red", "blue", "yellow", "white" }));
    }

    @RequestMapping("/first")
    public String first(@ModelAttribute Event event) {
        return "events/form";
    }

    @RequestMapping("/second")
    @ModelAttribute
    public Event second(@ModelAttribute Event event) {
        return event;
    }

}