package com.txakurrapp.businessservice.service.impl;

import com.txakurrapp.businessservice.classes.Money;
import com.txakurrapp.businessservice.controller.DTO.*;
import com.txakurrapp.businessservice.model.Address;
import com.txakurrapp.businessservice.model.BusinessOwner;
import com.txakurrapp.businessservice.model.Image;
import com.txakurrapp.businessservice.repository.AddressRepository;
import com.txakurrapp.businessservice.repository.BusinessOwnerRepository;
import com.txakurrapp.businessservice.repository.ImageRepository;
import com.txakurrapp.businessservice.repository.ServiceRepository;
import com.txakurrapp.businessservice.service.interfaces.IBusinessOwnerService;
import com.txakurrapp.businessservice.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessOwnerService implements IBusinessOwnerService {
    @Autowired
    private BusinessOwnerRepository businessOwnerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ServiceRepository serviceRepository;


    @Override
    public List<BusinessOwnerGetDTO> getAllBusinessOwners() {
        List<BusinessOwner> businessOwners = businessOwnerRepository.findAll();
        List<BusinessOwnerGetDTO> businessOwnerGetDTOS = new ArrayList<>();

        for (BusinessOwner bo : businessOwners) {
            businessOwnerGetDTOS.add(businessOwnerToGetDTO(bo));
        }

        return businessOwnerGetDTOS;
    }

    @Override
    public List<ServiceGetDTO> getAllServices() {
        List<com.txakurrapp.businessservice.model.Service> services = serviceRepository.findAll();
        List<ServiceGetDTO> serviceGetDTOS = new ArrayList<>();

        for (com.txakurrapp.businessservice.model.Service ser : services) {
            serviceGetDTOS.add(serviceToGetDTO(ser));
        }

        return serviceGetDTOS;
    }

    @Override
    public BusinessOwnerGetDTO getBusinessOwnerById(Long id) {
        Optional<BusinessOwner> businessOwnerOptional = businessOwnerRepository.findById(id);
        if (businessOwnerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Owner with that ID");

        return businessOwnerToGetDTO(businessOwnerOptional.get());
    }

    @Override
    public BusinessOwnerGetDTO getBusinessOwnerByPersonalId(String personalId) {
        Optional<BusinessOwner> businessOwnerOptional = businessOwnerRepository.findByPersonalId(personalId);
        if (businessOwnerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Owner with that Personal ID");

        return businessOwnerToGetDTO(businessOwnerOptional.get());
    }

    @Override
    public BusinessOwnerGetDTO getBusinessOwnerByUserId(Long id) {
        Optional<BusinessOwner> businessOwnerOptional = businessOwnerRepository.findByUserId(id);
        if (businessOwnerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Owner with that User ID");

        return businessOwnerToGetDTO(businessOwnerOptional.get());
    }

    @Override
    public BusinessOwnerGetDTO createBusinessOwner(BusinessOwnerPostDTO businessOwnerPostDTO) {
        BusinessOwner businessOwner;

        //First create the Address object and persist it
        Address address;
        try {
            address = new Address(
                    businessOwnerPostDTO.getAddress().getStreetName(),
                    businessOwnerPostDTO.getAddress().getNumber(),
                    businessOwnerPostDTO.getAddress().getApt(),
                    businessOwnerPostDTO.getAddress().getOtherInfo(),
                    businessOwnerPostDTO.getAddress().getPostalCode(),
                    businessOwnerPostDTO.getAddress().getCity(),
                    businessOwnerPostDTO.getAddress().getProvince(),
                    businessOwnerPostDTO.getAddress().getCountry()
            );
            address = addressRepository.save(address);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Address information");
        }

        //Then create the Image object and persist it
        Image profilePicture;
        try {
            profilePicture = new Image(
                    businessOwnerPostDTO.getProfilePicture().getName(),
                    businessOwnerPostDTO.getProfilePicture().getType(),
                    businessOwnerPostDTO.getProfilePicture().getPicByte()
            );
            profilePicture = imageRepository.save(profilePicture);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Image information");
        }

        try {
            businessOwner = new BusinessOwner(
                    businessOwnerPostDTO.getUserId(),
                    businessOwnerPostDTO.getFirstName(),
                    businessOwnerPostDTO.getLastName(),
                    businessOwnerPostDTO.getPersonalId(),
                    businessOwnerPostDTO.getBirthDate(),
                    businessOwnerPostDTO.getPhoneNumber(),
                    address,
                    profilePicture
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong request Body for BusinessOwnerPostDTO");
        }
        businessOwner = businessOwnerRepository.save(businessOwner);
        return businessOwnerToGetDTO(businessOwner);
    }

    @Override
    public BusinessOwnerGetDTO updateBusinessOwnerBasics(Long id, BusinessOwnerUpdateDTO businessOwnerUpdateDTO) {
        Optional<BusinessOwner> businessOwnerOptional = businessOwnerRepository.findById(id);
        if (businessOwnerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Owner with that ID");

        BusinessOwner businessOwner = businessOwnerOptional.get();
        if (!businessOwnerUpdateDTO.getFirstName().isBlank())
            businessOwner.setFirstName(businessOwnerUpdateDTO.getFirstName());
        if (!businessOwnerUpdateDTO.getLastName().isBlank())
            businessOwner.setLastName(businessOwnerUpdateDTO.getLastName());
        if (businessOwnerUpdateDTO.getBirthDate() != null)
            businessOwner.setBirthDate(businessOwnerUpdateDTO.getBirthDate());
        if (businessOwnerUpdateDTO.getPhoneNumber() > 10)
            businessOwner.setPhoneNumber(businessOwnerUpdateDTO.getPhoneNumber());

        businessOwner = businessOwnerRepository.save(businessOwner);
        return businessOwnerToGetDTO(businessOwner);
    }

    @Override
    public BusinessOwnerGetDTO updateBusinessOwnerAddress(Long id, Address address) {
        Optional<BusinessOwner> businessOwnerOptional = businessOwnerRepository.findById(id);
        if (businessOwnerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Owner with that ID");

        BusinessOwner businessOwner = businessOwnerOptional.get();
        try {
            businessOwner.setAddress(address);
            businessOwner = businessOwnerRepository.save(businessOwner);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't update business owner address");
        }
        return businessOwnerToGetDTO(businessOwner);
    }

    @Override
    public BusinessOwnerGetDTO updateBusinessOwnerPicture(Long id, Image picture) {
        Optional<BusinessOwner> businessOwnerOptional = businessOwnerRepository.findById(id);
        if (businessOwnerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Owner with that ID");

        BusinessOwner businessOwner = businessOwnerOptional.get();
        try {
            businessOwner.setProfilePicture(picture);
            businessOwner = businessOwnerRepository.save(businessOwner);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't update business owner profile pic");
        }
        return businessOwnerToGetDTO(businessOwner);
    }

    @Override
    public BusinessOwnerGetDTO addService(Long id, ServicePostDTO servicePostDTO) {
        // First we lookup the owner
        Optional<BusinessOwner> businessOwnerOptional = businessOwnerRepository.findById(id);
        if (businessOwnerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Owner with that ID");
        BusinessOwner businessOwner = businessOwnerOptional.get();

        Money price;
        try {
            BigDecimal amount = BigDecimal.valueOf(servicePostDTO.getAmount());
            Currency currency = Currency.getInstance(servicePostDTO.getCurrency());
            price = new Money(amount, currency);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong amount and/or currency");
        }

        // Create new service, persist it and save changes
        com.txakurrapp.businessservice.model.Service service;
        try {
            service = new com.txakurrapp.businessservice.model.Service(
                    servicePostDTO.getName(),
                    servicePostDTO.getDescription(),
                    price,
                    businessOwner
            );
            serviceRepository.save(service);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Service data, couldn't create new");
        }

        return businessOwnerToGetDTO(businessOwner);
    }

    @Override
    public BusinessOwnerGetDTO updateService(Long id, Long serviceId, ServicePostDTO servicePostDTO) {
        // First lookup if the Business Owner exists
        Optional<BusinessOwner> businessOwnerOptional = businessOwnerRepository.findById(id);
        if (businessOwnerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Owner with that ID");
        BusinessOwner businessOwner = businessOwnerOptional.get();
        List<com.txakurrapp.businessservice.model.Service> actualServices = businessOwner.getServices();

        // Then, lookup if the service exists in the Business Owner's list
        Optional<com.txakurrapp.businessservice.model.Service> serviceOptional =
                actualServices.stream().filter(serv -> serv.getId() == serviceId).findFirst();
        if (serviceOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Service with that ID");
        com.txakurrapp.businessservice.model.Service service = serviceOptional.get();

        //Update all the values for this service
        Money price;
        try {
            BigDecimal amount = BigDecimal.valueOf(servicePostDTO.getAmount());
            Currency currency = Currency.getInstance(servicePostDTO.getCurrency());
            price = new Money(amount, currency);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong amount and/or currency");
        }
        service.setName(servicePostDTO.getName());
        service.setDescription(servicePostDTO.getDescription());
        service.setPrice(price);

        // Persist and save
        service = serviceRepository.save(service);

        return businessOwnerToGetDTO(businessOwner);
    }

    @Override
    public BusinessOwnerGetDTO deleteService(Long id, Long serviceId) {
        // First lookup if the Business Owner exists
        Optional<BusinessOwner> businessOwnerOptional = businessOwnerRepository.findById(id);
        if (businessOwnerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Owner with that ID");
        BusinessOwner businessOwner = businessOwnerOptional.get();
        List<com.txakurrapp.businessservice.model.Service> actualServices = businessOwner.getServices();

        // Then, lookup if the service exists in the Business Owner's list
        Optional<com.txakurrapp.businessservice.model.Service> serviceOptional =
                actualServices.stream().filter(serv -> serv.getId() == serviceId).findFirst();
        if (serviceOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Service with that ID");
        com.txakurrapp.businessservice.model.Service serviceToRemove = serviceOptional.get();

        // If everything is ok, remove from repository
        serviceRepository.delete(serviceToRemove);

        return businessOwnerToGetDTO(businessOwner);
    }

    @Override
    public void deleteBusinessOwner(Long id) {
        Optional<BusinessOwner> businessOwnerOptional = businessOwnerRepository.findById(id);
        if (businessOwnerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Owner with that ID");

        businessOwnerRepository.delete(businessOwnerOptional.get());
    }

    private BusinessOwnerGetDTO businessOwnerToGetDTO(BusinessOwner businessOwner) {
        return new BusinessOwnerGetDTO(
                businessOwner.getId(),
                businessOwner.getSignUpDate(),
                businessOwner.getUserId(),
                businessOwner.getFirstName(),
                businessOwner.getLastName(),
                businessOwner.getPersonalId(),
                businessOwner.getBirthDate(),
                businessOwner.getPhoneNumber(),
                businessOwner.getAddress(),
                businessOwner.getProfilePicture(),
                businessOwner.getServices()
        );
    }

    private ServiceGetDTO serviceToGetDTO(com.txakurrapp.businessservice.model.Service ser) {
        return new ServiceGetDTO(
                ser.getId(),
                ser.getName(),
                ser.getDescription(),
                ser.getPrice(),
                ser.getBusinessOwner(),
                ser.isAvailable()
        );
    }
}
