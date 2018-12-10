package me.ninjasul.databinding.formatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
// @WebMvcTest 어노테이션은 계층형 테스트를 위한 어노테이션으로 웹과 관련된 Bean들,
// 즉 주로 Controller들만 자동등록 해 주고 Converter나 Formatter는 자동등록되지 않음.
// 따라서 Converter나 Formatter는 수동 등록을 해 주어야 함.
@WebMvcTest({EventFormatterController.class, EventFormatter.class})
public class EventFormatterControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getTest() throws Exception {
        mockMvc.perform(get("/event/formatter/22"))
                .andExpect(status().isOk())
                .andExpect(content().string("22"));
    }

}