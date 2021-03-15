package com.txakurrapp.businessservice.controller.impl;

import com.txakurrapp.businessservice.controller.DTO.*;
import com.txakurrapp.businessservice.controller.interfaces.IBusinessOwnerController;
import com.txakurrapp.businessservice.model.Address;
import com.txakurrapp.businessservice.model.Image;
import com.txakurrapp.businessservice.model.Service;
import com.txakurrapp.businessservice.service.interfaces.IBusinessOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BusinessOwnerController implements IBusinessOwnerController {
    @Autowired
    private IBusinessOwnerService businessOwnerService;


    @Override
    @GetMapping("/businesses")
    public List<BusinessOwnerGetDTO> getAllBusinessOwners(){
        return businessOwnerService.getAllBusinessOwners();
    }

    @Override
    @GetMapping("/services")
    public List<ServiceGetDTO> getAllServices(){
        return businessOwnerService.getAllServices();
    }

    @Override
    @GetMapping("/business/{id}")
    public BusinessOwnerGetDTO getBusinessOwnerById(@PathVariable(name="id") Long id) {
        return businessOwnerService.getBusinessOwnerById(id);
    }

    @Override
    @GetMapping("/business/user-id/{id}")
    public BusinessOwnerGetDTO getBusinessOwnerByUserId(@PathVariable(name = "id") Long id){
        return businessOwnerService.getBusinessOwnerByUserId(id);
    }

    @Override
    @GetMapping("/business/personal-id/{personal-id}")
    public BusinessOwnerGetDTO getBusinessOwnerByPersonalId(@PathVariable(name = "personal-id") String personalId) {
        return businessOwnerService.getBusinessOwnerByPersonalId(personalId);
    }

    @Override
    @PostMapping("/business")
    @ResponseStatus(HttpStatus.CREATED)
    public BusinessOwnerGetDTO createBusinessOwner(@RequestBody BusinessOwnerPostDTO businessOwnerPostDTO) {
        return businessOwnerService.createBusinessOwner(businessOwnerPostDTO);
    }

    @Override
    @PutMapping("/business/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BusinessOwnerGetDTO updateBusinessOwnerBasics(@PathVariable(name = "id") Long id, @RequestBody BusinessOwnerUpdateDTO businessOwnerUpdateDTO) {
        return businessOwnerService.updateBusinessOwnerBasics(id, businessOwnerUpdateDTO);
    }

    @Override
    @PutMapping("/business/{id}/address")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BusinessOwnerGetDTO updateBusinessOwnerAddress(@PathVariable(name = "id") Long id, @RequestBody Address address) {
        return businessOwnerService.updateBusinessOwnerAddress(id, address);
    }

    @Override
    @PutMapping("/business/{id}/picture")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BusinessOwnerGetDTO updateBusinessOwnerPicture(@PathVariable(name = "id") Long id, @RequestBody Image profilePicture) {
        return businessOwnerService.updateBusinessOwnerPicture(id, profilePicture);
    }

    @Override
    @PutMapping("/business/{id}/add-service")
    public BusinessOwnerGetDTO addService(@PathVariable(name = "id") Long id, @RequestBody ServicePostDTO servicePostDTO) {
        return businessOwnerService.addService(id, servicePostDTO);
    }

    @Override
    @PutMapping("/business/{id}/update-service/{service-id}")
    public BusinessOwnerGetDTO updateService(@PathVariable(name = "id") Long businessOwnerId, @PathVariable(name="service-id") Long serviceId, @RequestBody ServicePostDTO servicePostDTO) {
        return businessOwnerService.updateService(businessOwnerId, serviceId, servicePostDTO);
    }

    @Override
    @PutMapping("/business/{id}/delete-service/{service-id}")
    public BusinessOwnerGetDTO deleteService(@PathVariable(name = "id") Long businessOwnerId, @PathVariable(name = "service-id") Long serviceId) {
        return businessOwnerService.deleteService(businessOwnerId, serviceId);
    }

    @Override
    @DeleteMapping("/business/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBusinessOwner(@PathVariable(name = "id") Long id) {
        businessOwnerService.deleteBusinessOwner(id);
    }
}
