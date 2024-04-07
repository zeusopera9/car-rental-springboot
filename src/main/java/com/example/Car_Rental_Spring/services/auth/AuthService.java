package com.example.Car_Rental_Spring.services.auth;

import com.example.Car_Rental_Spring.dto.SignupRequest;
import com.example.Car_Rental_Spring.dto.UserDto;

public interface AuthService {

    UserDto createCustomer(SignupRequest signupRequest);

    boolean hasCustomerWithEmail(String email);
}
