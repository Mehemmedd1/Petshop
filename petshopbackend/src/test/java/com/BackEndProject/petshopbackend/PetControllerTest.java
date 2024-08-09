package com.BackEndProject.petshopbackend;

import com.BackEndProject.petshopbackend.Controller.PetController;
import com.BackEndProject.petshopbackend.Entity.PetEntity.Pet;
import com.BackEndProject.petshopbackend.Service.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)
@ExtendWith(MockitoExtension.class)
public class PetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @InjectMocks
    private PetController petController;

    private Pet pet;

    @BeforeEach
    void setup(){
        mockMvc= MockMvcBuilders.standaloneSetup(petController).build();
        pet=new Pet();
        pet.setId(5l);
        pet.setPet("Buddy");

    }
    @Test
    void testAddPet() throws Exception{
        when(petService.addPet(any(Pet.class))).thenReturn(pet);

        mockMvc.perform(post("/pet/addPet")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"pet\": \"Buddy\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.pet").value("Buddy"));

    }
    @Test
    void testGetAllPets() throws Exception{
        List<Pet> pets =new ArrayList<>();
        pets.add(pet);
        when(petService.getAllPets()).thenReturn(pets);

        mockMvc.perform(get("/pet/getAllPets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].pet").value("Buddy"));

    }
    @Test
    void testGetPetById() throws Exception {
        // given
        List<Pet> pets = new ArrayList<>();
        pets.add(pet);
        when(petService.getPetById(1L)).thenReturn(pets);

        // when, then
        mockMvc.perform(get("/pet/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].pet").value("Buddy"));
    }

}
