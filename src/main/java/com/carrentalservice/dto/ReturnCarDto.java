package com.carrentalservice.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReturnCarDto extends BaseEntityDto {

    private EmployeeDto employeeDTO;
    private Date dateOfReturn;
    private List<BookingDto> bookingListDTO = new ArrayList<>();
    private Double additionalPayment;
    private String comments;

    public ReturnCarDto(Long id, EmployeeDto employeeDTO, Date dateOfReturn, List<BookingDto> bookingListDTO, Double additionalPayment, String comments) {
        super(id);
        this.employeeDTO = employeeDTO;
        this.dateOfReturn = dateOfReturn;
        this.bookingListDTO = bookingListDTO;
        this.additionalPayment = additionalPayment;
        this.comments = comments;
    }

    public ReturnCarDto() {
    }

    public EmployeeDto getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDto employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public List<BookingDto> getBookingListDTO() {
        return bookingListDTO;
    }

    public void setBookingListDTO(List<BookingDto> bookingListDTO) {
        this.bookingListDTO = bookingListDTO;
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
