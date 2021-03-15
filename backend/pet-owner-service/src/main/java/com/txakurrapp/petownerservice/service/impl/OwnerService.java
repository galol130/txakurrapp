package com.txakurrapp.petownerservice.service.impl;

import com.txakurrapp.petownerservice.controller.DTO.*;
import com.txakurrapp.petownerservice.model.Address;
import com.txakurrapp.petownerservice.model.Image;
import com.txakurrapp.petownerservice.model.Owner;
import com.txakurrapp.petownerservice.model.Pet;
import com.txakurrapp.petownerservice.repository.AddressRepository;
import com.txakurrapp.petownerservice.repository.ImageRepository;
import com.txakurrapp.petownerservice.repository.OwnerRepository;
import com.txakurrapp.petownerservice.repository.PetRepository;
import com.txakurrapp.petownerservice.service.interfaces.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class OwnerService implements IOwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PetRepository petRepository;


    @Override
    public List<OwnerGetDTO> getAllOwners() {
        List<Owner> ownerList = ownerRepository.findAll();
        List<OwnerGetDTO> ownersDTO = new ArrayList<>();
        for (Owner owner : ownerList) {
            ownersDTO.add(ownerToGetDTO(owner));
        }
        return ownersDTO;
    }

    @Override
    public OwnerGetDTO getOwnerById(Long id) {
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if (ownerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No owner with that ID");

        return ownerToGetDTO(ownerOptional.get());
    }

    @Override
    public OwnerGetDTO getOwnerByUserId(Long id) {
        Optional<Owner> ownerOptional = ownerRepository.findOwnerByUserId(id);
        if (ownerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No owner with that User ID");

        return ownerToGetDTO(ownerOptional.get());
    }

    @Override
    public OwnerGetDTO getOwnerByPersonalId(String personalId) {
        Optional<Owner> ownerOptional = ownerRepository.findOwnerByPersonalId(personalId);
        if (ownerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No owner with that Personal ID");

        return ownerToGetDTO(ownerOptional.get());
    }

    @Override
    public OwnerGetDTO createOwner(OwnerPostDTO ownerPostDTO) {
        Owner owner;

        //First create the Address object and persist it
        Address address;
        try {
            address = new Address(
                    ownerPostDTO.getAddress().getStreetName(),
                    ownerPostDTO.getAddress().getNumber(),
                    ownerPostDTO.getAddress().getApt(),
                    ownerPostDTO.getAddress().getOtherInfo(),
                    ownerPostDTO.getAddress().getPostalCode(),
                    ownerPostDTO.getAddress().getCity(),
                    ownerPostDTO.getAddress().getProvince(),
                    ownerPostDTO.getAddress().getCountry()
            );
            address = addressRepository.save(address);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Address information");
        }

        //Then create the Image object and persist it
        Image profilePicture;
        try {
            profilePicture = new Image(
                    ownerPostDTO.getProfilePicture().getName(),
                    ownerPostDTO.getProfilePicture().getType(),
                    ownerPostDTO.getProfilePicture().getPicByte()
            );
            profilePicture = imageRepository.save(profilePicture);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Image information");
        }

        //Next, create the Owner and persist it
        try {
            owner = new Owner(
                    ownerPostDTO.getUserId(),
                    ownerPostDTO.getFirstName(),
                    ownerPostDTO.getLastName(),
                    ownerPostDTO.getPersonalId(),
                    ownerPostDTO.getBirthDate(),
                    ownerPostDTO.getPhoneNumber(),
                    address,
                    profilePicture
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong request Body for OwnerPostDTO");
        }

        owner = ownerRepository.save(owner);
        return ownerToGetDTO(owner);
    }

    @Override
    public OwnerGetDTO updateOwnerBasics(Long id, OwnerUpdateDTO ownerUpdateDTO) {
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if (ownerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No owner with that ID");

        Owner owner = ownerOptional.get();
        if (!ownerUpdateDTO.getFirstName().isBlank())
            owner.setFirstName(ownerUpdateDTO.getFirstName());
        if (!ownerUpdateDTO.getLastName().isBlank())
            owner.setLastName(ownerUpdateDTO.getLastName());
        if (ownerUpdateDTO.getBirthDate() != null)
            owner.setBirthDate(ownerUpdateDTO.getBirthDate());
        if (ownerUpdateDTO.getPhoneNumber() > 10)
            owner.setPhoneNumber(ownerUpdateDTO.getPhoneNumber());

        try {
            owner = ownerRepository.save(owner);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't update the owner basic info");
        }
        return ownerToGetDTO(owner);
    }

    @Override
    public OwnerGetDTO updateOwnerAddress(Long id, Address address) {
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if (ownerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No owner with that ID");
        Owner owner = ownerOptional.get();

        // Remove the old address from repository
//        addressRepository.delete(owner.getAddress());

        // Create the new Address object and assign it to the owner
        try {
            Address newAddress = addressRepository.save(address);
            owner.setAddress(newAddress);
            owner = ownerRepository.save(owner);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't update owner address");
        }
        return ownerToGetDTO(owner);
    }

    @Override
    public OwnerGetDTO updateOwnerPicture(Long id, Image picture) {
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if (ownerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No owner with that ID");
        Owner owner = ownerOptional.get();

        // Remove the old Picture
//        imageRepository.delete(owner.getProfilePicture());

        // Create the new Image object and assign it to the owner
        try {
            Image newProfilePicture = imageRepository.save(picture);
            owner.setProfilePicture(newProfilePicture);
            owner = ownerRepository.save(owner);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't update owner Profile Picture");
        }
        return ownerToGetDTO(owner);
    }

    @Override
    public OwnerGetDTO addPet(Long id, PetPostDTO petPostDTO) {
        // First we lookup the owner
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if (ownerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No owner with that ID");
        Owner owner = ownerOptional.get();

        //If there is an image, we create the object
        Image picture;
        if (!Objects.isNull(petPostDTO.getPicture())) {
            try {
                picture = new Image(
                        petPostDTO.getPicture().getName(),
                        petPostDTO.getPicture().getType(),
                        petPostDTO.getPicture().getPicByte()
                );
                picture = imageRepository.save(picture);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image data is not correct");
            }
        } else // If not, set a default image
        {
            picture = setDefaultImage();
        }

        // Create the new Pet, persist it  and save changes
        Pet pet;
        try {
            pet = new Pet(
                    petPostDTO.getType(),
                    petPostDTO.getName(),
                    picture,
                    petPostDTO.getBreed(),
                    petPostDTO.getBirthDate(),
                    petPostDTO.getWeight(),
                    petPostDTO.getOtherInfo(),
                    owner
            );
            pet = petRepository.save(pet);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Pet data, couldn't create new");
        }

        return ownerToGetDTO(owner);
    }

    @Override
    public OwnerGetDTO updatePet(Long id, Long petId, PetUpdateDTO petUpdateDTO) {
        // First we lookup the owner
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if (ownerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No owner with that ID");
        Owner owner = ownerOptional.get();
        List<Pet> petList = owner.getPets();

        // Then lookup the pet in the owner's pet list
        Optional<Pet> petOptional = petList.stream().filter(pet -> pet.getId() == petId).findFirst();
        if (petOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pet with that ID in owner's pet list");
        Pet pet = petOptional.get();

        // Update all the properties that are not empty or null. Then persist it
        if (!petUpdateDTO.getType().isBlank())
            pet.setType(petUpdateDTO.getType());
        if (!petUpdateDTO.getName().isBlank())
            pet.setName(petUpdateDTO.getName());
        if (petUpdateDTO.getBirthDate() != null)
            pet.setBirthDate(petUpdateDTO.getBirthDate());
        if (petUpdateDTO.getWeight() != null)
            pet.setWeight(petUpdateDTO.getWeight());
        if (!petUpdateDTO.getOtherInfo().isBlank())
            pet.setOtherInfo(petUpdateDTO.getOtherInfo());

        pet = petRepository.save(pet);

        return ownerToGetDTO(owner);
    }

    @Override
    public OwnerGetDTO updatePetPicture(Long id, Long petId, Image petPicture) {
        // First we lookup the owner
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if (ownerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No owner with that ID");
        Owner owner = ownerOptional.get();
        List<Pet> petList = owner.getPets();

        // Then lookup the pet in the owner's pet list
        Optional<Pet> petOptional = petList.stream().filter(pet -> pet.getId() == petId).findFirst();
        if (petOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pet with that ID in owner's pet list");
        Pet pet = petOptional.get();

        // Remove old picture
//        imageRepository.delete(pet.getPicture());

        // Create the new Image
        Image picture;
        try {
            picture = new Image(
                    petPicture.getName(),
                    petPicture.getType(),
                    petPicture.getPicByte()
            );
            picture = imageRepository.save(picture);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image data is not correct");
        }

        // Update and persist the pet
        pet.setPicture(picture);
        petRepository.save(pet);

        return ownerToGetDTO(owner);
    }

    @Override
    public OwnerGetDTO deletePet(Long id, Long petId) {
        // First we lookup the owner
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if (ownerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No owner with that ID");
        Owner owner = ownerOptional.get();
        List<Pet> petList = owner.getPets();

        // Then lookup the pet in the owner's pet list
        Optional<Pet> petOptional = petList.stream().filter(pet -> pet.getId() == petId).findFirst();
        if (petOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pet with that ID in owner's pet list");
        Pet pet = petOptional.get();

        petRepository.delete(pet);

        return ownerToGetDTO(owner);
    }

    @Override
    public OwnerGetDTO addFav(Long id, Long serviceId) {
        // First we lookup the owner
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if (ownerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No owner with that ID");
        Owner owner = ownerOptional.get();
        Set<Long> favs = owner.getFavs();
        favs.add(serviceId);
        owner.setFavs(favs);
        owner = ownerRepository.save(owner);

        return ownerToGetDTO(owner);
    }

    @Override
    public OwnerGetDTO removeFav(Long id, Long serviceId) {
        // First we lookup the owner
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if (ownerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No owner with that ID");
        Owner owner = ownerOptional.get();
        Set<Long> favs = owner.getFavs();

        // Remove the service ID from the list and persist
        try {
            favs.remove(serviceId);
            owner.setFavs(favs);
            owner = ownerRepository.save(owner);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No service with that ID in the owner's favs list");
        }

        return ownerToGetDTO(owner);
    }

    @Override
    public void deleteOwner(Long id) {
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if (ownerOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No owner with that ID");

        try {
            ownerRepository.delete(ownerOptional.get());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't delete owner");
        }
    }

    private OwnerGetDTO ownerToGetDTO(Owner owner) {
        return new OwnerGetDTO(
                owner.getId(),
                owner.getSignUpDate(),
                owner.getUserId(),
                owner.getFirstName(),
                owner.getLastName(),
                owner.getPersonalId(),
                owner.getBirthDate(),
                owner.getPhoneNumber(),
                owner.getFavs(),
                owner.getAddress(),
                owner.getProfilePicture(),
                owner.getPets()
        );
    }

    private Image setDefaultImage() {
        Image picture = new Image(
                "default_image",
                "image",
                "".getBytes()
        );
        return imageRepository.save(picture);
    }
}
