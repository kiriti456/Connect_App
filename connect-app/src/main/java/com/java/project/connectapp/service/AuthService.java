package com.java.project.connectapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.project.connectapp.model.User;
import com.java.project.connectapp.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User signUp(User user) {
        return userRepository.save(user);
    }

	public Long findByUsername(String userName) {
		return userRepository.findByUsername(userName).getId();
	}

	public List<User> searchUsers(String query) {
        return userRepository.findByUsernameContainingIgnoreCase(query);
    }
}
