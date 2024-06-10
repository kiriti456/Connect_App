package com.java.project.connectapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.java.project.connectapp.model.Message;
import com.java.project.connectapp.model.MessageStatus;
import com.java.project.connectapp.model.User;
import com.java.project.connectapp.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    private List<String> messages = new ArrayList<>();

    public void saveMessage(Long senderId, String content) {
        messages.add(senderId + ": " + content);
    }

    public List<String> getAllMessages() {
        return new ArrayList<>(messages);
    }
    
    public List<Message> getChatHistory(Long senderId, Long receiverId) {
    	User sender = new User();
    	User receiver = new User();
    	sender.setId(senderId);
    	receiver.setId(receiverId);
        return messageRepository.findBySenderAndReceiverOrderBySendDateAsc(sender, receiver);
    }

    public Message sendMessage(Message message) {
        Message savedMessage = messageRepository.save(message);
        messagingTemplate.convertAndSendToUser(
                message.getReceiver().getUsername(),
                "/topic/messages",
                savedMessage);

        return savedMessage;
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getUnreadMessages(Long receiverId) {
        return messageRepository.findByReceiverIdAndStatusOrderBySendDateAsc(receiverId, MessageStatus.RECEIVED);
    }    
}
