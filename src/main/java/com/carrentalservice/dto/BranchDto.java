package com.carrentalservice.dto;

import java.util.List;

public class BranchDto extends BaseEntityDto {

    private String name;
    private String address;
    private List<EmployeeDto> employees;
    private List<CarDto> cars;
    private RentalOfficeDto rentalOffice;

    public BranchDto(Long id, String name, String address, List<EmployeeDto> employees, List<CarDto> cars, RentalOfficeDto rentalOffice) {
        super(id);
        this.name = name;
        this.address = address;
        this.employees = employees;
        this.cars = cars;
        this.rentalOffice = rentalOffice;
    }

    public BranchDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }

    public List<CarDto> getCars() {
        return cars;
    }

    public void setCars(List<CarDto> cars) {
        this.cars = cars;
    }

    public RentalOfficeDto getRentalOffice() {
        return rentalOffice;
    }

    public void setRentalOffice(RentalOfficeDto rentalOffice) {
        this.rentalOffice = rentalOffice;
    }

}
