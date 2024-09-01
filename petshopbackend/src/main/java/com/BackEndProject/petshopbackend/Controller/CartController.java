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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String addPetFoodToCart(@RequestParam Long foodId, @RequestParam int quantity, RedirectAttributes redirectAttributes){
        cartService.addPetFoodToCart(foodId,quantity);
        redirectAttributes.addFlashAttribute("message",
                "Product added to cart successfully");
        return "redirect:/petFood";
    }
    @PostMapping("/cart/remove")
    public String removePetFoodFromCart(@RequestParam Long cartId,RedirectAttributes redirectAttributes){
        cartService.removePetFoodFromCart(cartId);
        redirectAttributes.addFlashAttribute("message",
                "Product removed from cart successfully");
        return "redirect:/cart";
    }
    @PostMapping("/cart/clear")
    public String clearCart(RedirectAttributes redirectAttributes){
        cartService.clearCart();
        redirectAttributes.addFlashAttribute("message",
                "Cart cleared successfully!");
        return "redirect:/cart";
    }

}