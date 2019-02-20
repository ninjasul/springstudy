package ninjasul.springmvc.application.handler.param.httpmethod;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HttpMethodParamControllerTest {

    private static final String TARGET_URL = "/param/httpmethod";

    @Autowired
    private MockMvc mockMvc;

    private Map<HttpMethod, RequestBuilder> requestBuilderMap = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        requestBuilderMap.put(HttpMethod.GET, get(TARGET_URL));
        requestBuilderMap.put(HttpMethod.PUT, put(TARGET_URL));
        requestBuilderMap.put(HttpMethod.POST, post(TARGET_URL));
        requestBuilderMap.put(HttpMethod.DELETE, delete(TARGET_URL));
    }

    @Test
    public void test_httpMethod() throws Exception {

        for( Map.Entry<HttpMethod, RequestBuilder> mapEntry : requestBuilderMap.entrySet() ) {
            mockMvc.perform(mapEntry.getValue())
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(mapEntry.getKey().toString()))
            ;
        }
    }


}