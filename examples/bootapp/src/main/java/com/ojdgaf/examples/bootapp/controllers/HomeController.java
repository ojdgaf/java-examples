package com.ojdgaf.examples.bootapp.controllers;

import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public Resource<String> index() {
        return new Resource<>("Spring Boot Application Example");
    }
}
