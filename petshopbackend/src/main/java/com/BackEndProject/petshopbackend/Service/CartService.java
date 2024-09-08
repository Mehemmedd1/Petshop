package com.BackEndProject.petshopbackend.Service;

import com.BackEndProject.petshopbackend.Entity.Cart;
import com.BackEndProject.petshopbackend.Entity.PetEntity.PetFood;
import com.BackEndProject.petshopbackend.Repository.CartRepository;
import com.BackEndProject.petshopbackend.Repository.PetFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private PetFoodRepository petFoodRepository;
    public List<Cart> getCartItems() {
        return  cartRepository.findAll();

    }

    public void addPetFoodToCart(Long petFoodId, int quantity) {
        PetFood petFood=petFoodRepository.findById(petFoodId).orElseThrow(()->new RuntimeException());
        Cart cart=new Cart();
        cart.setPetFood(petFood);
        cart.setQuantity(quantity);
        cartRepository.save(cart);
    }

    public void removePetFoodFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public void clearCart() {
        cartRepository.deleteAll();
    }
}
