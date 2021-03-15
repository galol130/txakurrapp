package com.txakurrapp.businessservice.controller.DTO;

import com.txakurrapp.businessservice.model.Address;
import com.txakurrapp.businessservice.model.Image;

import java.time.LocalDate;

public class BusinessOwnerPostDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String personalId;
    private LocalDate birthDate;
    private Integer phoneNumber;
    private Address address;
    private Image profilePicture;


    public BusinessOwnerPostDTO() {
    }

    public BusinessOwnerPostDTO(Long userId, String firstName, String lastName, String personalId, LocalDate birthDate, Integer phoneNumber, Address address, Image profilePicture) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.profilePicture = profilePicture;
    }


    //  Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }
}
