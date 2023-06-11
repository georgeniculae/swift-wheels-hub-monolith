package com.carrentalservice.restcontroller;

import com.carrentalservice.dto.BranchDto;
import com.carrentalservice.entity.Branch;
import com.carrentalservice.mapper.BranchMapper;
import com.carrentalservice.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        BranchDto branchDto = branchMapper.mapEntityToDto(branch);

        return ResponseEntity.ok(branchDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BranchDto> deleteBranchById(@PathVariable("id") Long id) {
        branchService.deleteBranchById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto) {
        Branch branch = branchMapper.mapDtoToEntity(branchDto);
        Branch savedBranch = branchService.saveBranch(branch);
        BranchDto savedBranchDto = branchMapper.mapEntityToDto(savedBranch);

        return ResponseEntity.ok(savedBranchDto);
    }

    @PutMapping
    public ResponseEntity<BranchDto> updateBranch(@RequestBody BranchDto branchDto) {
        Branch branch = branchMapper.mapDtoToEntity(branchDto);
        Branch updatedBranch = branchService.updateBranch(branch);
        BranchDto updatedBranchDto = branchMapper.mapEntityToDto(updatedBranch);

        return ResponseEntity.ok(updatedBranchDto);
    }

    @GetMapping
    public ResponseEntity<List<BranchDto>> listAllBranches() {
        List<BranchDto> branchDtoList = branchService.findAllBranches()
                .stream()
                .map(branchMapper::mapEntityToDto)
                .toList();

        return ResponseEntity.ok(branchDtoList);
    }

}
