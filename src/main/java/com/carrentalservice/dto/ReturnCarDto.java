package com.carrentalservice.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReturnCarDto extends BaseEntityDto {

    private EmployeeDto employeeDto;
    private Date dateOfReturn;
    private List<BookingDto> bookingDtoList = new ArrayList<>();
    private Double additionalPayment;
    private String comments;

    public ReturnCarDto(Long id, EmployeeDto employeeDto, Date dateOfReturn, List<BookingDto> bookingDtoList, Double additionalPayment, String comments) {
        super(id);
        this.employeeDto = employeeDto;
        this.dateOfReturn = dateOfReturn;
        this.bookingDtoList = bookingDtoList;
        this.additionalPayment = additionalPayment;
        this.comments = comments;
    }

    public ReturnCarDto() {
    }

    public EmployeeDto getEmployeeDTO() {
        return employeeDto;
    }

    public void setEmployeeDTO(EmployeeDto employeeDTO) {
        this.employeeDto = employeeDTO;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public List<BookingDto> getBookingListDTO() {
        return bookingDtoList;
    }

    public void setBookingListDTO(List<BookingDto> bookingListDTO) {
        this.bookingDtoList = bookingListDTO;
    }

    public Double getAdditionalPayment() {
        return additionalPayment;
    }

    public void setAdditionalPayment(Double additionalPayment) {
        this.additionalPayment = additionalPayment;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
