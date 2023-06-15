package com.carrentalservice.service;

import com.carrentalservice.entity.Branch;
import com.carrentalservice.entity.RentalOffice;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.repository.BranchRepository;
import com.carrentalservice.util.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BranchServiceTest {

    @InjectMocks
    private BranchService branchService;

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private RentalOfficeService rentalOfficeService;

    @Test
    void findBranchByIdTest_success() {
        when(branchRepository.findById(anyLong())).thenReturn(Optional.of(TestData.createRentalBranch()));

        assertDoesNotThrow(() -> branchService.findBranchById(1L));
        Branch actualBranch = branchService.findBranchById(1L);

        assertNotNull(actualBranch);
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

        when(branchRepository.findById(anyLong())).thenReturn(Optional.of(branch));
        when(branchRepository.save(branch)).thenReturn(branch);

        assertDoesNotThrow(() -> branchService.updateBranch(branch));
        Branch updatedBranch = branchService.updateBranch(branch);

        assertNotNull(updatedBranch);
    }

    @Test
    void deleteBranchByIdTest_success() {
        Branch branch = TestData.createRentalBranch();
        RentalOffice rentalOffice = TestData.createRentalOffice();
        branch.setRentalOffice(rentalOffice);
        rentalOffice.setBranches(List.of(branch));

        when(branchRepository.findById(anyLong())).thenReturn(Optional.of(branch));
        when(rentalOfficeService.saveRentalOffice(any(RentalOffice.class))).thenReturn(rentalOffice);

        assertDoesNotThrow(() -> branchService.deleteBranchById(1L));
    }

}
