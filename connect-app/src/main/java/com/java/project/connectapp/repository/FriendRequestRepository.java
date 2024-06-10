package com.java.project.connectapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.project.connectapp.model.FriendRequest;
import com.java.project.connectapp.model.FriendRequestStatus;
import com.java.project.connectapp.model.User;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

	boolean existsBySenderAndReceiverAndStatusIn(User sender, User receiver, List<FriendRequestStatus> statuses);
	
	List<FriendRequest> findByReceiverAndStatus(User receiver, FriendRequestStatus status);
	
}
