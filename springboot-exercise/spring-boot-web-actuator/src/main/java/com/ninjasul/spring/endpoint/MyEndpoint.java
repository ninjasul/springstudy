package com.ninjasul.spring.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "myendpoint")
public class MyEndpoint {
    private final static String DEFAULT_VALUE = "hello world";
    private String value = DEFAULT_VALUE;

    @ReadOperation
    public String getValue() {
        return this.value;
    }

    @WriteOperation
    public void setValue(String value) {
        this.value = value;
    }

    @DeleteOperation
    public void deleteValue() {
        this.value = DEFAULT_VALUE;
    }
}