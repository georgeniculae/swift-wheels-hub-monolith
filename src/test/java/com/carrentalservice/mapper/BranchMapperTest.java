package com.carrentalservice.mapper;

import com.carrentalservice.dto.BranchDto;
import com.carrentalservice.entity.Branch;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class BranchMapperTest {

    private final BranchMapper branchMapper = new BranchMapperImpl();

    @Test
    void mapEntityToDtoTest_success() {
        Branch branch = TestUtils.getResourceAsJson("/data/Branch.json", Branch.class);

        BranchDto branchDto = branchMapper.mapEntityToDto(branch);

        assertNotNull(branchDto);
        AssertionUtils.assertBranch(branch, branchDto);
    }

    @Test
    void mapDtoToEntityTest_success() {
        BranchDto branchDto = TestUtils.getResourceAsJson("/data/BranchDto.json", BranchDto.class);

        Branch branch = branchMapper.mapDtoToEntity(branchDto);

        assertNotNull(branch);
        AssertionUtils.assertBranch(branch, branchDto);
    }

}
