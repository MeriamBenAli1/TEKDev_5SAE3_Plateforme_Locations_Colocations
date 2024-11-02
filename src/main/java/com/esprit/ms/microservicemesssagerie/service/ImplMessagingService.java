package com.esprit.ms.microservicemesssagerie.service;


import com.esprit.ms.microservicemesssagerie.entity.Conversation;
import com.esprit.ms.microservicemesssagerie.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.ms.microservicemesssagerie.repository.ConversationRepository;
import com.esprit.ms.microservicemesssagerie.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ImplMessagingService implements MessagingService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private MessageRepository messageRepository;

    // Conversation-related methods
    /*
    @Override
    public Conversation getOrCreateConversation(Long user1Id, Long user2Id) {
        Optional<Conversation> conversation = conversationRepository.findByUser1IdAndUser2Id(user1Id, user2Id);
        return conversation.orElseGet(() -> {
            Conversation newConversation = new Conversation();
            newConversation.setUser1Id(user1Id);
            newConversation.setUser2Id(user2Id);
            return conversationRepository.save(newConversation);
        });
    }

     */

    @Override
    public Conversation getOrCreateConversation(Long user1Id, Long user2Id) {
        return null;
    }

    @Override
    public Conversation findConversationById(Long conversationId) {
        return conversationRepository.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation not found with id: " + conversationId));
    }

    // Message-related methods
    @Override
    public Message sendMessage(Long senderId, Long receiverId, String content) {
        Conversation conversation = getOrCreateConversation(senderId, receiverId);

        Message message = new Message();
        message.setSenderId(senderId);
        // message.setConversation(conversation);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessagesInConversation(Long conversationId) {
        return null;
    }

    @Override
    public List<Message> getUnreadMessages(Long receiverId) {
        return null;
    }


    @Override
    public void markMessageAsRead(Long messageId) {
        messageRepository.findById(messageId).ifPresent(message -> {
            message.setRead(true);
            messageRepository.save(message);
        });
    }

    @Override
    public List<Conversation> getAllConversations() {
        return conversationRepository.findAll();
    }
}
