package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.BranchDto;
import com.carrentalservice.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/branch")
@CrossOrigin(origins = "*")
public class BranchRestController {

    private final BranchService branchService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<BranchDto> findBranchById(@PathVariable("id") Long id) {
        BranchDto branchDto = branchService.findBranchById(id);

        return ResponseEntity.ok(branchDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BranchDto> deleteBranchById(@PathVariable("id") Long id) {
        branchService.deleteBranchById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto) {
        BranchDto savedBranchDto = branchService.saveBranch(branchDto);

        return ResponseEntity.ok(savedBranchDto);
    }

    @PutMapping
    public ResponseEntity<BranchDto> updateBranch(@RequestBody BranchDto branchDto) {
        BranchDto updatedBranchDto = branchService.updateBranch(branchDto);

        return ResponseEntity.ok(updatedBranchDto);
    }

    @GetMapping
    public ResponseEntity<List<BranchDto>> listAllBranches() {
        List<BranchDto> branchDtoList = branchService.findAllBranches();

        return ResponseEntity.ok(branchDtoList);
    }

}
