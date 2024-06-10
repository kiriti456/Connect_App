package com.java.project.connectapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.java.project.connectapp.model.ChatNotification;
import com.java.project.connectapp.model.Message;
import com.java.project.connectapp.service.ChatRoomService;
import com.java.project.connectapp.service.MessageService;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload Message message) {
        var chatId = chatRoomService.getChatId(message.getSender().getId(), message.getReceiver().getId(), true);
        message.setChatId(chatId.get());

        Message saved = messageService.save(message);

        messagingTemplate.convertAndSendToUser(
                message.getReceiver().getId().toString(), "/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSender().getId(),
                        saved.getSender().getFullName()));
    }

    @GetMapping("/history/{senderId}/{receiverId}")
    public ResponseEntity<List<Message>> getChatHistory(
            @PathVariable Long senderId,
            @PathVariable Long receiverId) {
        List<Message> chatHistory = messageService.getChatHistory(senderId, receiverId);
        return ResponseEntity.ok(chatHistory);
    }

    @SubscribeMapping("/user/queue/messages")
    public String handleSubscription() {
        return "Subscribed to /user/queue/messages";
    }
}
