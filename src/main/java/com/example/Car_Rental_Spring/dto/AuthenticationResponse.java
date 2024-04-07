package com.example.Car_Rental_Spring.dto;

import com.example.Car_Rental_Spring.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private UserRole userRole;

    private Long userId;

}
