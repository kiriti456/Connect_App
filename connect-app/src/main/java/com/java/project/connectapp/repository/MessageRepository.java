package com.java.project.connectapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.project.connectapp.model.Message;
import com.java.project.connectapp.model.MessageStatus;
import com.java.project.connectapp.model.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findByReceiverIdAndStatusOrderBySendDateAsc(Long receiverId, MessageStatus status);
	
	List<Message> findBySenderAndReceiverOrderBySendDateAsc(User sender, User receiver);
	
}