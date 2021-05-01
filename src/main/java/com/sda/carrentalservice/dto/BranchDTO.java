package com.sda.carrentalservice.dto;

import java.util.List;

public class BranchDTO extends BaseEntityDTO {

    private String name;
    private String address;
    private List<EmployeeDTO> employeesDTO;
    private List<CarDTO> carsDTO;
    private RentalOfficeDTO rentalOfficeDTO;

    public BranchDTO(Long id, String name, String address, List<EmployeeDTO> employeesDTO, List<CarDTO> carsDTO, RentalOfficeDTO rentalOfficeDTO) {
        super(id);
        this.name = name;
        this.address = address;
        this.employeesDTO = employeesDTO;
        this.carsDTO = carsDTO;
        this.rentalOfficeDTO = rentalOfficeDTO;
    }

    public BranchDTO() {
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

    public List<EmployeeDTO> getEmployeesDTO() {
        return employeesDTO;
    }

    public void setEmployees(List<EmployeeDTO> employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    public List<CarDTO> getCarsDTO() {
        return carsDTO;
    }

    public void setCarsDTO(List<CarDTO> cars) {
        this.carsDTO = carsDTO;
    }

    public RentalOfficeDTO getRentalOfficeDTO() {
        return rentalOfficeDTO;
    }

    public void setRentalOfficeDTO(RentalOfficeDTO rentalOfficeDTO) {
        this.rentalOfficeDTO = rentalOfficeDTO;
    }
}
