package com.carrentalservice.mapper;

import com.carrentalservice.dto.BranchDto;
import com.carrentalservice.entity.Branch;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper {

    public BranchDto mapEntityToDto(Branch branch) {
        BranchDto branchDTO = new BranchDto();
        BeanUtils.copyProperties(branch, branchDTO);

        return branchDTO;
    }

    public Branch mapDtoToEntity(BranchDto branchDTO) {
        Branch branch = new Branch();
        BeanUtils.copyProperties(branchDTO, branch);

        return branch;
    }

}
