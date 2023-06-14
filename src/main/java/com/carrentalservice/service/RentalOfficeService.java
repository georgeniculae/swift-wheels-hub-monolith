package com.carrentalservice.service;

import com.carrentalservice.entity.RentalOffice;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.repository.RentalOfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalOfficeService {

    private final RentalOfficeRepository rentalOfficeRepository;

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
            return optionalRentalOffice.get();
        }

        throw new NotFoundException("Rental office with id " + id + " does not exist");
    }

    public RentalOffice updateRentalOffice(RentalOffice newRentalOffice) {
        RentalOffice existingRentalOffice = findRentalOfficeById(newRentalOffice.getId());
        newRentalOffice.setId(existingRentalOffice.getId());

        return saveRentalOffice(newRentalOffice);
    }

    public Long countRentalOffices() {
        return rentalOfficeRepository.count();
    }

    public RentalOffice findRentalOfficeByName(String searchString) {
        return rentalOfficeRepository.findRentalOfficeByName(searchString);
    }

}
