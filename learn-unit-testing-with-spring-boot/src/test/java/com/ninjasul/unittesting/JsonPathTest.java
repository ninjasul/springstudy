package com.ninjasul.unittesting;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class JsonPathTest {

    @Test
    public void learning() {
        String responseFromService = "[\n" +
            "{ \"id\" : 10000, \"name\" : \"Pencil\", \"quantity\" : 5 }," +
            "{ \"id\" : 10001, \"name\" : \"Pen\", \"quantity\" : 15 }," +
            "{ \"id\" : 10002, \"name\" : \"Eraser\", \"quantity\" : 10 }" +
        "]";

        DocumentContext context = JsonPath.parse(responseFromService);

        int length = context.read("$.length()");
        assertThat(length).isEqualTo(3);

        List<Integer> ids = context.read("$..id");

        // containsExactly - 순서와 값 모두 체크
        assertThat(ids).containsExactly(10000, 10001, 10002);

        // containsExactly - 값만 체크
        assertThat(ids).contains(10002, 10000, 10001);
        assertThat(ids).doesNotContain(10005);

        log.info("\"$.[1]\": {}", context.read("$.[1]").toString());
        log.info("\"$.[0:1]\": {}", context.read("$.[0:1]").toString());
        log.info("\"$.[0:2]\": {}", context.read("$.[0:2]").toString());
        log.info("\"$.[1:2]\": {}", context.read("$.[1:2]").toString());
        log.info("\"$.[?(@.name=='Eraser')]\": {}", context.read("$.[?(@.name=='Eraser')]").toString());
        log.info("\"$.[?(@.quantity==5)]\": {}", context.read("$.[?(@.quantity==5)]").toString());
    }
}