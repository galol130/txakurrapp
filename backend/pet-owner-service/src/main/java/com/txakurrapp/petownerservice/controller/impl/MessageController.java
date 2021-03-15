package com.txakurrapp.petownerservice.controller.impl;

import com.txakurrapp.petownerservice.controller.DTO.MessagePostDTO;
import com.txakurrapp.petownerservice.controller.interfaces.IMessageController;
import com.txakurrapp.petownerservice.model.Message;
import com.txakurrapp.petownerservice.service.interfaces.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class MessageController implements IMessageController {
    @Autowired
    private IMessageService messageService;

    @Override
    @GetMapping("/owner/{owner-id}/messages-ordered-by-recipient")
    public List<Message> getMessagesBySenderIdOrderedByRecipientId(@PathVariable(name = "owner-id") Long id) {
        return messageService.getMessagesBySenderIdOrderedByRecipientId(id);
    }

    @Override
    @GetMapping("/owner/{owner-id}/messages/recipient/{recipient-id}")
    public List<Message> getMessagesBySenderIdAndRecipientId(@PathVariable(name = "owner-id") Long senderId, @PathVariable(name = "recipient-id") Long recipientId){
        return messageService.getMessagesBySenderIdAndRecipientId(senderId, recipientId);
    }


    @Override
    @GetMapping("/owner/messages-by-sender/{sender-id}")
    public List<Message> getMessagesBySenderId(@PathVariable(name = "sender-id") Long id) {
        return messageService.getMessagesBySenderId(id);
    }

    @Override
    @GetMapping("/owner/messages-by-recipient/{recipient-id}")
    public List<Message> getMessagesByRecipientId(@PathVariable(name = "recipient-id") Long id) {
        return messageService.getMessagesByRecipientId(id);
    }

    @Override
    @PostMapping("/owner/{owner-id}/message")
    public Message createMessage(@PathVariable(name = "owner-id") Long id, @RequestBody MessagePostDTO messagePostDTO) {
        return messageService.createMessage(id, messagePostDTO);
    }

    @Override
    @DeleteMapping("/owner/message/{sender-id}/{recipient-id}")
    public void deleteMessagesByRecipientId(@PathVariable(name = "sender-id") Long senderId, @PathVariable(name = "recipient-id") Long recipientId) {
        messageService.deleteMessagesByRecipientId(senderId, recipientId);
    }

    @Override
    @DeleteMapping("/owner/message/id/{id}")
    public void deleteMessage(@PathVariable(name = "id") Long id) {
        messageService.deleteMessage(id);
    }
}
