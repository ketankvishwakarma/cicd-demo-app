package com.demo.cicdapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SimpleRestController {
    
    @GetMapping
    public String hello(){
        return "Hello"+"!";
    }

    @GetMapping("/{something}")
    public String helloSomethig(@PathVariable String something){
        return "Hello "+something+"!";
    }
}
