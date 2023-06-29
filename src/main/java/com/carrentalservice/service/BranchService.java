package com.carrentalservice.service;

import com.carrentalservice.dto.BranchDto;
import com.carrentalservice.entity.Branch;
import com.carrentalservice.entity.RentalOffice;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.BranchMapper;
import com.carrentalservice.repository.BranchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final RentalOfficeService rentalOfficeService;
    private final BranchMapper branchMapper;

    @Transactional
    public BranchDto saveBranch(BranchDto branchDto) {
        Branch newBranch = branchMapper.mapDtoToEntity(branchDto);

        newBranch.setRentalOffice(rentalOfficeService.findEntityById(branchDto.getRentalOffice().getId()));
        Branch savedBranch = branchRepository.save(newBranch);

        return branchMapper.mapEntityToDto(savedBranch);
    }

    public List<BranchDto> findAllBranches() {
        return branchRepository.findAll()
                .stream()
                .map(branchMapper::mapEntityToDto)
                .toList();
    }

    public BranchDto findBranchById(Long id) {
        Branch branch = findEntityById(id);

        return branchMapper.mapEntityToDto(branch);
    }

    public Branch findEntityById(Long id) {
        Optional<Branch> optionalBranch = branchRepository.findById(id);

        if (optionalBranch.isPresent()) {
            return optionalBranch.get();
        }

        throw new NotFoundException("Branch with id " + id + " does not exist");
    }

    public Branch saveEntity(Branch branch) {
        return branchRepository.save(branch);
    }

    public BranchDto updateBranch(BranchDto updatedBranchDto) {
        RentalOffice rentalOffice = rentalOfficeService.findEntityById(updatedBranchDto.getRentalOffice().getId());

        Branch exitingBranch = findEntityById(updatedBranchDto.getId());
        exitingBranch.setName(updatedBranchDto.getName());
        exitingBranch.setAddress(updatedBranchDto.getAddress());
        exitingBranch.setRentalOffice(rentalOffice);

        Branch savedBranch = saveEntity(exitingBranch);

        return branchMapper.mapEntityToDto(savedBranch);
    }

    public void deleteBranchById(Long id) {
        branchRepository.deleteById(id);
    }

    public Long countBranches() {
        return branchRepository.count();
    }

    public BranchDto findBranchByFilter(String searchString) {
        Optional<Branch> optionalBranch = branchRepository.findByFilter(searchString);

        if (optionalBranch.isPresent()) {
            return branchMapper.mapEntityToDto(optionalBranch.get());
        }

        throw new NotFoundException("Branch with filter: " + searchString + " does not exist");
    }

}
