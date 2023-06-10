package com.carrentalservice.transformer;

import com.carrentalservice.dto.BranchDto;
import com.carrentalservice.entity.Branch;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BranchTransformer {

    public BranchDto transformFromEntityToDto(Branch branch) {
        BranchDto branchDTO = new BranchDto();
        BeanUtils.copyProperties(branch, branchDTO);

        return branchDTO;
    }

    public Branch transformFromDtoToEntity(BranchDto branchDTO) {
        Branch branch = new Branch();
        BeanUtils.copyProperties(branchDTO, branch);

        return branch;
    }
}
