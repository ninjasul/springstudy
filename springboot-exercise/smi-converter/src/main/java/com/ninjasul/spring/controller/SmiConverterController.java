package com.ninjasul.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SmiConverterController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

  /*  @RequestMapping("/convert")
    public String convert() {

    }*/
}