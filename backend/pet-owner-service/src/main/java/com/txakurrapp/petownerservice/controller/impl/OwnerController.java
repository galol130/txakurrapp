package com.txakurrapp.petownerservice.controller.impl;

import com.txakurrapp.petownerservice.controller.DTO.*;
import com.txakurrapp.petownerservice.controller.interfaces.IOwnerController;
import com.txakurrapp.petownerservice.model.Address;
import com.txakurrapp.petownerservice.model.Image;
import com.txakurrapp.petownerservice.service.interfaces.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class OwnerController implements IOwnerController {
    @Autowired
    private IOwnerService ownerService;


    @Override
    @GetMapping("/owners")
    public List<OwnerGetDTO> getOwners() {
        return ownerService.getAllOwners();
    }

    @Override
    @GetMapping("/owner/{id}")
    public OwnerGetDTO getOwnerById(@PathVariable(name = "id") Long id) {
        return ownerService.getOwnerById(id);
    }

    @Override
    @GetMapping("/owner/user-id/{id}")
    public OwnerGetDTO getOwnerByUserId(@PathVariable(name = "id") Long id){
        return ownerService.getOwnerByUserId(id);
    }

    @Override
    @GetMapping("/owner/personal-id/{personal-id}")
    public OwnerGetDTO getOwnerByPersonalId(@PathVariable(value = "personal-id") String personalId) {
        return ownerService.getOwnerByPersonalId(personalId);
    }

    @Override
    @PostMapping("/owner")
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerGetDTO createOwner(@RequestBody OwnerPostDTO ownerPostDTO) {
        return ownerService.createOwner(ownerPostDTO);
    }

    @Override
    @PutMapping("/owner/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OwnerGetDTO updateOwnerBasics(@PathVariable(value = "id") Long id, @RequestBody OwnerUpdateDTO ownerUpdateDTO) {
        return ownerService.updateOwnerBasics(id, ownerUpdateDTO);
    }

    @Override
    @PutMapping("/owner/{id}/address")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OwnerGetDTO updateOwnerAddress(@PathVariable(value = "id") Long id, @RequestBody Address address) {
        return ownerService.updateOwnerAddress(id, address);
    }

    @Override
    @PutMapping("/owner/{id}/picture")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OwnerGetDTO updateOwnerPicture(@PathVariable(value = "id") Long id, @RequestBody Image image) {
        return ownerService.updateOwnerPicture(id, image);
    }

    @Override
    @PutMapping("/owner/{id}/add-pet")
    public OwnerGetDTO addPet(@PathVariable(name = "id") Long id, @RequestBody PetPostDTO petPostDTO) {
        return ownerService.addPet(id, petPostDTO);
    }

    @Override
    @PutMapping("/owner/{id}/update-pet/{pet-id}")
    public OwnerGetDTO updatePet(@PathVariable(name = "id") Long id, @PathVariable(name = "pet-id") Long petId, @RequestBody PetUpdateDTO petUpdateDTO) {
        return ownerService.updatePet(id, petId, petUpdateDTO);
    }

    @Override
    @PutMapping("owner/{id}/update-pet-image/{pet-id}")
    public OwnerGetDTO updatePetPicture(@PathVariable(name = "id") Long id, @PathVariable(name = "pet-id") Long petId, @RequestBody Image petPicture) {
        return ownerService.updatePetPicture(id, petId, petPicture);
    }

    @Override
    @PutMapping("/owner/{id}/delete-pet/{pet-id}")
    public OwnerGetDTO deletePet(@PathVariable(name = "id") Long id, @PathVariable(name = "pet-id") Long petId) {
        return ownerService.deletePet(id, petId);
    }

    @Override
    @PutMapping("/owner/{id}/add-fav/{service-id}")
    public OwnerGetDTO addFav(@PathVariable(name = "id") Long id, @PathVariable(name = "service-id") Long serviceId){
        return ownerService.addFav(id, serviceId);
    }

    @Override
    @PutMapping("/owner/{id}/remove-fav/{service-id}")
    public OwnerGetDTO removeFav(@PathVariable(name = "id") Long id, @PathVariable(name = "service-id") Long serviceId){
        return ownerService.removeFav(id, serviceId);
    }


    @Override
    @DeleteMapping("/owner/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOwner(@PathVariable(value = "id") Long id) {
        ownerService.deleteOwner(id);
    }
}
