package com.txakurrapp.petownerservice.controller.DTO;

import com.txakurrapp.petownerservice.model.Image;
import com.txakurrapp.petownerservice.model.Owner;

import java.time.LocalDate;

public class PetGetDTO {
    private Long id;
    private String type;
    private String name;
    private Image picture;
    private String breed;
    private LocalDate birthDate;
    private Double weight;
    private String otherInfo;
    private Owner owner;

    public PetGetDTO() {
    }

    public PetGetDTO(Long id, String type, String name, Image picture, String breed, LocalDate birthDate, Double weight, String otherInfo, Owner owner) {
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

    public Image getPicture() {
        return picture;
    }

    public void setPicture(Image picture) {
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

}
