package com.BackEndProject.petshopbackend.Entity;

import com.BackEndProject.petshopbackend.Entity.PetEntity.PetFood;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PetFood petFood;

    private int quantity;

}
