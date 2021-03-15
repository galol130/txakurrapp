package com.txakurrapp.petownerservice.service.impl;

import com.txakurrapp.petownerservice.controller.DTO.MessagePostDTO;
import com.txakurrapp.petownerservice.model.Message;
import com.txakurrapp.petownerservice.repository.MessageRepository;
import com.txakurrapp.petownerservice.service.interfaces.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements IMessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> getMessagesBySenderIdOrderedByRecipientId(Long id) {
        return messageRepository.findBySenderIdOrderByRecipientId(id);
    }

    @Override
    public List<Message> getMessagesBySenderIdAndRecipientId(Long senderId, Long recipientId) {
        return messageRepository.findMessagesBySenderIdAndRecipientId(senderId, recipientId);
    }

    @Override
    public List<Message> getMessagesBySenderId(Long id) {
        return messageRepository.findMessagesBySenderIdOrderByTimeStampAsc(id);
    }

    @Override
    public List<Message> getMessagesByRecipientId(Long id) {
        return messageRepository.findMessagesByRecipientIdOrderByTimeStampAsc(id);
    }

    @Override
    public Message createMessage(Long id, MessagePostDTO messagePostDTO) {
        Message message = new Message(
                id,
                messagePostDTO.getRecipientId(),
                messagePostDTO.getMessageSubject(),
                messagePostDTO.getMessageBody()
        );

        return messageRepository.save(message);
    }

    @Override
    public void deleteMessagesByRecipientId(Long senderId, Long recipientId) {
        List<Message> messages = messageRepository.findMessagesBySenderIdAndRecipientId(senderId, recipientId);
        if (messages.size() > 0) {
            messageRepository.deleteAll(messages);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No messages found with those sender and recipient IDs");
        }
    }

    @Override
    public void deleteMessage(Long id) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        if (messageOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No message found with that ID");

        Message message = messageOptional.get();
        messageRepository.delete(message);
    }
}
