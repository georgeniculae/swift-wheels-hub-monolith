package com.sda.carrentalservice.transformer;

import com.sda.carrentalservice.dto.BranchDTO;
import com.sda.carrentalservice.entity.Branch;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BranchTransformer {

    public BranchDTO transformFromEntityToDTO(Branch branch) {
        BranchDTO branchDTO = new BranchDTO();
        BeanUtils.copyProperties(branch, branchDTO);
        return branchDTO;
    }

    public Branch transformFromDTOToEntity(BranchDTO branchDTO) {
        Branch branch = new Branch();
        BeanUtils.copyProperties(branchDTO, branch);
        return branch;
    }
}
