package com.BackEndProject.petshopbackend.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"","/","/petshop"})
    public String home(){
        return "index";
    }
    @GetMapping({"/contact"})
    public String contact(){
        return "contact";
    }
}
