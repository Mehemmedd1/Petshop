package com.BackEndProject.petshopbackend.Repository;

import com.BackEndProject.petshopbackend.Entity.PetEntity.PetFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetFoodRepository extends JpaRepository<PetFood,Long> {
}
