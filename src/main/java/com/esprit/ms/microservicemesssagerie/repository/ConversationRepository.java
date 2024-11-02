package com.esprit.ms.microservicemesssagerie.repository;
import com.esprit.ms.microservicemesssagerie.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

  //  Optional<Conversation> findByUser1IdAndUser2Id(Long user1Id, Long user2Id);
}

