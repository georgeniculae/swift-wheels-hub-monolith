package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.BranchDto;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.RentalOffice;
import com.swiftwheelshub.exception.NotFoundException;
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

    public BranchDto saveBranch(BranchDto branchDto) {
        Branch newBranch = branchMapper.mapDtoToEntity(branchDto);

        newBranch.setRentalOffice(rentalOfficeService.findEntityById(branchDto.getRentalOfficeDetails().getId()));
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
        return branchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Branch with id " + id + " does not exist"));
    }

    public Branch saveEntity(Branch branch) {
        return branchRepository.save(branch);
    }

    public BranchDto updateBranch(Long id, BranchDto updatedBranchDto) {
        Long actualId = getId(id, updatedBranchDto.getId());

        RentalOffice rentalOffice = rentalOfficeService.findEntityById(updatedBranchDto.getRentalOfficeDetails().getId());

        Branch exitingBranch = findEntityById(actualId);
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
        return branchRepository.findByFilter(searchString)
                .map(branchMapper::mapEntityToDto)
                .orElseThrow(() -> new NotFoundException("Branch with filter: " + searchString + " does not exist"));
    }

    private Long getId(Long id, Long updatedBookingId) {
        Long actualId = updatedBookingId;

        if (ObjectUtils.isNotEmpty(id)) {
            actualId = id;
        }

        return actualId;
    }

}
