package com.txakurrapp.edgeservice.controller.impl;

import com.txakurrapp.edgeservice.client.BusinessOwnerClient;
import com.txakurrapp.edgeservice.controller.DTO.business.BusinessOwnerGetDTO;
import com.txakurrapp.edgeservice.controller.DTO.business.BusinessOwnerPostDTO;
import com.txakurrapp.edgeservice.controller.DTO.business.ServiceGetDTO;
import com.txakurrapp.edgeservice.controller.DTO.business.ServicePostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/business")
public class EdgeBusinessController {
    @Autowired
    private BusinessOwnerClient businessOwnerClient;

    @GetMapping("/user-id/{id}")
    @PreAuthorize("hasRole('PETOWNER') or hasRole('BUSINESS')")
    public BusinessOwnerGetDTO getBusinessOwnerByUserId(@PathVariable(name = "id") Long id){
        return businessOwnerClient.getBusinessOwnerByUserId(id);
    }

    @GetMapping("/owner/{id}")
    @PreAuthorize("hasRole('PETOWNER') or hasRole('BUSINESS')")
    public BusinessOwnerGetDTO getBusinessOwnerById(@PathVariable(name = "id") Long id){
        return businessOwnerClient.getBusinessOwnerById(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('BUSINESS')")
    public BusinessOwnerGetDTO createBusinessOwner(@RequestBody BusinessOwnerPostDTO businessOwnerPostDTO){
        return businessOwnerClient.createBusinessOwner(businessOwnerPostDTO);
    }


    /****************   Services ENDPOINTS   ****************/

    @GetMapping("/services")
    public List<ServiceGetDTO> getAllServices(){
        return businessOwnerClient.getAllServices();
    }

    @PostMapping("/{user-id}/add-service")
    @PreAuthorize("hasRole('BUSINESS')")
    public BusinessOwnerGetDTO addService(@PathVariable(name = "user-id") Long id, @RequestBody ServicePostDTO servicePostDTO){
        BusinessOwnerGetDTO businessOwner = businessOwnerClient.getBusinessOwnerByUserId(id);
        return businessOwnerClient.addService(businessOwner.getId(), servicePostDTO);
    }

    @PutMapping("/{user-id}/remove-service/{service-id}")
    @PreAuthorize("hasRole('BUSINESS')")
    public BusinessOwnerGetDTO removeService(@PathVariable(name = "user-id") Long id, @PathVariable(name = "service-id") Long serviceId){
        BusinessOwnerGetDTO businessOwner = businessOwnerClient.getBusinessOwnerByUserId(id);
        return businessOwnerClient.deleteService(businessOwner.getId(), serviceId);
    }

    @GetMapping("/businesses")
    @PreAuthorize("hasRole('BUSINESS')")
    public List<BusinessOwnerGetDTO> getAllBusinesses(){
        return businessOwnerClient.getAllBusinessOwners();
    }


    /**********      MESSAGES ENDPOINTS       *************/

}
