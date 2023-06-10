package com.carrentalservice.dto;

import javax.validation.constraints.NotEmpty;

public class EmployeeDto extends BaseEntityDto {

    @NotEmpty(message = "First name cannot be empty!")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty!")
    private String lastName;

    @NotEmpty(message = "Job position cannot be empty!")
    private String jobPosition;

    private BranchDto workingBranchDto;

    private ReturnCarDto returnCarDto;

    private RentalDto rentalDto;

    public EmployeeDto(Long id, String firstName, String lastName, String jobPosition, BranchDto workingBranchDto, ReturnCarDto returnCarDto, RentalDto rentalDto) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobPosition = jobPosition;
        this.workingBranchDto = workingBranchDto;
        this.returnCarDto = returnCarDto;
        this.rentalDto = rentalDto;
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

    public BranchDto getWorkingBranchDto() {
        return workingBranchDto;
    }

    public void setWorkingBranchDto(BranchDto workingBranchDto) {
        this.workingBranchDto = workingBranchDto;
    }

    public ReturnCarDto getReturnCarDto() {
        return returnCarDto;
    }

    public void setReturnCarDto(ReturnCarDto returnCarDto) {
        this.returnCarDto = returnCarDto;
    }

    public RentalDto getRentalDto() {
        return rentalDto;
    }

    public void setRentalDto(RentalDto rentalDto) {
        this.rentalDto = rentalDto;
    }

}
