package com.sda.carrentalservice.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReturnCarDTO extends BaseEntityDTO {

    private EmployeeDTO employeeDTO;
    private Date dateOfReturn;
    private List<BookingDTO> bookingListDTO = new ArrayList<>();
    private Double additionalPayment;
    private String comments;

    public ReturnCarDTO(Long id, EmployeeDTO employeeDTO, Date dateOfReturn, List<BookingDTO> bookingListDTO, Double additionalPayment, String comments) {
        super(id);
        this.employeeDTO = employeeDTO;
        this.dateOfReturn = dateOfReturn;
        this.bookingListDTO = bookingListDTO;
        this.additionalPayment = additionalPayment;
        this.comments = comments;
    }

    public ReturnCarDTO() {
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public List<BookingDTO> getBookingListDTO() {
        return bookingListDTO;
    }

    public void setBookingListDTO(List<BookingDTO> bookingListDTO) {
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
