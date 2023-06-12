package com.carrentalservice.service;

import com.carrentalservice.entity.Branch;
import com.carrentalservice.entity.RentalOffice;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    private final BranchRepository branchRepository;
    private final RentalOfficeService rentalOfficeService;

    @Autowired
    public BranchService(BranchRepository branchRepository, RentalOfficeService rentalOfficeService) {
        this.branchRepository = branchRepository;
        this.rentalOfficeService = rentalOfficeService;
    }

    public Branch saveBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    public List<Branch> findAllBranches() {
        return branchRepository.findAll();
    }

    public Branch findBranchById(Long id) {
        Optional<Branch> optionalBranch = branchRepository.findById(id);

        if (optionalBranch.isPresent()) {
            return optionalBranch.get();
        }

        throw new NotFoundException("Branch with id " + id + " does not exist.");
    }

    public Branch updateBranch(Branch newBranch) {
        Branch exitingBranch = findBranchById(newBranch.getId());
        newBranch.setId(exitingBranch.getId());

        return saveBranch(newBranch);
    }

    public void deleteBranchById(Long id) {
        Branch existingBranch = findBranchById(id);

        RentalOffice rentalOffice = existingBranch.getRentalOffice();
        List<Branch> allBranches = new ArrayList<>(rentalOffice.getBranches());
        allBranches.remove(existingBranch);
        rentalOffice.setBranches(allBranches);
        rentalOfficeService.saveRentalOffice(rentalOffice);

        branchRepository.deleteById(id);
    }

    public Long countBranches() {
        return branchRepository.count();
    }

    public Branch findBranchByName(String searchString) {
        return branchRepository.findBranchByName(searchString);
    }

}
