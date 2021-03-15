package com.txakurrapp.edgeservice.client;

import com.txakurrapp.edgeservice.controller.DTO.petOwner.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(name = "petOwner-service") // For localhost
@FeignClient(name = "petOwner-service", url = "https://txakurrapp-pet-owner.herokuapp.com/") // For Heroku
// toDo:solve this please!
public interface PetOwnerClient {

    @GetMapping("/owners")
    List<OwnerGetDTO> getOwners();

    @GetMapping("/owner/{id}")
    OwnerGetDTO getOwnerById(@PathVariable(name = "id") Long id);

    @GetMapping("/owner/user-id/{id}")
    OwnerGetDTO getOwnerByUserId(@PathVariable(name = "id") Long id);

    @GetMapping("/owner/personal-id/{personal-id}")
    OwnerGetDTO getOwnerByPersonalId(@PathVariable(value = "personal-id") String personalId);

    @PostMapping("/owner")
    @ResponseStatus(HttpStatus.CREATED)
    OwnerGetDTO createOwner(@RequestBody OwnerPostDTO ownerPostDTO);

    @PutMapping("/owner/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    OwnerGetDTO updateOwnerBasics(@PathVariable(value = "id") Long id, @RequestBody OwnerUpdateDTO ownerUpdateDTO);

    @PutMapping("/owner/{id}/address")
    @ResponseStatus(HttpStatus.ACCEPTED)
    OwnerGetDTO updateOwnerAddress(@PathVariable(value = "id") Long id, @RequestBody AddressPostDTO address);

    @PutMapping("/owner/{id}/picture")
    @ResponseStatus(HttpStatus.ACCEPTED)
    OwnerGetDTO updateOwnerPicture(@PathVariable(value = "id") Long id, @RequestBody ImagePostDTO image);

    @PutMapping("/owner/{id}/add-pet")
    OwnerGetDTO addPet(@PathVariable(name = "id") Long id, @RequestBody PetPostDTO petPostDTO);

    @PutMapping("/owner/{id}/update-pet/{pet-id}")
    OwnerGetDTO updatePet(@PathVariable(name = "id") Long id, @PathVariable(name = "pet-id") Long petId, @RequestBody PetUpdateDTO petUpdateDTO);

    @PutMapping("owner/{id}/update-pet-image/{pet-id}")
    OwnerGetDTO updatePetPicture(@PathVariable(name = "id") Long id, @PathVariable(name = "pet-id") Long petId, @RequestBody ImagePostDTO petPicture);

    @PutMapping("/owner/{id}/delete-pet/{pet-id}")
    OwnerGetDTO deletePet(@PathVariable(name = "id") Long id, @PathVariable(name = "pet-id") Long petId);

    @PutMapping("/owner/{id}/add-fav/{service-id}")
    OwnerGetDTO addFav(@PathVariable(name = "id") Long id, @PathVariable(name = "service-id") Long serviceId);

    @PutMapping("/owner/{id}/remove-fav/{service-id}")
     OwnerGetDTO removeFav(@PathVariable(name = "id") Long id, @PathVariable(name = "service-id") Long serviceId);

    @DeleteMapping("/owner/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteOwner(@PathVariable(value = "id") Long id);


/*******************    MESSAGE ENDPOINTS    ******************/

    @GetMapping("/owner/{owner-id}/messages-ordered-by-recipient")
    List<MessageGetDTO> getMessagesBySenderIdOrderedByRecipientId(@PathVariable(name = "owner-id") Long id);

    @GetMapping("/owner/{owner-id}/messages/recipient/{recipient-id}")
    List<MessageGetDTO> getMessagesBySenderIdAndRecipientId(@PathVariable(name = "owner-id") Long senderId, @PathVariable(name = "recipient-id") Long recipientId);

    @GetMapping("/owner/messages-by-sender/{sender-id}")
    List<MessageGetDTO> getMessagesBySenderId(@PathVariable(name = "sender-id") Long id);

    @GetMapping("/owner/messages-by-recipient/{recipient-id}")
    List<MessageGetDTO> getMessagesByRecipientId(@PathVariable(name = "recipient-id") Long id);

    @PostMapping("/owner/{owner-id}/message")
    MessageGetDTO createMessage(@PathVariable(name = "owner-id") Long id, @RequestBody MessagePostDTO messagePostDTO);

    @DeleteMapping("/owner/message/{sender-id}/{recipient-id}")
    void deleteMessagesByRecipientId(@PathVariable(name = "sender-id") Long senderId, @PathVariable(name = "recipient-id") Long recipientId);

    @DeleteMapping("/owner/message/id/{id}")
    void deleteMessage(@PathVariable(name = "id") Long id);



}
