package com.BackEndProject.petshopbackend.Entity.PetEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "pets")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pet_name")
    private String pet;
    private String type;
    private String age;





}
