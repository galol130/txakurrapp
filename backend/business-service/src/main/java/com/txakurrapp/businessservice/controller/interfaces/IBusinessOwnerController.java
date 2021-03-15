package com.txakurrapp.businessservice.controller.interfaces;

import com.txakurrapp.businessservice.controller.DTO.*;
import com.txakurrapp.businessservice.model.Address;
import com.txakurrapp.businessservice.model.Image;
import com.txakurrapp.businessservice.model.Service;

import java.util.List;

public interface IBusinessOwnerController {

    List<BusinessOwnerGetDTO> getAllBusinessOwners();

    List<ServiceGetDTO> getAllServices();

    BusinessOwnerGetDTO getBusinessOwnerById(Long id);

    BusinessOwnerGetDTO getBusinessOwnerByUserId(Long id);

    BusinessOwnerGetDTO getBusinessOwnerByPersonalId(String personalId);

    BusinessOwnerGetDTO createBusinessOwner(BusinessOwnerPostDTO businessOwnerPostDTO);

    BusinessOwnerGetDTO updateBusinessOwnerBasics(Long id, BusinessOwnerUpdateDTO businessOwnerUpdateDTO);

    BusinessOwnerGetDTO updateBusinessOwnerAddress(Long id, Address address);

    BusinessOwnerGetDTO updateBusinessOwnerPicture(Long id, Image profilePicture);

    BusinessOwnerGetDTO addService(Long id, ServicePostDTO servicePostDTO);

    BusinessOwnerGetDTO updateService(Long businessOwnerId, Long serviceId, ServicePostDTO servicePostDTO);

    BusinessOwnerGetDTO deleteService(Long businessOwnerId, Long serviceId);

    void deleteBusinessOwner(Long id);

}
