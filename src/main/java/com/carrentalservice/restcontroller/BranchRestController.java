package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.BranchDto;
import com.carrentalservice.service.BranchService;
import com.carrentalservice.transformer.BranchTransformer;
import com.carrentalservice.entity.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/branch")
public class BranchRestController {

    private final BranchService branchService;
    private final BranchTransformer branchTransformer;

    @Autowired
    public BranchRestController(BranchService branchService, BranchTransformer branchTransformer) {
        this.branchService = branchService;
        this.branchTransformer = branchTransformer;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BranchDto> findBranchById(@PathVariable("id") Long id) {
        Branch branch = branchService.findBranchById(id);
        BranchDto branchDTO = branchTransformer.transformFromEntityToDTO(branch);
        return ResponseEntity.ok(branchDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BranchDto> deleteBranchById(@PathVariable("id") Long id) {
        branchService.deleteBranchById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDTO) {
        Branch branch = branchTransformer.transformFromDTOToEntity(branchDTO);
        Branch savedBranch = branchService.saveBranch(branch);
        BranchDto savedBranchDTO = branchTransformer.transformFromEntityToDTO(savedBranch);
        return ResponseEntity.ok(savedBranchDTO);
    }

    @PutMapping
    public ResponseEntity<BranchDto> updateEmployee(@RequestBody BranchDto branchDTO) {
        Branch branch = branchTransformer.transformFromDTOToEntity(branchDTO);
        Branch savedBranch = branchService.saveBranch(branch);
        BranchDto savedBranchDTO = branchTransformer.transformFromEntityToDTO(savedBranch);
        return ResponseEntity.ok(savedBranchDTO);
    }

    @GetMapping
    public ResponseEntity<List<BranchDto>> listAllBranches() {
        List<Branch> allBranches = branchService.findAllBranches();
        List<BranchDto> allBranchesDTO = new ArrayList<>();

        for (Branch branch : allBranches) {
            allBranchesDTO.add(branchTransformer.transformFromEntityToDTO(branch));
        }
        return ResponseEntity.ok(allBranchesDTO);
    }
}
