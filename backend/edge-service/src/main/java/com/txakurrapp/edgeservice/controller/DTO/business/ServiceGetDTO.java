package com.txakurrapp.edgeservice.controller.DTO.business;



public class ServiceGetDTO {
    private Long id;
    private String name;
    private String description;
    private Object price;
    private boolean available;
    private Object businessOwner;

    public ServiceGetDTO() {
    }

    public ServiceGetDTO(Long id, String name, String description, Object price, Object businessOwner, boolean isAvailable) {
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

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Object getBusinessOwner() {
        return businessOwner;
    }

    public void setBusinessOwner(Object businessOwner) {
        this.businessOwner = businessOwner;
    }

}
