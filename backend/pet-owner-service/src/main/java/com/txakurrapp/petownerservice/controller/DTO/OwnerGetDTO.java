package com.txakurrapp.petownerservice.controller.DTO;

import com.txakurrapp.petownerservice.model.Address;
import com.txakurrapp.petownerservice.model.Image;
import com.txakurrapp.petownerservice.model.Pet;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class OwnerGetDTO {
    private Long id;
    private LocalDate signUpDate;
    private Long userId;
    private String firstName;
    private String lastName;
    private String personalId;
    private LocalDate birthDate;
    private Long phoneNumber;
    private Set<Long> favs;

    private Address address;

    private Image profilePicture;

    private List<Pet> pets;


    public OwnerGetDTO() {
    }

    public OwnerGetDTO(Long id, LocalDate signUpDate, Long userId,String firstName, String lastName, String personalId, LocalDate birthDate, Long phoneNumber, Set<Long> favs, Address address, Image profilePicture, List<Pet> pets) {
        this.id = id;
        this.signUpDate = signUpDate;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.favs = favs;
        this.address = address;
        this.profilePicture = profilePicture;
        this.pets = pets;
    }

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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
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

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public Set<Long> getFavs() {
        return favs;
    }

    public void setFavs(Set<Long> favs) {
        this.favs = favs;
    }
}
