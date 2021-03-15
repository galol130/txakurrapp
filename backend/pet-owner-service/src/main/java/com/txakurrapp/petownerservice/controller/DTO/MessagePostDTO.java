package com.txakurrapp.petownerservice.controller.DTO;

public class MessagePostDTO {
    private Long recipientId;
    private String messageSubject;
    private String messageBody;

    public MessagePostDTO() {
    }

    public MessagePostDTO(Long recipientId, String messageSubject, String messageBody) {
        this.recipientId = recipientId;
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;
    }



    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
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
