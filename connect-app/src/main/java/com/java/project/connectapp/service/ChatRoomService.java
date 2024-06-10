package com.java.project.connectapp.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {

	private final Map<String, Set<Long>> chatRooms = new HashMap<>();

    public Optional<String> getChatId(Long senderId, Long receiverId, boolean createIfNotExist) {
        String chatId = senderId + "_" + receiverId;

        if (createIfNotExist) {
            chatRooms.computeIfAbsent(chatId, k -> new HashSet<>(Set.of(senderId, receiverId)));
        }

        return Optional.of(chatId);
    }

    public Set<Long> getParticipants(String chatId) {
        return chatRooms.getOrDefault(chatId, new HashSet<>());
    }
}
