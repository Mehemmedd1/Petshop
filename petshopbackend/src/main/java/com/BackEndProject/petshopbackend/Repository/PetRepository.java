package com.BackEndProject.petshopbackend.Repository;

import com.BackEndProject.petshopbackend.Entity.PetEntity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {
}
