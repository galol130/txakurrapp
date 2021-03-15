package com.txakurrapp.petownerservice.repository;

import com.txakurrapp.petownerservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderIdOrderByRecipientId(Long id);

    List<Message> findMessagesBySenderIdOrderByTimeStampAsc(Long id);

    List<Message> findMessagesByRecipientIdOrderByTimeStampAsc(Long id);

    List<Message> findMessagesBySenderIdAndRecipientId(Long senderId, Long recipientId);

}
