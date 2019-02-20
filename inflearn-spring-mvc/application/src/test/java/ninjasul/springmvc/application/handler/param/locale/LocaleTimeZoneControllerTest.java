package ninjasul.springmvc.application.handler.param.locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest
public class LocaleTimeZoneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_locale_timezone() throws Exception {

        mockMvc.perform(get("/param/localetimezone"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.locale").value(Locale.US.getLanguage()))
                .andExpect(jsonPath("$.zoneId").value("Asia/Seoul"))
        ;

    }
}