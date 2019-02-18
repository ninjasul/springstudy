package ninjasul.springmvc.application.requestmapping;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HeaderParamRequestMappingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_requestMapping_when_having_headers() throws Exception {

        mockMvc.perform(get("/hasHeaders"))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

        mockMvc.perform(get("/hasHeaders")
                    .header(HttpHeaders.FROM, "127.0.0.1"))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

        mockMvc.perform(get("/hasHeaders")
                    .header(HttpHeaders.HOST, "localhost"))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

        mockMvc.perform(get("/hasHeaders")
                .header(HttpHeaders.FROM, "127.0.0.1")
                .header(HttpHeaders.AGE, "100"))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

        mockMvc.perform(get("/hasHeaders")
                .header(HttpHeaders.HOST, "localhost")
                .header(HttpHeaders.AGE, "100"))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

        mockMvc.perform(get("/hasHeaders")
                    .header(HttpHeaders.FROM, "127.0.0.1")
                    .header(HttpHeaders.HOST, "localhost")
                    .header(HttpHeaders.AGE, "100"))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

        mockMvc.perform(get("/hasHeaders")
                    .header(HttpHeaders.FROM, "localhost")
                    .header(HttpHeaders.HOST, "localhost"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hasHeaders"))
        ;
    }

    @Test
    public void test_requestMapping_when_having_no_header() throws Exception {

        mockMvc.perform(get("/hasNoHeaders")
                .header(HttpHeaders.FROM, "127.0.0.1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/hasNoHeaders")
                .header(HttpHeaders.HOST, "localhost"))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

        mockMvc.perform(get("/hasNoHeaders")
                .header(HttpHeaders.AGE, "100"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hasNoHeaders"))
        ;
    }

    @Test
    public void test_requestMapping_when_having_header_keyValue() throws Exception {

        mockMvc.perform(get("/hasHeaderKeyValues"))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

        mockMvc.perform(get("/hasHeaderKeyValues")
                .header(HttpHeaders.FROM, "127.0.0.0"))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

        mockMvc.perform(get("/hasHeaderKeyValues")
                .header(HttpHeaders.FROM, "127.0.0.1")
                .header(HttpHeaders.HOST, "localhost"))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

        mockMvc.perform(get("/hasHeaderKeyValues")
                .header(HttpHeaders.FROM, "127.0.0.1")
                .header(HttpHeaders.AGE, "99"))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

        mockMvc.perform(get("/hasHeaderKeyValues")
                .header(HttpHeaders.FROM, "127.0.0.1")
                .header(HttpHeaders.HOST, "remotehost")
                .header(HttpHeaders.AGE, "99"))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

        mockMvc.perform(get("/hasHeaderKeyValues")
                .header(HttpHeaders.FROM, "127.0.0.1")
                .header(HttpHeaders.AGE, "100.5"))
                .andExpect(status().isOk())
                .andExpect(content().string("hasHeaderKeyValues"))
        ;

        mockMvc.perform(get("/hasHeaderKeyValues")
                .header(HttpHeaders.FROM, "127.0.0.1")
                .header(HttpHeaders.HOST, "remotehost")
                .header(HttpHeaders.AGE, "100.5"))
                .andExpect(status().isOk())
                .andExpect(content().string("hasHeaderKeyValues"))
        ;

        mockMvc.perform(get("/hasHeaderKeyValues")
                .header(HttpHeaders.FROM, "127.0.0.1")
                .header(HttpHeaders.HOST, "remotehost")
                .header(HttpHeaders.AGE, 100.5))
                .andExpect(status().isOk())
                .andExpect(content().string("hasHeaderKeyValues"))
        ;
    }

    @Test
    public void test_requestMapping_when_having_parameters() throws Exception {

        mockMvc.perform(get("/hasParameters"))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;

        mockMvc.perform(get("/hasParameters")
                        .param( "id", "id"))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;

        mockMvc.perform(get("/hasParameters")
                    .param( "id", "id")
                    .param( "pwd", "pwd")
                    .param( "no", "no"))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;

        mockMvc.perform(get("/hasParameters")
                    .param( "id", "id")
                    .param( "pwd", "pwd"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hasParameters"))
        ;

    }

    @Test
    public void test_requestMapping_when_having_no_parameters() throws Exception {

        mockMvc.perform(get("/hasNoParameters"))
                .andExpect(status().isBadRequest())
                .andDo(print())
        ;

        mockMvc.perform(get("/hasNoParameters")
                .param( "id", "id"))
                .andExpect(status().isBadRequest())
                .andDo(print())
        ;

        mockMvc.perform(get("/hasNoParameters")
                .param( "pwd", "pwd"))
                .andExpect(status().isBadRequest())
                .andDo(print())
        ;

        mockMvc.perform(get("/hasNoParameters")
                .param( "id", "id")
                .param( "pwd", "pwd"))
                .andExpect(status().isBadRequest())
                .andDo(print())
        ;

        mockMvc.perform(get("/hasNoParameters")
                .param( "no", "no"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hasNoParameters"))
        ;
    }

    @Test
    public void test_requestMapping_when_having_parameter_keyValues() throws Exception {

        mockMvc.perform(get("/hasParamKeyValues"))
                .andExpect(status().isBadRequest())
                .andDo(print())
        ;

        mockMvc.perform(get("/hasParamKeyValues")
                .param( "id", "id"))
                .andExpect(status().isBadRequest())
                .andDo(print())
        ;

        mockMvc.perform(get("/hasParamKeyValues")
                .param( "id", "id")
                .param( "pwd", "pwd"))
                .andExpect(status().isBadRequest())
                .andDo(print())
        ;

        mockMvc.perform(get("/hasParamKeyValues")
                .param( "id", "id")
                .param( "pwd", "password")
                .param( "no", "12345"))
                .andExpect(status().isBadRequest())
                .andDo(print())
        ;


        mockMvc.perform(get("/hasParamKeyValues")
                .param( "id", "id")
                .param( "no", "123456"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hasParamKeyValues"))
        ;

        mockMvc.perform(get("/hasParamKeyValues")
                .param( "id", "id")
                .param( "pwd", "password")
                .param( "no", "123456"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hasParamKeyValues"))
        ;
    }
}