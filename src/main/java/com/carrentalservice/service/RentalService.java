package com.carrentalservice.service;

import com.carrentalservice.entity.Rental;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;

    public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public List<Rental> findAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental updateRental(Rental newRental) {
        Rental existingRental = findRentalById(newRental.getId());
        newRental.setId(existingRental.getId());

        return saveRental(newRental);
    }

    public void deleteAllRentals() {
        rentalRepository.deleteAll();
    }

    public void deleteRentalById(Long id) {
        rentalRepository.deleteById(id);
    }

    public Rental findRentalById(Long id) {
        Optional<Rental> optionalRental = rentalRepository.findById(id);

        if (optionalRental.isPresent()) {
            return optionalRental.get();
        }

        throw new NotFoundException("Rental with id " + id + " does not exist");
    }

    public Long countRental() {
        return rentalRepository.count();
    }

    public Rental findRentalByName(String searchString) {
        return rentalRepository.findRentalByName(searchString);
    }

}
