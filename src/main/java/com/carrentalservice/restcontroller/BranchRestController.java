package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.BranchDto;
import com.carrentalservice.service.BranchService;
import com.carrentalservice.mapper.BranchMapper;
import com.carrentalservice.entity.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/branch")
@CrossOrigin(origins = "*")
public class BranchRestController {

    private final BranchService branchService;
    private final BranchMapper branchMapper;

    @Autowired
    public BranchRestController(BranchService branchService, BranchMapper branchMapper) {
        this.branchService = branchService;
        this.branchMapper = branchMapper;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BranchDto> findBranchById(@PathVariable("id") Long id) {
        Branch branch = branchService.findBranchById(id);
        BranchDto branchDto = branchMapper.mapFromEntityToDto(branch);

        return ResponseEntity.ok(branchDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BranchDto> deleteBranchById(@PathVariable("id") Long id) {
        branchService.deleteBranchById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto) {
        Branch branch = branchMapper.mapFromDtoToEntity(branchDto);
        Branch savedBranch = branchService.saveBranch(branch);
        BranchDto savedBranchDto = branchMapper.mapFromEntityToDto(savedBranch);

        return ResponseEntity.ok(savedBranchDto);
    }

    @PutMapping
    public ResponseEntity<BranchDto> updateBranch(@RequestBody BranchDto branchDto) {
        Branch branch = branchMapper.mapFromDtoToEntity(branchDto);
        Branch savedBranch = branchService.updateBranch(branch);
        BranchDto savedBranchDto = branchMapper.mapFromEntityToDto(savedBranch);

        return ResponseEntity.ok(savedBranchDto);
    }

    @GetMapping
    public ResponseEntity<List<BranchDto>> listAllBranches() {
        List<Branch> allBranches = branchService.findAllBranches();
        List<BranchDto> allBranchesDto = new ArrayList<>();

        for (Branch branch : allBranches) {
            allBranchesDto.add(branchMapper.mapFromEntityToDto(branch));
        }

        return ResponseEntity.ok(allBranchesDto);
    }

}
