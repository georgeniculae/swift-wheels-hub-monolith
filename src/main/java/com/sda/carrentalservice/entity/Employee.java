package com.sda.carrentalservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Employee extends BaseEntity {

    @NotEmpty(message = "First name cannot be empty!")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty!")
    private String lastName;

    @NotEmpty(message = "Job position cannot be empty!")
    private String jobPosition;

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch workingBranch;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ReturnCar returnCar;

    @OneToOne(mappedBy = "employeeOfRental", fetch = FetchType.LAZY)
    private Rental rental;

    public Employee(String firstName, String lastName, String jobPosition, Branch workingBranch, ReturnCar returnCar, Rental rental) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobPosition = jobPosition;
        this.workingBranch = workingBranch;
        this.returnCar = returnCar;
        this.rental = rental;
    }

    public Employee() {
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

    public Branch getWorkingBranch() {
        return workingBranch;
    }

    public void setWorkingBranch(Branch branch) {
        this.workingBranch = branch;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public ReturnCar getReturnCar() {
        return returnCar;
    }

    public void setReturnCar(ReturnCar returnCar) {
        this.returnCar = returnCar;
    }
}
