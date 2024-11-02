package com.esprit.ms.microservicemesssagerie.service;
import com.esprit.ms.microservicemesssagerie.entity.Conversation;
import com.esprit.ms.microservicemesssagerie.entity.Message;

import java.util.List;

public interface MessagingService {

    // Conversation methods
    Conversation getOrCreateConversation(Long user1Id, Long user2Id);

    Conversation findConversationById(Long conversationId);

    // Message methods
    Message sendMessage(Long senderId, Long receiverId, String content);

    List<Message> getMessagesInConversation(Long conversationId);

    List<Message> getUnreadMessages(Long receiverId);

    void markMessageAsRead(Long messageId);

    List<Conversation> getAllConversations();
}

