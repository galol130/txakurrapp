package com.txakurrapp.businessservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.txakurrapp.businessservice.classes.Money;

import javax.persistence.*;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Embedded
    private Money price;
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "business_owner_id")
    @JsonIgnore
    private BusinessOwner businessOwner;

    public Service() {
    }

    public Service(String name, String description, Money price, BusinessOwner businessOwner) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.businessOwner = businessOwner;
        this.available = true;
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
