package com.BackEndProject.petshopbackend.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {
    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    @Column(unique = true,nullable = false)
    private String email;

    private String phone;
    private String address;
    @Size(min=6,message = "Minimum 6 characters")
    private String password;

    private String confirmPassword;

}
