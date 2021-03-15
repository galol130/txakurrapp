package com.txakurrapp.petownerservice.service.interfaces;

import com.txakurrapp.petownerservice.controller.DTO.*;
import com.txakurrapp.petownerservice.model.Address;
import com.txakurrapp.petownerservice.model.Image;

import java.util.List;

public interface IOwnerService {
    List<OwnerGetDTO> getAllOwners();

    OwnerGetDTO getOwnerById(Long id);

    OwnerGetDTO getOwnerByUserId(Long id);

    OwnerGetDTO getOwnerByPersonalId(String personalId);

    OwnerGetDTO createOwner(OwnerPostDTO ownerPostDTO);

    OwnerGetDTO updateOwnerBasics(Long id, OwnerUpdateDTO ownerUpdateDTO);

    OwnerGetDTO updateOwnerAddress(Long id, Address address);

    OwnerGetDTO updateOwnerPicture(Long id, Image image);

    OwnerGetDTO addPet(Long id, PetPostDTO petPostDTO);

    OwnerGetDTO updatePet(Long id, Long petId, PetUpdateDTO petUpdateDTO);

    OwnerGetDTO updatePetPicture(Long id, Long petId, Image petPicture);

    OwnerGetDTO deletePet(Long id, Long petId);

    OwnerGetDTO addFav(Long id, Long serviceId);

    OwnerGetDTO removeFav(Long id, Long serviceId);

    void deleteOwner(Long id);
}
