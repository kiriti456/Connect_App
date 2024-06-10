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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.project.connectapp.model.User;
import com.java.project.connectapp.service.AuthService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        User newUser = authService.signUp(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {

        User getUser = authService.findByUsernameAndPassword(username, password);

        if (getUser != null) {
        	return ResponseEntity.ok("success");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not found");
    }
    
    @GetMapping("/current-user/{userName}")
    public Long returnCurrentUserId(@PathVariable String userName) {
    	try {
	    	Long id = authService.findByUsername(userName);
	    	System.out.println("User Id: "+ id);
	    	return id;
    	}
    	catch(Exception e) {
    		return (long) 0;
    	}
    }
    
    @GetMapping("/search-users")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String query) {
        List<User> searchResults = authService.searchUsers(query);
        return ResponseEntity.ok(searchResults);
    }
    
}
