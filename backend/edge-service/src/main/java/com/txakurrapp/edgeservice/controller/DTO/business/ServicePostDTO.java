package com.txakurrapp.edgeservice.controller.DTO.business;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

public class ServicePostDTO {
    @NotBlank
    private String name;
    private String description;
    @Length(min = 3, max = 3, message = "Please, use the 3-char for currency")
    private String currency;
    @DecimalMin(value = "0.01")
    @DecimalMax(value = "9999.99")
    private Double amount;


    public ServicePostDTO() {
    }

    public ServicePostDTO(@NotBlank String name, String description, @Length(min = 3, max = 3, message = "Please, use the 3-char for currency") String currency, @DecimalMin(value = "0.01") @DecimalMax(value = "9999.99") Double amount) {
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
