package com.sda.carrentalservice.dto;

import javax.validation.constraints.NotEmpty;

public class EmployeeDTO extends BaseEntityDTO {

    @NotEmpty(message = "First name cannot be empty!")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty!")
    private String lastName;

    @NotEmpty(message = "Job position cannot be empty!")
    private String jobPosition;

    private BranchDTO workingBranchDTO;
    private ReturnCarDTO returnCarDTO;
    private RentalDTO rentalDTO;

    public EmployeeDTO(Long id, String firstName, String lastName, String jobPosition, BranchDTO workingBranchDTO, ReturnCarDTO returnCarDTO, RentalDTO rentalDTO) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobPosition = jobPosition;
        this.workingBranchDTO = workingBranchDTO;
        this.returnCarDTO = returnCarDTO;
        this.rentalDTO = rentalDTO;
    }

    public EmployeeDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public BranchDTO getWorkingBranchDTO() {
        return workingBranchDTO;
    }

    public void setWorkingBranchDTO(BranchDTO workingBranchDTO) {
        this.workingBranchDTO = workingBranchDTO;
    }

    public ReturnCarDTO getReturnCarDTO() {
        return returnCarDTO;
    }

    public void setReturnCarDTO(ReturnCarDTO returnCarDTO) {
        this.returnCarDTO = returnCarDTO;
    }

    public RentalDTO getRentalDTO() {
        return rentalDTO;
    }

    public void setRentalDTO(RentalDTO rentalDTO) {
        this.rentalDTO = rentalDTO;
    }
}
