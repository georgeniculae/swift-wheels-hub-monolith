package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.BranchRequest;
import com.swiftwheelshub.dto.BranchResponse;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.entity.RentalOffice;
import com.swiftwheelshub.exception.SwiftWheelsHubNotFoundException;
import com.swiftwheelshub.mapper.BranchMapper;
import com.swiftwheelshub.mapper.BranchMapperImpl;
import com.swiftwheelshub.repository.BranchRepository;
import com.swiftwheelshub.util.AssertionUtils;
import com.swiftwheelshub.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        Branch branch = TestUtils.getResourceAsJson("/data/Branch.json", Branch.class);

        when(branchRepository.findById(anyLong())).thenReturn(Optional.of(branch));

        BranchResponse actualBranchResponse = branchService.findBranchById(1L);

        assertNotNull(actualBranchResponse);

        verify(branchMapper).mapEntityToDto(any(Branch.class));
    }

    @Test
    void findBranchByIdTest_errorOnFindingById() {
        when(branchRepository.findById(anyLong())).thenReturn(Optional.empty());

        SwiftWheelsHubNotFoundException swiftWheelsHubNotFoundException = assertThrows(SwiftWheelsHubNotFoundException.class, () -> branchService.findBranchById(1L));

        assertNotNull(swiftWheelsHubNotFoundException);
    }

    @Test
    void updateBranchTest_success() {
        Branch branch = TestUtils.getResourceAsJson("/data/Branch.json", Branch.class);
        BranchRequest branchRequest = TestUtils.getResourceAsJson("/data/BranchRequest.json", BranchRequest.class);
        RentalOffice rentalOffice = TestUtils.getResourceAsJson("/data/RentalOffice.json", RentalOffice.class);

        when(rentalOfficeService.findEntityById(anyLong())).thenReturn(rentalOffice);
        when(branchRepository.findById(anyLong())).thenReturn(Optional.of(branch));
        when(branchRepository.save(branch)).thenReturn(branch);

        BranchResponse updatedBranchRequest = branchService.updateBranch(1L, branchRequest);

        assertNotNull(updatedBranchRequest);
    }

    @Test
    void saveBranchTest_success() {
        Branch branch = TestUtils.getResourceAsJson("/data/Branch.json", Branch.class);
        BranchRequest branchRequest = TestUtils.getResourceAsJson("/data/BranchRequest.json", BranchRequest.class);
        RentalOffice rentalOffice = TestUtils.getResourceAsJson("/data/RentalOffice.json", RentalOffice.class);

        when(rentalOfficeService.findEntityById(anyLong())).thenReturn(rentalOffice);
        when(branchRepository.save(any(Branch.class))).thenReturn(branch);

        BranchResponse savedBranchResponse = branchService.saveBranch(branchRequest);

        assertNotNull(savedBranchResponse);
    }

    @Test
    void findAllBranchesTest_success() {
        Branch branch = TestUtils.getResourceAsJson("/data/Branch.json", Branch.class);

        when(branchRepository.findAll()).thenReturn(List.of(branch));

        List<BranchResponse> branchResponses = branchService.findAllBranches();

        AssertionUtils.assertBranchResponse(branch, branchResponses.getFirst());
    }

    @Test
    void findBranchByFilterTest_success() {
        Branch branch = TestUtils.getResourceAsJson("/data/Branch.json", Branch.class);

        when(branchRepository.findByFilter(anyString())).thenReturn(Optional.of(branch));

        BranchResponse branchResponse = branchService.findBranchByFilter("Test");

        AssertionUtils.assertBranchResponse(branch, branchResponse);
    }

    @Test
    void findBranchByFilterTest_errorOnFindingByFilter() {
        when(branchRepository.findByFilter(anyString())).thenReturn(Optional.empty());

        SwiftWheelsHubNotFoundException swiftWheelsHubNotFoundException =
                assertThrows(SwiftWheelsHubNotFoundException.class, () -> branchService.findBranchByFilter("Test"));

        assertNotNull(swiftWheelsHubNotFoundException);
        assertEquals("Branch with filter: Test does not exist", swiftWheelsHubNotFoundException.getMessage());
    }

}
