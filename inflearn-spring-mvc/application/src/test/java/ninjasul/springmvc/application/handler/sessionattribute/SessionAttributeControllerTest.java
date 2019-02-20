package ninjasul.springmvc.application.handler.sessionattribute;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class SessionAttributeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_createEvent_with_HttpSession() throws Exception {

        mockMvc.perform(get("/sessionattributes/withHttpSession"))
                .andDo(print())
                .andExpect(view().name("/events/form"))
                .andExpect(model().attributeExists("event"))
                .andExpect(request().sessionAttribute("event", notNullValue()))
        ;
    }

    @Test
    public void test_createEvent_with_SessionAttributes_and_Model() throws Exception {

        mockMvc.perform(get("/sessionattributes/withSessionAttributes/withModel"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("event", notNullValue()))
        ;
    }

    @Test
    public void test_createEvent_with_SessionAttributes_and_without_Model() throws Exception {

        mockMvc.perform(get("/sessionattributes/withSessionAttributes/withoutModel"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("event", nullValue()))
        ;
    }

    @Test
    public void test_createEvent_with_SessionAttributes_and_Model_and_SessionStatus() throws Exception {

        mockMvc.perform(get("/sessionattributes/withSessionAttributes/withModel/withSessionStatus"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("event", nullValue()))
        ;
    }

}