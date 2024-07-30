package com.BackEndProject.petshopbackend.Repository;

import com.BackEndProject.petshopbackend.Entity.UserEntity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Integer> {


    public AppUser findByEmail(String email);
}
