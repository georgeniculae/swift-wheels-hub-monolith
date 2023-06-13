package com.carrentalservice.dto;

import jakarta.validation.constraints.NotEmpty;

public class EmployeeDto extends BaseEntityDto {

    @NotEmpty(message = "First name cannot be empty!")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty!")
    private String lastName;

    @NotEmpty(message = "Job position cannot be empty!")
    private String jobPosition;

    private BranchDto workingBranch;

    private ReturnCarDto returnCar;

    private RentalDto rental;

    public EmployeeDto(Long id, String firstName, String lastName, String jobPosition, BranchDto workingBranch, ReturnCarDto returnCar, RentalDto rental) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobPosition = jobPosition;
        this.workingBranch = workingBranch;
        this.returnCar = returnCar;
        this.rental = rental;
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

    public BranchDto getWorkingBranch() {
        return workingBranch;
    }

    public void setWorkingBranch(BranchDto workingBranch) {
        this.workingBranch = workingBranch;
    }

    public ReturnCarDto getReturnCar() {
        return returnCar;
    }

    public void setReturnCar(ReturnCarDto returnCar) {
        this.returnCar = returnCar;
    }

    public RentalDto getRental() {
        return rental;
    }

    public void setRental(RentalDto rental) {
        this.rental = rental;
    }

}
