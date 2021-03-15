package com.txakurrapp.petownerservice.controller.interfaces;

import com.txakurrapp.petownerservice.controller.DTO.MessagePostDTO;
import com.txakurrapp.petownerservice.model.Message;

import java.util.List;

public interface IMessageController {
    List<Message> getMessagesBySenderIdOrderedByRecipientId(Long id);

    List<Message> getMessagesBySenderIdAndRecipientId(Long senderId, Long recipientId);

    List<Message> getMessagesBySenderId(Long id);

    List<Message> getMessagesByRecipientId(Long id);

    Message createMessage(Long id, MessagePostDTO messagePostDTO);

    void deleteMessagesByRecipientId(Long senderId, Long recipientId);

    void deleteMessage(Long id);
}
