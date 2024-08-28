package com.BackEndProject.petshopbackend.Controller;

import com.BackEndProject.petshopbackend.Dto.RegisterDto;
import com.BackEndProject.petshopbackend.Entity.UserEntity.AppUser;
import com.BackEndProject.petshopbackend.Repository.UserRepository;
import com.BackEndProject.petshopbackend.Service.UserService;
import jakarta.validation.Valid;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@Controller
public class AccountController {
    @Autowired
    private UserService userService;
    @Autowired
    private final UserRepository repo;

    public AccountController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/register")
    public String register(Model model){
        RegisterDto registerDto=new RegisterDto();
        model.addAttribute(registerDto);
        model.addAttribute("success",false);
        return "register";
    }
    @PostMapping("/register")
    public String register(Model model, @Valid @ModelAttribute RegisterDto registerDto, BindingResult result){
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())){
            result.addError(
                    new FieldError("registerDto",
                            "confirmPassword",
                            "Password and Confirm Password do not match"));
        }
        AppUser appUser =repo.findByEmail(registerDto.getEmail());
        if (appUser!= null){
            result.addError(new FieldError("registerDto","email","Email is already used")
            );
        }
        if (result.hasErrors()){
            return "register";
        }
        try {
            var bCryptEncoder=new BCryptPasswordEncoder();


            AppUser newUser=new AppUser();
            newUser.setFirstName(registerDto.getFirstName());
            newUser.setLastName(registerDto.getLastName());
            newUser.setEmail(registerDto.getEmail());
            newUser.setPhone(registerDto.getPhone());
            newUser.setAddress(registerDto.getAddress());
            newUser.setRole("user");
            newUser.setCreatedAt(new Date());
            newUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));

            String randomCode= RandomString.make(64);
            newUser.setVerificationCode(randomCode);


            repo.save(newUser);

            model.addAttribute("registerDto",new RegisterDto());
            model.addAttribute("success",true);



        }
        catch (Exception ex){
            result.addError(new FieldError("registerDto","firstName",ex.getMessage())
            );
        }

        return "register";
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        AppUser user = userService.getCurrentUser(); // Make sure this method is correctly fetching the user
        model.addAttribute("user", user);
        return "profile";
    }




}
