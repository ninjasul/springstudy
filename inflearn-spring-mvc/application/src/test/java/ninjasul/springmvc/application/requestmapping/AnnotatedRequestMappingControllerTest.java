package ninjasul.springmvc.application.requestmapping;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AnnotatedRequestMappingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void requestMapping_with_custom_annotation() throws Exception {

        mockMvc.perform(get("/requestmapping/annotation"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}