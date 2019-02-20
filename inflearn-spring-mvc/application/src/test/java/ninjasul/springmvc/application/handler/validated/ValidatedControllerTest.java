package ninjasul.springmvc.application.handler.validated;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ValidatedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_validate_name_for_empty_name() throws Exception {

        ValidatedEvent event = ValidatedEvent.builder()
                .name("")
                .limit(-350)
                .build();

        mockMvc.perform(post("/validate/name")
                .param("name", event.getName())
                .param("limit", event.getLimit().toString()))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void test_validate_name_for_negative_limit() throws Exception {

        ValidatedEvent event = ValidatedEvent.builder()
                .name("Validate Name Event")
                .limit(-350)
                .build();

        mockMvc.perform(post("/validate/name")
                .param("name", event.getName())
                .param("limit", event.getLimit().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(event.getName()))
                .andExpect(jsonPath("limit").value(event.getLimit()))
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void test_validate_limit_for_negative_limit() throws Exception {

        ValidatedEvent event = ValidatedEvent.builder()
                .name("")
                .limit(-350)
                .build();

        mockMvc.perform(post("/validate/limit")
                .param("name", event.getName())
                .param("limit", event.getLimit().toString()))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void test_validate_limit_for_empty_name() throws Exception {

        ValidatedEvent event = ValidatedEvent.builder()
                .name("Validate Limit Event")
                .limit(100)
                .build();

        mockMvc.perform(post("/validate/limit")
                .param("name", event.getName())
                .param("limit", event.getLimit().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(event.getName()))
                .andExpect(jsonPath("limit").value(event.getLimit()))
                .andExpect(status().isOk())
        ;
    }
}