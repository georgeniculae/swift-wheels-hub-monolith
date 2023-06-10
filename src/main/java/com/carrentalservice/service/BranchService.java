package com.carrentalservice.service;

import com.carrentalservice.entity.Branch;
import com.carrentalservice.entity.RentalOffice;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.repository.BranchRepository;
import com.carrentalservice.repository.RentalOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    private final BranchRepository branchRepository;
    private final RentalOfficeRepository rentalOfficeRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository, RentalOfficeRepository rentalOfficeRepository) {
        this.branchRepository = branchRepository;
        this.rentalOfficeRepository = rentalOfficeRepository;
    }

    public Branch saveBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    public List<Branch> findAllBranches() {
        return branchRepository.findAll();
    }

    public void deleteBranchById(Long id) {
        Branch branchById = findBranchById(id);

        RentalOffice rentalOffice = branchById.getRentalOffice();
        rentalOffice.getBranches().remove(branchById);
        rentalOfficeRepository.save(rentalOffice);

        branchRepository.deleteById(id);
    }

    public Branch findBranchById(Long id) {
        Optional<Branch> optionalBranch = branchRepository.findById(id);

        if (optionalBranch.isPresent()) {
            return optionalBranch.get();
        }

        throw new NotFoundException("Branch with id " + id + " does not exist.");
    }

    public Long countBranches() {
        return branchRepository.count();
    }

    public Branch findBranchByName(String searchString) {
        return branchRepository.findBranchByName(searchString);
    }

}
