package com.springframework.spring5webmvcrecipies.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndex() {
        System.out.println("Some message to say...12345");
        return "index";
    }

}
