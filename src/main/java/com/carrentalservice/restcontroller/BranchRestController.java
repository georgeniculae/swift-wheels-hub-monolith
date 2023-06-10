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
        BranchDto branchDto = branchTransformer.transformFromEntityToDto(branch);

        return ResponseEntity.ok(branchDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BranchDto> deleteBranchById(@PathVariable("id") Long id) {
        branchService.deleteBranchById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto) {
        Branch branch = branchTransformer.transformFromDtoToEntity(branchDto);
        Branch savedBranch = branchService.saveBranch(branch);
        BranchDto savedBranchDto = branchTransformer.transformFromEntityToDto(savedBranch);

        return ResponseEntity.ok(savedBranchDto);
    }

    @PutMapping
    public ResponseEntity<BranchDto> updateBranch(@RequestBody BranchDto branchDto) {
        Branch branch = branchTransformer.transformFromDtoToEntity(branchDto);
        Branch savedBranch = branchService.saveBranch(branch);
        BranchDto savedBranchDto = branchTransformer.transformFromEntityToDto(savedBranch);

        return ResponseEntity.ok(savedBranchDto);
    }

    @GetMapping
    public ResponseEntity<List<BranchDto>> listAllBranches() {
        List<Branch> allBranches = branchService.findAllBranches();
        List<BranchDto> allBranchesDto = new ArrayList<>();

        for (Branch branch : allBranches) {
            allBranchesDto.add(branchTransformer.transformFromEntityToDto(branch));
        }

        return ResponseEntity.ok(allBranchesDto);
    }
}
