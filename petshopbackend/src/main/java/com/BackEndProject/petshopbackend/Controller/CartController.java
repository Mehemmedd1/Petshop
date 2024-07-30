package com.BackEndProject.petshopbackend.Controller;

import com.BackEndProject.petshopbackend.Repository.PetFoodRepository;
import com.BackEndProject.petshopbackend.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private PetFoodRepository petFoodRepository;

    @GetMapping("/cart")
    public String viewCart(Model model){
        model.addAttribute("cartItems",cartService.getCartItems());
        return "cart";
    }
    @GetMapping("/petFood")
    public String viewProducts(Model model) {
        model.addAttribute("petFood", petFoodRepository.findAll());
        return "petfood";
    }
    @PostMapping("/cart/add")
    public String addPetFoodToCart(@RequestParam Long petFoodId,@RequestParam int quantity){
        cartService.addPetFoodToCart(petFoodId,quantity);
        return "redirect:/cart";
    }
    @PostMapping("/cart/remove")
    public String removePetFoodFromCart(@RequestParam Long cartId){
        cartService.removePetFoodFromCart(cartId);
        return "redirect:/cart";
    }
    @PostMapping("/cart/clear")
    public String clearCart(){
        cartService.clearCart();
        return "redirect:/cart";
    }

}
