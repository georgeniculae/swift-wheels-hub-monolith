package com.carrentalservice.service;

import com.carrentalservice.dto.BranchDto;
import com.carrentalservice.entity.Branch;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.BranchMapper;
import com.carrentalservice.mapper.BranchMapperImpl;
import com.carrentalservice.repository.BranchRepository;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestData;
import com.carrentalservice.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
    void saveBranchTest_success() {
        Branch branch = TestUtils.getResourceAsJson("/data/Branch.json", Branch.class);
        BranchDto branchDto = TestUtils.getResourceAsJson("/data/BranchDto.json", BranchDto.class);

        when(branchRepository.save(any(Branch.class))).thenReturn(branch);

        assertDoesNotThrow(() -> branchService.saveBranch(branchDto));
        BranchDto savedBranchDto = branchService.saveBranch(branchDto);

        assertThat(savedBranchDto).usingRecursiveAssertion().isEqualTo(branchDto);
    }

    @Test
    void findAllBranchesTest_success() {
        Branch branch = TestUtils.getResourceAsJson("/data/Branch.json", Branch.class);

        when(branchRepository.findAll()).thenReturn(List.of(branch));

        assertDoesNotThrow(() -> branchService.findAllBranches());
        List<BranchDto> branchDtoList = branchService.findAllBranches();

        AssertionUtils.assertBranch(branch, branchDtoList.get(0));
    }

}
