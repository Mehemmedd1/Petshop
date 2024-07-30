package com.BackEndProject.petshopbackend.Controller;

import com.BackEndProject.petshopbackend.Entity.PetEntity.Pet;
import com.BackEndProject.petshopbackend.Service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;

    @PostMapping("/addPet")
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet){
        Pet createdPet = petService.addPet(pet);
        return new ResponseEntity<>(createdPet, HttpStatus.CREATED);

    }
    @GetMapping("/getAllPets")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Pet>> getAllPets(){
        List<Pet> allpets=petService.getAllPets();
        return ResponseEntity.ok(allpets);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Pet>> getPetById(@PathVariable Long id){
        List<Pet> pet =petService.getPetById(id);
        return ResponseEntity.ok(pet);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePet(@PathVariable Long id){
        petService.deletePet(id);
        return ResponseEntity.ok("Pet not found this id: "+id);

    }
    @PutMapping("/{id}")
    public Object updatePet(@PathVariable Long id, @RequestBody Pet updpet){
        Pet pet=petService.updatePet(id,updpet);
        if (pet != null) {
            return "Succesfully changed";
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }



}
