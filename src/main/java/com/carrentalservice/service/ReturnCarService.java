package com.carrentalservice.service;

import com.carrentalservice.entity.ReturnCar;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.repository.ReturnCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReturnCarService {

    private final ReturnCarRepository returnCarRepository;

    @Autowired
    public ReturnCarService(ReturnCarRepository returnCarRepository) {
        this.returnCarRepository = returnCarRepository;
    }

    public void saveReturnCar(ReturnCar returnCar) {
        returnCarRepository.save(returnCar);
    }

    public List<ReturnCar> findAllReturnCar() {
        return returnCarRepository.findAll();
    }

    public ReturnCar findReturnCarById(Long id) {
        Optional<ReturnCar> optionalReturnCar = returnCarRepository.findById(id);

        if (optionalReturnCar.isPresent()) {
            return optionalReturnCar.get();
        }

        throw new NotFoundException("Return car with id " + id + "does not exist.");
    }

    public void deleteReturnCarById(Long id) {
        this.findReturnCarById(id);

        returnCarRepository.deleteById(id);
    }

    public Long countReturnCar() {
        return returnCarRepository.count();
    }

    public ReturnCar findReturnCarByName(String searchString) {
        return returnCarRepository.findReturnCarByName(searchString);
    }

}
