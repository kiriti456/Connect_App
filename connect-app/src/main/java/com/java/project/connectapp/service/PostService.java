package com.java.project.connectapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.project.connectapp.model.Posts;
import com.java.project.connectapp.model.User;
import com.java.project.connectapp.repository.PostRepository;
import com.java.project.connectapp.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PostService {

	@Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Posts addPost(Posts post) {
        // Assuming the authenticated user is adding a post
        User user = userRepository.findById(post.getUser().getId()).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + post.getUser().getId()));
        post.setUser(user);

        return postRepository.save(post);
    }

    public List<Posts> getUserPosts(Long userId) {
        return postRepository.findByUserId(userId);
    }

	public List<Posts> getFriendsPublicPosts(Long userId) {
		System.out.println("\n\n\n========================="+userId+"================\n\n\n");
		return postRepository.findFriendsPublicPosts(userId);
	}
}
