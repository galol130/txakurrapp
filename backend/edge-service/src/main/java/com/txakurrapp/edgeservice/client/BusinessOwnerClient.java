package com.txakurrapp.edgeservice.client;

import com.txakurrapp.edgeservice.controller.DTO.business.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(name = "businessOwner-service") // For localhost
@FeignClient(name = "businessOwner-service", url="https://txakurrapp-business.herokuapp.com/") // For Herouku
public interface BusinessOwnerClient {

    @GetMapping("/businesses")
    List<BusinessOwnerGetDTO> getAllBusinessOwners();

    @GetMapping("/services")
    List<ServiceGetDTO> getAllServices();

    @GetMapping("/business/{id}")
    BusinessOwnerGetDTO getBusinessOwnerById(@PathVariable(name="id") Long id);

    @GetMapping("/business/user-id/{id}")
    BusinessOwnerGetDTO getBusinessOwnerByUserId(@PathVariable(name = "id") Long id);

    @GetMapping("/business/personal-id/{personal-id}")
    BusinessOwnerGetDTO getBusinessOwnerByPersonalId(@PathVariable(name = "personal-id") String personalId);

    @PostMapping("/business")
    @ResponseStatus(HttpStatus.CREATED)
    BusinessOwnerGetDTO createBusinessOwner(@RequestBody BusinessOwnerPostDTO businessOwnerPostDTO);

    @PutMapping("/business/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    BusinessOwnerGetDTO updateBusinessOwnerBasics(@PathVariable(name = "id") Long id, @RequestBody BusinessOwnerUpdateDTO businessOwnerUpdateDTO);

    @PutMapping("/business/{id}/address")
    @ResponseStatus(HttpStatus.ACCEPTED)
    BusinessOwnerGetDTO updateBusinessOwnerAddress(@PathVariable(name = "id") Long id, @RequestBody AddressPostDTO addressPostDTO);

    @PutMapping("/business/{id}/picture")
    @ResponseStatus(HttpStatus.ACCEPTED)
    BusinessOwnerGetDTO updateBusinessOwnerPicture(@PathVariable(name = "id") Long id, @RequestBody ImagePostDTO imagePostDTO);

    @PutMapping("/business/{id}/add-service")
    BusinessOwnerGetDTO addService(@PathVariable(name = "id") Long id, @RequestBody ServicePostDTO servicePostDTO);

    @PutMapping("/business/{id}/update-service/{service-id}")
    BusinessOwnerGetDTO updateService(@PathVariable(name = "id") Long businessOwnerId, @PathVariable(name="service-id") Long serviceId, @RequestBody ServicePostDTO servicePostDTO);

    @PutMapping("/business/{id}/delete-service/{service-id}")
    BusinessOwnerGetDTO deleteService(@PathVariable(name = "id") Long businessOwnerId, @PathVariable(name = "service-id") Long serviceId);

    @DeleteMapping("/business/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBusinessOwner(@PathVariable(name = "id") Long id);
}
