package com.dio.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "index")
public class IndexController {

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "test")
    public String test(){
        return "server.port: " + port;
    }


}
