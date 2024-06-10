package com.java.project.connectapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.project.connectapp.model.FriendRequest;
import com.java.project.connectapp.model.User;
import com.java.project.connectapp.repository.UserRepository;
import com.java.project.connectapp.service.FriendService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/friends")
public class FriendController {

	@Autowired
    private FriendService friendService;
	
	@Autowired
    private UserRepository userRepository;

    @PostMapping("/send-request")
    public ResponseEntity<FriendRequest> sendFriendRequest(@RequestBody FriendRequest friendRequestDTO) {
    	System.out.println(friendRequestDTO.toString());
    	if(friendRequestDTO.getSender().getId() != friendRequestDTO.getReceiver().getId()) {
    		FriendRequest friendRequest = friendService.sendFriendRequest(friendRequestDTO.getSender().getId(), friendRequestDTO.getReceiver().getId());
        	return ResponseEntity.status(HttpStatus.CREATED).body(friendRequest);
    	}
    	return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping("/accept-request")
    public ResponseEntity<Void> acceptFriendRequest(@RequestParam Long requestId) {
        friendService.acceptFriendRequest(requestId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/pending-requests")
    public ResponseEntity<List<FriendRequest>> getPendingFriendRequests(@RequestParam int receiverUserId) {
    	User receiver = userRepository.findById(receiverUserId);
    	if (receiver == null) {
    	    throw new EntityNotFoundException("User not found with user name: " + receiverUserId);
    	}
        List<FriendRequest> pendingRequests = friendService.getPendingFriendRequests(receiver);
        return ResponseEntity.ok(pendingRequests);
    }
    
}