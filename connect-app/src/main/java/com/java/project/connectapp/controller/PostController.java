package com.java.project.connectapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.project.connectapp.model.Posts;
import com.java.project.connectapp.service.PostService;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/add")
    public ResponseEntity<Posts> addPost(@RequestBody Posts post) {
        Posts newPost = postService.addPost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Posts>> getUserPosts(@PathVariable Long userId) {
        List<Posts> userPosts = postService.getUserPosts(userId);
        return new ResponseEntity<>(userPosts, HttpStatus.OK);
    }
    
    @GetMapping("/friends-public/{userId}")
    public ResponseEntity<List<Posts>> getFriendsPublicPosts(@PathVariable Long userId) {
        List<Posts> posts = postService.getFriendsPublicPosts(userId);
        return ResponseEntity.ok(posts);
    }
    
}