package ninjasul.springmvc.bootconfiguration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void helloPathVariable() throws Exception {

        mockMvc.perform(get("/helloPathVariable/ninjasul"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello ninjasul"))
        ;
    }

    @Test
    public void helloRequestParam() throws Exception {

        mockMvc.perform(get("/helloRequestParam")
                    .param("name", "ninjasul"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello ninjasul"))
        ;
    }
}