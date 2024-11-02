package com.esprit.ms.microservicemesssagerie.controller;
import com.esprit.ms.microservicemesssagerie.entity.Conversation;
import com.esprit.ms.microservicemesssagerie.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.esprit.ms.microservicemesssagerie.service.MessagingService;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessagingService messagingService;

    @PostMapping("/send")
    public Message sendMessage(@RequestParam Long senderId, @RequestParam Long receiverId, @RequestParam String content) {
        return messagingService.sendMessage(senderId, receiverId, content);
    }

    @GetMapping("/conversation")
    public Conversation getOrCreateConversation(@RequestParam Long user1Id, @RequestParam Long user2Id) {
        return messagingService.getOrCreateConversation(user1Id, user2Id);
    }

    @GetMapping("/conversation/{conversationId}")
    public List<Message> getMessagesInConversation(@PathVariable Long conversationId) {
        return messagingService.getMessagesInConversation(conversationId);
    }

    @GetMapping("/unread")
    public List<Message> getUnreadMessages(@RequestParam Long receiverId) {
        return messagingService.getUnreadMessages(receiverId);
    }

    @PutMapping("/markAsRead/{messageId}")
    public void markMessageAsRead(@PathVariable Long messageId) {
        messagingService.markMessageAsRead(messageId);
    }

    @GetMapping("/conversations")
    public List<Conversation> getAllConversations() {
        return messagingService.getAllConversations();
    }

}

