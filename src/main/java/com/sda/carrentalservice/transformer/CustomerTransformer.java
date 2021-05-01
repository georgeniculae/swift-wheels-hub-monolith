package com.sda.carrentalservice.transformer;

import com.sda.carrentalservice.dto.CustomerDTO;
import com.sda.carrentalservice.entity.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerTransformer {

    public Customer transformFromDTOToEntity(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public CustomerDTO transformFromEntityToDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }
}
