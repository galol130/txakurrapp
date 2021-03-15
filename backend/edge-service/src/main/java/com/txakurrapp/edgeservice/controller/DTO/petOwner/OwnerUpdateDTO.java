package com.txakurrapp.edgeservice.controller.DTO.petOwner;

import java.time.LocalDate;

public class OwnerUpdateDTO {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Integer phoneNumber;

    public OwnerUpdateDTO() {
    }

    public OwnerUpdateDTO(String firstName, String lastName, LocalDate birthDate, Integer phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

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
