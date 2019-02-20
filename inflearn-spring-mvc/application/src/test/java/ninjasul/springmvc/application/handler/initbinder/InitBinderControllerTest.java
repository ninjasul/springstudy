package ninjasul.springmvc.application.handler.initbinder;

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

    @Test
    public void test_noInitBinder() throws Exception {

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
                .param("limit", event.getLimit().toString())
                .param("startDate", "2019-02-20"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(event.getId()))
                .andExpect(jsonPath("name").value(event.getName()))
                .andExpect(jsonPath("limit").value(event.getLimit()))
                .andExpect(jsonPath("startDate").value("2019-02-20"))
        ;

    }

    @Test
    public void test_initBinder_for_the_negative_limit() throws Exception {

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
                .param("limit", event.getLimit().toString())
                .param("startDate", "2019-02-20"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
        ;
    }

    @Test
    public void test_initBinder_for_empty_name() throws Exception {

        Event event = Event.builder()
                .id(1)
                .name("")
                .limit(-35)
                .build();

        mockMvc.perform(post("/initbinder/events")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("id", event.getId().toString())
                .param("name", event.getName())
                .param("limit", event.getLimit().toString())
                .param("startDate", "2019-02-20"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
        ;
    }

    @Test
    public void test_initBinder_for_wrongly_formatted_startDate() throws Exception {

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
                .param("limit", event.getLimit().toString())
                .param("startDate", "20190220"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
        ;
    }

    @Test
    public void test_initBinder_for_successful_case() throws Exception {

        Event event = Event.builder()
                .id(1)
                .name("initBinderEvent")
                .limit(1000)
                .build();

        mockMvc.perform(post("/initbinder/events")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("id", event.getId().toString())
                .param("name", event.getName())
                .param("limit", event.getLimit().toString())
                .param("startDate", "2019-02-20"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isEmpty())
                .andExpect(jsonPath("name").value(event.getName()))
                .andExpect(jsonPath("limit").value(event.getLimit()))
                .andExpect(jsonPath("startDate").value("2019-02-20"))
        ;
    }
}
