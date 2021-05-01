package com.sda.carrentalservice.restcontroller;

import com.sda.carrentalservice.dto.BranchDTO;
import com.sda.carrentalservice.entity.Branch;
import com.sda.carrentalservice.service.BranchService;
import com.sda.carrentalservice.transformer.BranchTransformer;
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
    public ResponseEntity<BranchDTO> findBranchById(@PathVariable("id") Long id) {
        Branch branch = branchService.findBranchById(id);
        BranchDTO branchDTO = branchTransformer.transformFromEntityToDTO(branch);
        return ResponseEntity.ok(branchDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BranchDTO> deleteBranchById(@PathVariable("id") Long id) {
        branchService.deleteBranchById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO) {
        Branch branch = branchTransformer.transformFromDTOToEntity(branchDTO);
        Branch savedBranch = branchService.saveBranch(branch);
        BranchDTO savedBranchDTO = branchTransformer.transformFromEntityToDTO(savedBranch);
        return ResponseEntity.ok(savedBranchDTO);
    }

    @PutMapping
    public ResponseEntity<BranchDTO> updateEmployee(@RequestBody BranchDTO branchDTO) {
        Branch branch = branchTransformer.transformFromDTOToEntity(branchDTO);
        Branch savedBranch = branchService.saveBranch(branch);
        BranchDTO savedBranchDTO = branchTransformer.transformFromEntityToDTO(savedBranch);
        return ResponseEntity.ok(savedBranchDTO);
    }

    @GetMapping
    public ResponseEntity<List<BranchDTO>> listAllBranches() {
        List<Branch> allBranches = branchService.findAllBranches();
        List<BranchDTO> allBranchesDTO = new ArrayList<>();

        for (Branch branch : allBranches) {
            allBranchesDTO.add(branchTransformer.transformFromEntityToDTO(branch));
        }
        return ResponseEntity.ok(allBranchesDTO);
    }
}
