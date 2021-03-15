package com.txakurrapp.petownerservice.controller.DTO;

import com.txakurrapp.petownerservice.model.Image;

import java.time.LocalDate;

public class PetUpdateDTO {
    private String type;
    private String name;
    private String breed;
    private LocalDate birthDate;
    private Double weight;
    private String otherInfo;

    public PetUpdateDTO() {
    }

    public PetUpdateDTO(String type, String name, String breed, LocalDate birthDate, Double weight, String otherInfo) {
        this.type = type;
        this.name = name;
        this.breed = breed;
        this.birthDate = birthDate;
        this.weight = weight;
        this.otherInfo = otherInfo;
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
}
