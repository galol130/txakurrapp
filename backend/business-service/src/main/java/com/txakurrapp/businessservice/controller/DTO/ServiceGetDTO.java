package com.txakurrapp.businessservice.controller.DTO;


import com.txakurrapp.businessservice.classes.Money;
import com.txakurrapp.businessservice.model.BusinessOwner;

public class ServiceGetDTO {
    private Long id;
    private String name;
    private String description;
    private Money price;
    private boolean available;
    private BusinessOwner businessOwner;

    public ServiceGetDTO() {
    }

    public ServiceGetDTO(Long id, String name, String description, Money price, BusinessOwner businessOwner, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.businessOwner = businessOwner;
        this.available = isAvailable;
    }

    //  Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public BusinessOwner getBusinessOwner() {
        return businessOwner;
    }

    public void setBusinessOwner(BusinessOwner businessOwner) {
        this.businessOwner = businessOwner;
    }

}
