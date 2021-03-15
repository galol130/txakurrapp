package com.txakurrapp.petownerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate signUpDate;
    private Long userId;
    private String firstName;
    private String lastName;
    private String personalId;
    private LocalDate birthDate;
    private Long phoneNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "favs", joinColumns = {@JoinColumn(name = "owner_id")})
    @Column(name = "fav_id")
    private Set<Long> favs = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @JsonIgnore
    private Address address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    @JsonIgnore
    private Image profilePicture;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<Pet> pets;

    public Owner() {
    }

    public Owner(Long userId, String firstName, String lastName, String personalId, LocalDate birthDate, Long phoneNumber, Address address, Image profilePicture) {
        this.signUpDate = LocalDate.now();
        this.favs.add(0L);
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
