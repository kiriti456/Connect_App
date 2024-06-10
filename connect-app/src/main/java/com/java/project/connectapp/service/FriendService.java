package com.java.project.connectapp.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.project.connectapp.model.FriendRequest;
import com.java.project.connectapp.model.FriendRequestStatus;
import com.java.project.connectapp.model.User;
import com.java.project.connectapp.repository.FriendRequestRepository;
import com.java.project.connectapp.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class FriendService {

	@Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public FriendRequest sendFriendRequest(Long senderId, Long receiverId) {
        User sender = userRepository.findById(senderId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + senderId));
        User receiver = userRepository.findById(receiverId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + receiverId));

        if (friendRequestRepository.existsBySenderAndReceiverAndStatusIn(sender, receiver, Arrays.asList(FriendRequestStatus.PENDING, FriendRequestStatus.ACCEPTED))
        		|| friendRequestRepository.existsBySenderAndReceiverAndStatusIn(receiver, sender, Arrays.asList(FriendRequestStatus.PENDING, FriendRequestStatus.ACCEPTED))) {
            return null;
        }

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSender(sender);
        friendRequest.setReceiver(receiver);
        friendRequest.setStatus(FriendRequestStatus.PENDING);
        System.out.println("\n\n***************************\nSender: "+friendRequest.getSender().getFullName()+"**********************\n\n");
        return friendRequestRepository.save(friendRequest);
    }

    @Transactional
    public void acceptFriendRequest(Long requestId) {
        FriendRequest friendRequest = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("FriendRequest not found with id: " + requestId));
        System.out.println("\n\n***************************\nAcceptor: "+friendRequest.getSender().getFullName()+"**********************\n\n");
        if (friendRequest.getStatus() == FriendRequestStatus.PENDING) {
            friendRequest.setStatus(FriendRequestStatus.ACCEPTED);
            friendRequestRepository.save(friendRequest);
        } else {
            throw new IllegalStateException("FriendRequest is not in PENDING status and cannot be accepted.");
        }
    }

	public List<FriendRequest> getPendingFriendRequests(User receiver) {
		return friendRequestRepository.findByReceiverAndStatus(receiver, FriendRequestStatus.PENDING);
	}
}
