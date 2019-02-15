package ninjasul.springmvc.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static ninjasul.springmvc.application.MediaTypeRequestMappingController.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MediaTypeRequestMappingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void request_consumesJson_without_requestHeader() throws Exception {

        // 서버가 Json 요청을 허용하는데 클라이언트가 contentType 없이 요청을 보내는 경우
        // "415 Unsupported Media Type" 응답오류가 발생
        mockMvc.perform(get(MEDIATYPE + CONSUMES + JSON_VALUE))
                .andDo(print())
                .andExpect(status().isUnsupportedMediaType());

    }

    @Test
    public void request_consumesJson_with_applicationJsonValue_contenttype() throws Exception {

        mockMvc.perform(get(MEDIATYPE + CONSUMES + JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(CONSUMES + JSON_VALUE));

    }

    @Test
    public void request_consumesJsonUtf8_with_applicationJsonValue_contentType() throws Exception {

        mockMvc.perform(get(MEDIATYPE + CONSUMES + JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(CONSUMES + JSON_UTF8_VALUE));

    }

    @Test
    public void request_consumesJsonUtf8_with_applicationJson_contentType_and_applicationJson_acceptHeader() throws Exception {

        mockMvc.perform(get(MEDIATYPE + CONSUMES + JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(CONSUMES + JSON_UTF8_VALUE));

    }

    @Test
    public void request_consumesJsonUtf8AndProducesTextPlain_with_applicationJson_contentType_and_applicationJson_acceptHeader() throws Exception {

        // 서버가 TextPlain 으로 응답을 주는데 클라이언트는 Json으로 받으려 하는 경우 "406 Not Acceptable" 응답오류 발생.
        mockMvc.perform(get(MEDIATYPE + CONSUMES + JSON_UTF8_VALUE + AND + PRODUCES + TEXT_PLAIN_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotAcceptable());

    }

    @Test
    public void request_consumesJsonUtf8AndProducesTextPlain_with_applicationJson_contentType_and_no_acceptHeader() throws Exception {

        // 서버가 TextPlain 으로 응답을 주는데 클라이언트는 받고자 하는 응답값을 지정하지 않는 경우 OK 응답 발생.
        mockMvc.perform(get(MEDIATYPE + CONSUMES + JSON_UTF8_VALUE + AND + PRODUCES + TEXT_PLAIN_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(CONSUMES + JSON_VALUE + AND + PRODUCES + TEXT_PLAIN_VALUE));

    }
}