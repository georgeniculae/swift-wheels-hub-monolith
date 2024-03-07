package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.BranchRequest;
import com.swiftwheelshub.dto.BranchResponse;
import com.swiftwheelshub.entity.Branch;
import com.swiftwheelshub.util.AssertionUtils;
import com.swiftwheelshub.util.TestUtils;
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

        BranchResponse branchResponse = branchMapper.mapEntityToDto(branch);

        assertNotNull(branchResponse);
        AssertionUtils.assertBranchResponse(branch, branchResponse);
    }

    @Test
    void mapDtoToEntityTest_success() {
        BranchRequest branchRequest = TestUtils.getResourceAsJson("/data/BranchRequest.json", BranchRequest.class);

        Branch branch = branchMapper.mapDtoToEntity(branchRequest);

        assertNotNull(branch);
        AssertionUtils.assertBranchRequest(branch, branchRequest);
    }

}
