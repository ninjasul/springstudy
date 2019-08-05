package com.ninjasul.test.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/hello")
    public String hello() {
        return "";
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}