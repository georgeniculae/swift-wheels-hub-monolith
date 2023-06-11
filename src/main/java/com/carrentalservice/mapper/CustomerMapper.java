package com.carrentalservice.mapper;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.entity.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer mapDtoToEntity(CustomerDto customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);

        return customer;
    }

    public CustomerDto mapEntityToDto(Customer customer){
        CustomerDto customerDTO = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDTO);

        return customerDTO;
    }

}
