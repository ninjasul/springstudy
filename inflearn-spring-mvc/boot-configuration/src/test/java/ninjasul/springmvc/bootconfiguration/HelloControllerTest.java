package ninjasul.springmvc.bootconfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.oxm.Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

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

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Marshaller marshaller;

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

    @Test
    public void message() throws Exception {
        mockMvc.perform(get("/message")
                    .contentType(MediaType.TEXT_PLAIN)
                    .accept(MediaType.TEXT_PLAIN)
                    .content("ninjasul"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string( "hello ninjasul"))
        ;
    }

    @Test
    public void jsonMessage() throws Exception {

        Person person = Person.builder()
                                .name("ninjasul")
                                .build();

        String personJson = objectMapper.writeValueAsString(person);

        mockMvc.perform(get("/jsonMessage")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(personJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("ninjasul"))
        ;
    }

    @Test
    public void xmlMessage() throws Exception {

        Person person = Person.builder()
                .name("ninjasul")
                .build();

        StringWriter stringWriter = new StringWriter();
        Result result = new StreamResult(stringWriter);
        marshaller.marshal( person, result );

        mockMvc.perform(get("/xmlMessage")
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
                .content(stringWriter.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("person/id").exists())
                .andExpect(xpath("person/name").string("ninjasul"))
        ;
    }
}