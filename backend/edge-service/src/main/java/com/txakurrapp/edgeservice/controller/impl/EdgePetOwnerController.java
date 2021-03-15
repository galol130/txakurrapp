package com.txakurrapp.edgeservice.controller.impl;

import com.txakurrapp.edgeservice.client.BusinessOwnerClient;
import com.txakurrapp.edgeservice.client.PetOwnerClient;
import com.txakurrapp.edgeservice.controller.DTO.business.BusinessOwnerGetDTO;
import com.txakurrapp.edgeservice.controller.DTO.petOwner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/petowner")
public class EdgePetOwnerController {
    @Autowired
    private PetOwnerClient petOwnerClient;

    @Autowired
    private BusinessOwnerClient businessOwnerClient;


    @GetMapping("/user-id/{id}")
    @PreAuthorize("hasRole('PETOWNER') or hasRole('BUSINESS')")
    public OwnerGetDTO getOwnerByUserId(@PathVariable(name = "id") Long id){
        return petOwnerClient.getOwnerByUserId(id);
    }

    @GetMapping("/owner/{id}")
    @PreAuthorize("hasRole('PETOWNER') or hasRole('BUSINESS')")
    public OwnerGetDTO getPetOwnerById(@PathVariable(name = "id") Long id){
        return petOwnerClient.getOwnerById(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('PETOWNER')")
    public OwnerGetDTO createPetOwner(@RequestBody OwnerPostDTO ownerPostDTO){
        return petOwnerClient.createOwner(ownerPostDTO);
    }


    /**********      Pets ENDPOINTS       *************/

    @PostMapping("/{user-id}/add-pet")
    @PreAuthorize("hasRole('PETOWNER')")
    public OwnerGetDTO addPet(@PathVariable(name = "user-id") Long id, @RequestBody PetPostDTO petPostDTO){
        OwnerGetDTO petOwner = petOwnerClient.getOwnerByUserId(id);
        return petOwnerClient.addPet(petOwner.getId(), petPostDTO);
    }

    @PutMapping("/{user-id}/remove-pet/{pet-id}")
    @PreAuthorize("hasRole('PETOWNER')")
    public OwnerGetDTO removePet(@PathVariable(name = "user-id") Long id, @PathVariable(name = "pet-id") Long petId){
        OwnerGetDTO petOwner = petOwnerClient.getOwnerByUserId(id);
        return petOwnerClient.deletePet(petOwner.getId(), petId);
    }

    /**********      Favorites ENDPOINTS       *************/

    @PutMapping("/{user-id}/add-fav/{service-id}")
    @PreAuthorize("hasRole('PETOWNER')")
    public OwnerGetDTO addServiceToFav(@PathVariable(name = "user-id") Long userId, @PathVariable(name = "service-id") Long serviceId){
        OwnerGetDTO petOwner = petOwnerClient.getOwnerByUserId(userId);
        return petOwnerClient.addFav(petOwner.getId(), serviceId);
    }

    @PutMapping("/{user-id}/remove-fav/{service-id}")
    @PreAuthorize("hasRole('PETOWNER')")
    public OwnerGetDTO RemoveServiceFromFav(@PathVariable(name = "user-id") Long userId, @PathVariable(name = "service-id") Long serviceId){
        OwnerGetDTO petOwner = petOwnerClient.getOwnerByUserId(userId);
        return petOwnerClient.removeFav(petOwner.getId(), serviceId);
    }


/**********      MESSAGES ENDPOINTS       *************/

    @GetMapping("/{user-id}/messages")
    @PreAuthorize("hasRole('PETOWNER')")
    public List<MessageGetDTO> getMessagesBySender(@PathVariable(name = "user-id") Long id){
        OwnerGetDTO petOwner = petOwnerClient.getOwnerByUserId(id);
        return petOwnerClient.getMessagesBySenderIdOrderedByRecipientId(petOwner.getId());
    }

    @GetMapping("/{user-id}/messages/recipient/{recipient-id}")
    @PreAuthorize("hasRole('PETOWNER')")
    public List<MessageGetDTO> getMessagesBySenderAndRecipient(@PathVariable(name = "user-id") Long senderUserId, @PathVariable(name = "recipient-id") Long recipientUserId){
        OwnerGetDTO petOwner = petOwnerClient.getOwnerByUserId(senderUserId);
        BusinessOwnerGetDTO businessOwner = businessOwnerClient.getBusinessOwnerByUserId(recipientUserId);
        return petOwnerClient.getMessagesBySenderIdAndRecipientId(petOwner.getId(), businessOwner.getId());
    }

    @PostMapping("/{user-id}/message")
    @PreAuthorize("hasRole('PETOWNER')")
    public MessageGetDTO createMessage(@PathVariable(name = "user-id") Long userId, @RequestBody MessagePostDTO messagePostDTO){
        OwnerGetDTO petOwner = petOwnerClient.getOwnerByUserId(userId);
        return petOwnerClient.createMessage(petOwner.getId(), messagePostDTO);
    }

    @DeleteMapping("/message/{message-id}")
    @PreAuthorize("hasRole('PETOWNER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessageById(@PathVariable(name = "message-id") Long id){
        petOwnerClient.deleteMessage(id);
    }



}
