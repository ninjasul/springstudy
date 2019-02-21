package ninjasul.springmvc.application.handler.modelattribute;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ModelAttributeControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void first() throws Exception {

        mockMvc.perform(get("/modelattribute/first"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("genres"))
                .andExpect(model().attributeExists("colors"))
                .andExpect(model().attributeExists("event"))
        ;

    }

    @Test
    public void second() throws Exception {

        mockMvc.perform(get("/modelattribute/second"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("modelattribute/second"))
                .andExpect(model().attributeExists("genres"))
                .andExpect(model().attributeExists("colors"))
                .andExpect(model().attributeExists("event"))
        ;

    }
}