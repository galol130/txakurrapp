package com.txakurrapp.businessservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String streetName;
    private Integer number;
    private String apt;
    private String otherInfo;
    private String postalCode;
    private String city;
    private String province;
    private String country;

    @OneToOne(mappedBy = "address", orphanRemoval = true)
    @JsonIgnore
    private BusinessOwner businessOwner;

    public Address() {
    }

    public Address(String streetName, Integer number, String apt, String otherInfo, String postalCode, String city, String province, String country) {
        this.streetName = streetName;
        this.number = number;
        this.apt = apt;
        this.otherInfo = otherInfo;
        this.postalCode = postalCode;
        this.city = city;
        this.province = province;
        this.country = country;
    }

//  Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
