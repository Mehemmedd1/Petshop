package com.BackEndProject.petshopbackend.Service;



import com.BackEndProject.petshopbackend.Entity.UserEntity.AppUser;
import com.BackEndProject.petshopbackend.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser= repo.findByEmail(email);
        if(appUser!=null){
            var springUser= User.withUsername(appUser.getEmail())
                    .password(appUser.getPassword())
                    .roles(appUser.getRole())
                    .build();
            return springUser;
        }
        return null;
    }

    public AppUser getCurrentUser(){
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if(principal instanceof UserDetails){
            email=((UserDetails) principal).getUsername();
        }
        else {
            email=principal.toString();
        }
        AppUser user=repo.findByEmail(email);
        if (user==null){
            throw   new RuntimeException("User not fount in this email: "+ email);
        }
        return user;

    }
}