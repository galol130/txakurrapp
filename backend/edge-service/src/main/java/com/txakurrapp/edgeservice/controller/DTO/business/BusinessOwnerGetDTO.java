package com.txakurrapp.edgeservice.controller.DTO.business;


import java.time.LocalDate;
import java.util.List;

public class BusinessOwnerGetDTO {
    private Long id;
    private LocalDate signUpDate;
    private Long userId;
    private String firstName;
    private String lastName;
    private String personalId;
    private LocalDate birthDate;
    private Integer phoneNumber;
    private Object address;
    private Object profilePicture;
    private List<Object> services;

    public BusinessOwnerGetDTO() {
    }

    public BusinessOwnerGetDTO(Long id, LocalDate signUpDate, Long userId, String firstName, String lastName, String personalId, LocalDate birthDate, Integer phoneNumber, Object address, Object profilePicture, List<Object> services) {
        this.id = id;
        this.signUpDate = signUpDate;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.profilePicture = profilePicture;
        this.services = services;
    }

    //  Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(LocalDate signUpDate) {
        this.signUpDate = signUpDate;
    }

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

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Object profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Object> getServices() {
        return services;
    }

    public void setServices(List<Object> services) {
        this.services = services;
    }
}
