package com.sda.carrentalservice.service;

import com.sda.carrentalservice.entity.Car;
import com.sda.carrentalservice.entity.RentalOffice;
import com.sda.carrentalservice.exception.NotFoundException;
import com.sda.carrentalservice.repository.RentalOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalOfficeService {

    private final RentalOfficeRepository rentalOfficeRepository;

    @Autowired
    public RentalOfficeService(RentalOfficeRepository rentalOfficeRepository) {
        this.rentalOfficeRepository = rentalOfficeRepository;
    }

    public RentalOffice saveRentalOffice(RentalOffice rentalOffice) {
        return rentalOfficeRepository.save(rentalOffice);
    }

    public List<RentalOffice> findAllRentalOffices() {
        return rentalOfficeRepository.findAll();
    }

    public void deleteRentalOfficeById(Long id) {
        rentalOfficeRepository.deleteById(id);
    }

    public RentalOffice findRentalOfficeById(Long id) {
        Optional<RentalOffice> optionalRentalOffice = rentalOfficeRepository.findById(id);
        if (optionalRentalOffice.isPresent()) {
            RentalOffice rentalOffice = optionalRentalOffice.get();
            return rentalOffice;
        } else {
            throw new NotFoundException("Rental office with id " + id + " does not exist.");
        }
    }

    public Long countRentalOffices() {
        return rentalOfficeRepository.count();
    }

    public RentalOffice findRentalOfficeByName(String searchString) {
        return rentalOfficeRepository.findRentalOfficeByName(searchString);
    }
}
