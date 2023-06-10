package com.carrentalservice.dto;

import java.util.List;

public class BranchDto extends BaseEntityDto {

    private String name;
    private String address;
    private List<EmployeeDto> employeesDTO;
    private List<CarDto> carsDTO;
    private RentalOfficeDto rentalOfficeDTO;

    public BranchDto(Long id, String name, String address, List<EmployeeDto> employeesDTO, List<CarDto> carsDTO, RentalOfficeDto rentalOfficeDTO) {
        super(id);
        this.name = name;
        this.address = address;
        this.employeesDTO = employeesDTO;
        this.carsDTO = carsDTO;
        this.rentalOfficeDTO = rentalOfficeDTO;
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

    public List<EmployeeDto> getEmployeesDTO() {
        return employeesDTO;
    }

    public void setEmployees(List<EmployeeDto> employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    public List<CarDto> getCarsDTO() {
        return carsDTO;
    }

    public void setCarsDTO(List<CarDto> cars) {
        this.carsDTO = carsDTO;
    }

    public RentalOfficeDto getRentalOfficeDTO() {
        return rentalOfficeDTO;
    }

    public void setRentalOfficeDTO(RentalOfficeDto rentalOfficeDTO) {
        this.rentalOfficeDTO = rentalOfficeDTO;
    }
}
