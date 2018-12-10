package me.ninjasul.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return "Hello";
    }

    @GetMapping("/world")
    public String world() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return "World";
    }

}