package com.BackEndProject.petshopbackend.Entity.PetEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "petfood")
public class PetFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id",nullable = false)

    private Long id;
    @Column(name = "food_name")
    private String foodName;
    private double price;
    private String description;


}
