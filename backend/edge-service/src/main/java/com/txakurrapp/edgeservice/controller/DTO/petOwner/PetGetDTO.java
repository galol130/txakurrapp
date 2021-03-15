package com.txakurrapp.edgeservice.controller.DTO.petOwner;


import java.time.LocalDate;

public class PetGetDTO {
    private Long id;
    private String type;
    private String name;
    private Object picture;
    private String breed;
    private LocalDate birthDate;
    private Double weight;
    private String otherInfo;
    private Object owner;

    public PetGetDTO() {
    }

    public PetGetDTO(Long id, String type, String name, Object picture, String breed, LocalDate birthDate, Double weight, String otherInfo, Object owner) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.picture = picture;
        this.breed = breed;
        this.birthDate = birthDate;
        this.weight = weight;
        this.otherInfo = otherInfo;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getPicture() {
        return picture;
    }

    public void setPicture(Object picture) {
        this.picture = picture;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

}
