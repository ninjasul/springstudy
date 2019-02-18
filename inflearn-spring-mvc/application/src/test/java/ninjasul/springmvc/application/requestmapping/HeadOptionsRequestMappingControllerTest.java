package ninjasul.springmvc.application.requestmapping;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HeadOptionsRequestMappingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_head() throws Exception {
        mockMvc.perform(head("/headoptions/head"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void test_options() throws Exception {
        mockMvc.perform(options("/headoptions/options"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andExpect(header().stringValues(
                        HttpHeaders.ALLOW,
                            hasItems(
                                containsString(RequestMethod.GET.toString()),
                                containsString(RequestMethod.POST.toString()),
                                containsString(RequestMethod.HEAD.toString()),
                                containsString(RequestMethod.OPTIONS.toString())
                            )
                        )
                )
        ;
    }
}