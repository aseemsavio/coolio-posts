package com.savio.coolio.posts.controllers;

import com.savio.coolio.posts.service.PostService;
import com.savio.coolio.posts.templates.PostCreationRequest;
import com.savio.coolio.posts.templates.PostCreationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostsController {

    @Autowired
    PostService postService;

    @GetMapping("/hi")
    public String getString(){
        return "hello, posts!";
    }

    @PostMapping("/createPost")
    public ResponseEntity<PostCreationResponse> createPost(@RequestBody PostCreationRequest postCreationRequest){
        return postService.createPost(postCreationRequest);
    }

}
