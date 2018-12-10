package me.ninjasul.databinding.propertyeditor;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventEditorController {

    // PropertyEditor를 빈으로 등록하는 대신 WebDataBinder에 등록해 주어야 함.
    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(EditorEvent.class, new EventEditor());
    }

    @GetMapping("/event/editor/{event}")
    public String getEvent(@PathVariable EditorEvent event) {
        System.out.println(event);
        return event.getId().toString();
    }
}