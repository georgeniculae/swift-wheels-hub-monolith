package com.carrentalservice.dto;

import java.util.List;

public class BranchDto extends BaseEntityDto {

    private String name;
    private String address;
    private List<EmployeeDto> employeeDtoList;
    private List<CarDto> carDtoList;
    private RentalOfficeDto rentalOfficeDto;

    public BranchDto(Long id, String name, String address, List<EmployeeDto> employeeDtoList, List<CarDto> carDtoList, RentalOfficeDto rentalOfficeDto) {
        super(id);
        this.name = name;
        this.address = address;
        this.employeeDtoList = employeeDtoList;
        this.carDtoList = carDtoList;
        this.rentalOfficeDto = rentalOfficeDto;
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

    public List<EmployeeDto> getEmployeeDtoList() {
        return employeeDtoList;
    }

    public void setEmployeeDtoList(List<EmployeeDto> employeeDtoList) {
        this.employeeDtoList = employeeDtoList;
    }

    public List<CarDto> getCarDtoList() {
        return carDtoList;
    }

    public void setCarDtoList(List<CarDto> carDtoList) {
        this.carDtoList = carDtoList;
    }

    public RentalOfficeDto getRentalOfficeDto() {
        return rentalOfficeDto;
    }

    public void setRentalOfficeDto(RentalOfficeDto rentalOfficeDto) {
        this.rentalOfficeDto = rentalOfficeDto;
    }

}
