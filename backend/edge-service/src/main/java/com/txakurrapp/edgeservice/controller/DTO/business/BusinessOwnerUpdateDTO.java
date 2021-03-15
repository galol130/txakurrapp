package com.txakurrapp.edgeservice.controller.DTO.business;

import java.time.LocalDate;

public class BusinessOwnerUpdateDTO {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Integer phoneNumber;


    public BusinessOwnerUpdateDTO() {
    }

    public BusinessOwnerUpdateDTO(String firstName, String lastName, LocalDate birthDate, Integer phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    //  Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
