package com.txakurrapp.edgeservice.controller.DTO.petOwner;

import java.time.LocalDateTime;

public class MessageGetDTO {
    private Long id;
    private Long senderId;
    private Long recipientId;
    private LocalDateTime timeStamp;
    private String messageSubject;
    private String messageBody;

    public MessageGetDTO() {
    }

    public MessageGetDTO(Long id, Long senderId, Long recipientId, LocalDateTime timeStamp, String messageSubject, String messageBody) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.timeStamp = timeStamp;
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
