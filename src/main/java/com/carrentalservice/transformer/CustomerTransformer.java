package com.carrentalservice.transformer;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.entity.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerTransformer {

    public Customer transformFromDTOToEntity(CustomerDto customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public CustomerDto transformFromEntityToDTO(Customer customer){
        CustomerDto customerDTO = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }
}
