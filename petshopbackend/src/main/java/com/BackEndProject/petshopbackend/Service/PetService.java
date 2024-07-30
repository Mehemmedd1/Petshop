package com.BackEndProject.petshopbackend.Service;

import com.BackEndProject.petshopbackend.Entity.PetEntity.Pet;
import com.BackEndProject.petshopbackend.Repository.PetRepository;
import org.springframework.http.HttpMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetById(Long id) {
        Pet petId=petRepository.findById(id)
                .orElseThrow(()->new RuntimeException("pet not found"));
        return Collections.singletonList(petId);
    }

    public void deletePet(Long id) {
        Pet petId=petRepository.findById(id)
                .orElseThrow(()->new RuntimeException("pet not found"));
         petRepository.deleteById(id);
    }

    public Pet updatePet(Long id, Pet updpet) {
        Optional<Pet> pet=petRepository.findById(id);

        if (pet.isPresent()) {
            Pet changedPet=pet.get();
            changedPet.setPet(updpet.getPet());
            petRepository.save(changedPet);
            return changedPet;

        }
        else {
            return null;
        }
    }
}
