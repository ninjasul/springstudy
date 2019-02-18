package ninjasul.springmvc.application.requestmapping;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RequestMappingExerciseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getEvents() throws Exception {

        mockMvc.perform(get("/events"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("getEvents"));

    }

    @Test
    public void getEventsWithId() throws Exception {

        mockMvc.perform(get("/events/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("getEvents 1"));

        mockMvc.perform(get("/events/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("getEvents 2"));


        mockMvc.perform(get("/events/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("getEvents 3"));

    }

    @Test
    public void postEvents() throws Exception {

        mockMvc.perform(post("/events"))
                .andDo(print())
                .andExpect(status().isUnsupportedMediaType());

        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, containsString(MediaType.APPLICATION_JSON_VALUE)));
    }

    @Test
    public void deleteEvents() throws Exception {

        mockMvc.perform(delete("/events"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());

        mockMvc.perform(delete("/events/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("deleteEvents"));


        mockMvc.perform(delete("/events/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("deleteEvents"));

        mockMvc.perform(delete("/events/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("deleteEvents"));
    }

    @Test
    public void putEvents() throws Exception {

        mockMvc.perform(put("/events/1"))
                .andDo(print())
                .andExpect(status().isUnsupportedMediaType());

        mockMvc.perform(put("/events/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("putEvents"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, containsString(MediaType.APPLICATION_JSON_VALUE)));

        mockMvc.perform(put("/events/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("putEvents"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, containsString(MediaType.APPLICATION_JSON_VALUE)));

        mockMvc.perform(put("/events/3")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("putEvents"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, containsString(MediaType.APPLICATION_JSON_VALUE)));

    }


}