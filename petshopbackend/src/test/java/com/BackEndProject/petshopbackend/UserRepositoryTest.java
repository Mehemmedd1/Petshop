package com.BackEndProject.petshopbackend;

import com.BackEndProject.petshopbackend.Entity.PetEntity.Pet;
import com.BackEndProject.petshopbackend.Entity.UserEntity.AppUser;
import com.BackEndProject.petshopbackend.Repository.PetRepository;
import com.BackEndProject.petshopbackend.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private PetRepository petRepository;

    @Test
    void getPetByName(){
        //given
       Pet savedPet=petRepository.save(new Pet(null,"Max"));

        //when
        Pet pet=petRepository.findByPet("Max");

        //then
        then(pet.getId()).isNotNull();
        then(pet.getPet()).isEqualTo(savedPet.getPet());
    }


}
