package com.carrentalservice.service;

import com.carrentalservice.dto.BranchDto;
import com.carrentalservice.dto.RentalOfficeDto;
import com.carrentalservice.entity.Branch;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.BranchMapper;
import com.carrentalservice.mapper.BranchMapperImpl;
import com.carrentalservice.mapper.CarMapper;
import com.carrentalservice.mapper.CarMapperImpl;
import com.carrentalservice.repository.BranchRepository;
import com.carrentalservice.util.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BranchServiceTest {

    @InjectMocks
    private BranchService branchService;

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private RentalOfficeService rentalOfficeService;

    @Spy
    private CarMapper carMapper = new CarMapperImpl();

    @Spy
    private BranchMapper branchMapper = new BranchMapperImpl();

    @Test
    void findBranchByIdTest_success() {
        when(branchRepository.findById(anyLong())).thenReturn(Optional.of(TestData.createRentalBranch()));

        assertDoesNotThrow(() -> branchService.findBranchById(1L));
        BranchDto actualBranchDto = branchService.findBranchById(1L);

        assertNotNull(actualBranchDto);

        verify(branchMapper, times(2)).mapEntityToDto(any(Branch.class));
    }

    @Test
    void findBranchByIdTest_errorOnFindingById() {
        when(branchRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> branchService.findBranchById(1L));

        assertNotNull(notFoundException);
    }

    @Test
    void updateBranchTest_success() {
        Branch branch = TestData.createRentalBranch();
        BranchDto branchDto = TestData.createRentalBranchDto();

        when(branchRepository.findById(anyLong())).thenReturn(Optional.of(branch));
        when(branchRepository.save(branch)).thenReturn(branch);

        assertDoesNotThrow(() -> branchService.updateBranch(branchDto));
        BranchDto updatedBranchDto = branchService.updateBranch(branchDto);

        assertNotNull(updatedBranchDto);
    }

    @Test
    void deleteBranchByIdTest_success() {
        Branch branch = TestData.createRentalBranch();
        RentalOfficeDto rentalOfficeDto = TestData.createRentalOfficeDto();

        when(branchRepository.findById(anyLong())).thenReturn(Optional.of(branch));
        when(rentalOfficeService.saveRentalOffice(any(RentalOfficeDto.class))).thenReturn(rentalOfficeDto);

        assertDoesNotThrow(() -> branchService.deleteBranchById(1L));
    }

}
