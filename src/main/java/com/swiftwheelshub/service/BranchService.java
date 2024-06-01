package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.BranchRequest;
import com.swiftwheelshub.dto.BranchResponse;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.RentalOffice;
import com.swiftwheelshub.exception.SwiftWheelsHubNotFoundException;
import com.swiftwheelshub.mapper.BranchMapper;
import com.swiftwheelshub.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final RentalOfficeService rentalOfficeService;
    private final BranchMapper branchMapper;

    public BranchResponse saveBranch(BranchRequest branchRequest) {
        Branch newBranch = branchMapper.mapDtoToEntity(branchRequest);

        newBranch.setRentalOffice(rentalOfficeService.findEntityById(branchRequest.getRentalOfficeDetails().getId()));
        Branch savedBranch = branchRepository.save(newBranch);

        return branchMapper.mapEntityToDto(savedBranch);
    }

    public List<BranchResponse> findAllBranches() {
        return branchRepository.findAll()
                .stream()
                .map(branchMapper::mapEntityToDto)
                .toList();
    }

    public BranchResponse findBranchById(Long id) {
        Branch branch = findEntityById(id);

        return branchMapper.mapEntityToDto(branch);
    }

    public Branch findEntityById(Long id) {
        return branchRepository.findById(id)
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Branch with id " + id + " does not exist"));
    }

    public Branch saveEntity(Branch branch) {
        return branchRepository.save(branch);
    }

    public BranchResponse updateBranch(Long id, BranchRequest updatedBranchRequest) {
        RentalOffice rentalOffice = rentalOfficeService.findEntityById(updatedBranchRequest.getRentalOfficeDetails().getId());

        Branch exitingBranch = findEntityById(id);
        exitingBranch.setName(updatedBranchRequest.getName());
        exitingBranch.setAddress(updatedBranchRequest.getAddress());
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

    public BranchResponse findBranchByFilter(String searchString) {
        return branchRepository.findByFilter(searchString)
                .map(branchMapper::mapEntityToDto)
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Branch with filter: " + searchString + " does not exist"));
    }

    private Long getId(Long id, Long updatedBookingId) {
        Long actualId = updatedBookingId;

        if (ObjectUtils.isNotEmpty(id)) {
            actualId = id;
        }

        return actualId;
    }

}
