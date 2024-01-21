package com.swiftwheelshub.restcontroller;

import com.swiftwheelshub.dto.BranchDto;
import com.swiftwheelshub.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(path = "/count")
    public ResponseEntity<Long> countBranches() {
        Long numberOfBranches = branchService.countBranches();

        return ResponseEntity.ok(numberOfBranches);
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

    @PutMapping(path = "/{id}")
    public ResponseEntity<BranchDto> updateBranch(@PathVariable("id") Long id, @RequestBody BranchDto branchDto) {
        BranchDto updatedBranchDto = branchService.updateBranch(id, branchDto);

        return ResponseEntity.ok(updatedBranchDto);
    }

    @GetMapping
    public ResponseEntity<List<BranchDto>> listAllBranches() {
        List<BranchDto> branchDtoList = branchService.findAllBranches();

        return ResponseEntity.ok(branchDtoList);
    }

}
