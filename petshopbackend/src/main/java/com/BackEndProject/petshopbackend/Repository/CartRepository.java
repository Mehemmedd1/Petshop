package com.BackEndProject.petshopbackend.Repository;

import com.BackEndProject.petshopbackend.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
}
