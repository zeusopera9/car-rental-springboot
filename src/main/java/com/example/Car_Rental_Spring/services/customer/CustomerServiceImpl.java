package com.example.Car_Rental_Spring.services.customer;

import com.example.Car_Rental_Spring.dto.BookACarDto;
import com.example.Car_Rental_Spring.dto.CarDto;
import com.example.Car_Rental_Spring.entity.BookACar;
import com.example.Car_Rental_Spring.entity.Car;
import com.example.Car_Rental_Spring.entity.User;
import com.example.Car_Rental_Spring.enums.BookCarStatus;
import com.example.Car_Rental_Spring.repository.BookACarRepository;
import com.example.Car_Rental_Spring.repository.CarRepository;
import com.example.Car_Rental_Spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CarRepository carRepository;

    private final UserRepository userRepository;

    private final BookACarRepository bookACarRepository;

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public boolean bookACar(BookACarDto bookACarDto) {
        Optional<Car> optionalCar = carRepository.findById(bookACarDto.getCarId());
        Optional<User> optionalUser = userRepository.findById(bookACarDto.getUserId());
        if(optionalCar.isPresent() && optionalUser.isPresent()) {
            Car existingCar = optionalCar.get();
            BookACar bookACar = new BookACar();
            bookACar.setUser(optionalUser.get());
            bookACar.setCar(existingCar);
            bookACar.setBookCarStatus(BookCarStatus.PENDING);
            bookACar.setFromDate(bookACarDto.getFromDate());
            bookACar.setToDate(bookACarDto.getToDate());
            long diffInMilliSeconds = bookACarDto.getToDate().getTime() - bookACarDto.getFromDate().getTime();
            long diffInSeconds = diffInMilliSeconds / 1000;
            long diffInMinutes = diffInSeconds / 60;
            long diffInHours = diffInMinutes / 60;
            long days = diffInHours / 24;
            bookACar.setDays(days);
            bookACar.setPrice(existingCar.getPrice() * days);
            bookACarRepository.save(bookACar);
            return true;
        }
        return false;
    }

    @Override
    public CarDto getCarById(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        return optionalCar.map(Car::getCarDto).orElse(null);
    }

    @Override
    public List<BookACarDto> getBookingsByUserId(Long userId) {
        return bookACarRepository.findAllByUserId(userId).stream().map(BookACar::getBookACarDto).collect(Collectors.toList());

    }
}
