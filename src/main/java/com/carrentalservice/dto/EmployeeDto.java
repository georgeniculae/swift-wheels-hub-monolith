package com.carrentalservice.dto;

import javax.validation.constraints.NotEmpty;

public class EmployeeDto extends BaseEntityDto {

    @NotEmpty(message = "First name cannot be empty!")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty!")
    private String lastName;

    @NotEmpty(message = "Job position cannot be empty!")
    private String jobPosition;

    private BranchDto workingBranchDTO;
    private ReturnCarDto returnCarDTO;
    private RentalDto rentalDTO;

    public EmployeeDto(Long id, String firstName, String lastName, String jobPosition, BranchDto workingBranchDTO, ReturnCarDto returnCarDTO, RentalDto rentalDTO) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobPosition = jobPosition;
        this.workingBranchDTO = workingBranchDTO;
        this.returnCarDTO = returnCarDTO;
        this.rentalDTO = rentalDTO;
    }

    public EmployeeDto() {
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

    public BranchDto getWorkingBranchDTO() {
        return workingBranchDTO;
    }

    public void setWorkingBranchDTO(BranchDto workingBranchDTO) {
        this.workingBranchDTO = workingBranchDTO;
    }

    public ReturnCarDto getReturnCarDTO() {
        return returnCarDTO;
    }

    public void setReturnCarDTO(ReturnCarDto returnCarDTO) {
        this.returnCarDTO = returnCarDTO;
    }

    public RentalDto getRentalDTO() {
        return rentalDTO;
    }

    public void setRentalDTO(RentalDto rentalDTO) {
        this.rentalDTO = rentalDTO;
    }
}
