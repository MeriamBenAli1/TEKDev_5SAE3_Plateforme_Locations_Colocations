package com.esprit.ms.microservicemesssagerie.repository;
import com.esprit.ms.microservicemesssagerie.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    //List<Message> findByConversationId(Long conversationId);

   // List<Message> findBySenderIdAndIsReadFalse(Long senderId);
}

