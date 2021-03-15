package com.txakurrapp.businessservice.service.interfaces;

import com.txakurrapp.businessservice.controller.DTO.*;
import com.txakurrapp.businessservice.model.Address;
import com.txakurrapp.businessservice.model.Image;
import com.txakurrapp.businessservice.model.Service;

import java.util.List;

public interface IBusinessOwnerService {
    List<BusinessOwnerGetDTO> getAllBusinessOwners();

    List<ServiceGetDTO> getAllServices();

    BusinessOwnerGetDTO getBusinessOwnerById(Long id);

    BusinessOwnerGetDTO getBusinessOwnerByPersonalId(String personalId);

    BusinessOwnerGetDTO getBusinessOwnerByUserId(Long id);

    BusinessOwnerGetDTO createBusinessOwner(BusinessOwnerPostDTO businessOwnerPostDTO);

    BusinessOwnerGetDTO updateBusinessOwnerBasics(Long id, BusinessOwnerUpdateDTO businessOwnerUpdateDTO);

    BusinessOwnerGetDTO updateBusinessOwnerAddress(Long id, Address address);

    BusinessOwnerGetDTO updateBusinessOwnerPicture(Long id, Image picture);

    BusinessOwnerGetDTO addService(Long id, ServicePostDTO servicePostDTO);

    BusinessOwnerGetDTO updateService(Long id, Long serviceId, ServicePostDTO servicePostDTO);

    BusinessOwnerGetDTO deleteService(Long id, Long serviceId);

    void deleteBusinessOwner(Long id);


}
