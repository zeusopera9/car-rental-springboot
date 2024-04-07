package com.example.Car_Rental_Spring.services.customer;

import com.example.Car_Rental_Spring.dto.BookACarDto;
import com.example.Car_Rental_Spring.dto.CarDto;
import com.example.Car_Rental_Spring.entity.BookACar;

import java.util.List;

public interface CustomerService {

    List<CarDto> getAllCars();

    boolean bookACar(BookACarDto bookACarDto);

    CarDto getCarById(Long carId);

    List<BookACarDto> getBookingsByUserId(Long userId);

}
