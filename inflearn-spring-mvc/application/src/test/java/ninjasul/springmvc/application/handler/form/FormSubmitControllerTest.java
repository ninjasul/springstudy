package ninjasul.springmvc.application.handler.form;

import ninjasul.springmvc.application.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class FormSubmitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_createEvent_view() throws Exception {

        mockMvc.perform(get("/form/events/create"))
                .andDo(print())
                .andExpect(view().name("/events/form"))
                .andExpect(model().attributeExists("event"))
        ;
    }

    @Test
    public void test_createEvent_with_binding_error() throws Exception {

        Event event = Event.builder()
                            .name("a new event")
                            .build();

        mockMvc.perform(post("/form/events/create/noBindingResult")
                        .param("name", event.getName())
                        .param("limit", "ninjasul"))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void test_createEvent_with_validation_error() throws Exception {

        Event event = Event.builder()
                .name("a new event")
                .limit(-350)
                .build();

        mockMvc.perform(post("/form/events/create/withBindingResult/withValidation")
                .param("name", event.getName())
                .param("limit", event.getLimit().toString()))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void test_createEvent_for_successful_case() throws Exception {

        Event event = Event.builder()
                .name("a new event")
                .limit(100)
                .build();

        mockMvc.perform(post("/form/events/create/withBindingResult")
                .param("name", event.getName())
                .param("limit", event.getLimit().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(event.getName()))
                .andExpect(jsonPath("limit").value(event.getLimit()))
        ;
    }


}