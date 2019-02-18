package ninjasul.springmvc.application.requestmapping;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static ninjasul.springmvc.application.requestmapping.UriRequestMappingController.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UriRequestMappingControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    public void hello_get() throws Exception {

        mockMvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(REQUEST_MAPPING_WITH_HELLO));

        mockMvc.perform(put("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(REQUEST_MAPPING_WITH_HELLO));

        mockMvc.perform(post("/hello"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void requestMapping_withQuestionMark() throws Exception {

        mockMvc.perform(get("/hello2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(REQUEST_MAPPING_WITH_A_QUESTION_MARK));

        mockMvc.perform(get("/hello3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(REQUEST_MAPPING_WITH_A_QUESTION_MARK));

        mockMvc.perform(get("/hello4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(REQUEST_MAPPING_WITH_A_QUESTION_MARK));

        mockMvc.perform(get("/hello55"))
                .andDo(print())
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/hello666"))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void requestMapping_withOneSubPath() throws Exception {

        mockMvc.perform(get("/hello/world"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(REQUEST_MAPPING_WITH_A_SUB_PATH));

        mockMvc.perform(get("/hello/ninjasul"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(REQUEST_MAPPING_WITH_A_SUB_PATH));

        mockMvc.perform(get("/hello/world/ninjasul"))
                .andDo(print())
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/hello/world/ninjasul/ok"))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void requestMapping_withSubPaths() throws Exception {

        mockMvc.perform(get("/welcome"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(REQUEST_MAPPING_WITH_SUB_PATHS));

        mockMvc.perform(get("/welcome/ninjasul"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(REQUEST_MAPPING_WITH_SUB_PATHS));

        mockMvc.perform(get("/welcome/world/ninjasul"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(REQUEST_MAPPING_WITH_SUB_PATHS));

        mockMvc.perform(get("/welcome/world/ninjasul/ok"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(REQUEST_MAPPING_WITH_SUB_PATHS));
    }

    @Test
    public void requestMapping_withRegularExpression() throws Exception {

        mockMvc.perform(get("/greetings/world"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(REQUEST_MAPPING_WITH_A_REGULAR_EXPRESSION + " world"));

        mockMvc.perform(get("/greetings/ninjasul"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(REQUEST_MAPPING_WITH_A_REGULAR_EXPRESSION + " ninjasul"));

    }



}