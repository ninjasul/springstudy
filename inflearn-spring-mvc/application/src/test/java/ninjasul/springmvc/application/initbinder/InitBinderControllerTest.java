package ninjasul.springmvc.application.initbinder;

import com.fasterxml.jackson.databind.ObjectMapper;
import ninjasul.springmvc.application.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class InitBinderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void request_noInitBinderController() throws Exception {

        Event event = Event.builder()
                        .id(1)
                        .name("noInitBinderEvent")
                        .limit(-100)
                        .build();

        mockMvc.perform(post("/noinitbinder/events")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("id", event.getId().toString())
                .param("name", event.getName())
                .param("limit", event.getLimit().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(event.getId()))
                .andExpect(jsonPath("name").value(event.getName()))
                .andExpect(jsonPath("limit").value(event.getLimit()))
        ;

    }

    @Test
    public void request_initBinderController() throws Exception {

        Event event = Event.builder()
                .id(1)
                .name("initBinderEvent")
                .limit(-35)
                .build();

        mockMvc.perform(post("/initbinder/events")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("id", event.getId().toString())
                .param("name", event.getName())
                .param("limit", event.getLimit().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isEmpty())
                .andExpect(jsonPath("name").isEmpty())
                .andExpect(jsonPath("limit").value(event.getLimit()))
        ;
    }

}