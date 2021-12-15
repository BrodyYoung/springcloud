package com.yyb.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping("/get")
    public String get() {
        return "get方法";
    }


    @GetMapping("/put")
    public String put() {
        return "put方法";
    }
}
