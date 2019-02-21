package ninjasul.springmvc.application.handler.redirectattribute;

import ninjasul.springmvc.application.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.HttpSessionRequiredException;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RedirectAttributesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test(expected = HttpSessionRequiredException.class)
    public void test_setRedirectAttributesAndRedirectToSameModelAttribute() throws Exception {

        Event event = Event.builder()
                .name("event")
                .limit(100)
                .build();

        MvcResult mvcResult = mockMvc.perform(post("/redirectattributes/setRedirectAttributes/toSameModelAttributeName")
                .param("name", event.getName())
                .param("limit", event.getLimit().toString()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/redirectattributes/redirected/withSameModelAttribute?name=" + event.getName() + "&limit=" + event.getLimit()))
                .andReturn()
        ;

        mockMvc.perform(get(mvcResult.getResponse().getRedirectedUrl()))
                .andDo(print())
        ;

    }

    @Test
    public void test_setRedirectAttributesAndRedirectToNewModelAttribute() throws Exception {

        Event event = Event.builder()
                            .name("event")
                            .limit(100)
                            .build();

        MvcResult mvcResult = mockMvc.perform(post("/redirectattributes/setRedirectAttributes/toNewModelAttributeName")
                    .param("name", event.getName())
                    .param("limit", event.getLimit().toString()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/redirectattributes/redirected/withNewModelAttribute?name=" + event.getName() + "&limit=" + event.getLimit()))
                .andReturn()
        ;

        mockMvc.perform(get(mvcResult.getResponse().getRedirectedUrl()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(event.getName()))
                .andExpect(jsonPath("limit").value(event.getLimit()))
        ;
    }
}
