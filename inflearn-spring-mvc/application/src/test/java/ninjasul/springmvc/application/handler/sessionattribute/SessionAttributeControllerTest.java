package ninjasul.springmvc.application.handler.sessionattribute;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class SessionAttributeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_getVisitTime() throws Exception {

        mockMvc.perform(get("/sessionattribute/getVisitTime"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("visitTime", notNullValue()))
        ;

    }
}