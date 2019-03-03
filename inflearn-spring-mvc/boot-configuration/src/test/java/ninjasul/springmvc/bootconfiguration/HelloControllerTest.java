package ninjasul.springmvc.bootconfiguration;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

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

    @Test
    public void helloRequestParamById() throws Exception {
        Person person = new Person();
        person.setName("ninjasul");

        Person savedPerson = personRepository.save(person);

        mockMvc.perform(get("/helloRequestParamById")
                .param("id", savedPerson.getId().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello " + savedPerson.getName()))
        ;
    }

    @Test
    public void helloStatic() throws Exception {
        mockMvc.perform(get("/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string( Matchers.containsString("Hello Index")))
        ;
    }

    @Test
    public void helloStaticMobile() throws Exception {
        mockMvc.perform(get("/mobile/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().exists(HttpHeaders.CACHE_CONTROL))
                .andExpect(content().string( Matchers.containsString("Hello Mobile")))
        ;
    }
}