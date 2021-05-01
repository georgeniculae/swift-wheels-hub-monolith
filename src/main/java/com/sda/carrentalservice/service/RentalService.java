package com.sda.carrentalservice.service;

import com.sda.carrentalservice.entity.Car;
import com.sda.carrentalservice.entity.Rental;
import com.sda.carrentalservice.exception.NotFoundException;
import com.sda.carrentalservice.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public Rental saveRental(Rental rental){
        return rentalRepository.save(rental);
    }

    public List<Rental> findAllRentals() { return rentalRepository.findAll(); }

    public void deleteAllRentals() { rentalRepository.deleteAll(); }

    public void deleteRentalById(Long id) {
        rentalRepository.deleteById(id);
    }

    public Rental findRentalById(Long id) {
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isPresent()) {
            Rental rental = optionalRental.get();
            return rental;
        } else {
            throw new NotFoundException("Rental with id " + id + " does not exist.");
        }
    }

    public Long countRental() {
        return rentalRepository.count();
    }

    public Rental findRentalByName(String searchString) {
        return rentalRepository.findRentalByName(searchString);
    }
}
