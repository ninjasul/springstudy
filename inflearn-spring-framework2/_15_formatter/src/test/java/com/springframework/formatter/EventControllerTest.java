package com.springframework.formatter;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
/*
@WebMvcTest({
        EventController.class,
        EventFormatter.class,
})
*/
@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
public class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ConversionService conversionService;

    @Test
    public void getEvent() throws Exception {
        mockMvc.perform(get("/event/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    public void conversionService() {
        assertNotNull(conversionService);
        log.info(conversionService);
    }
}