package com.savio.coolio.posts.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostsController {

    @GetMapping("/hi")
    public String getString(){
        return "hello, posts!";
    }
}
