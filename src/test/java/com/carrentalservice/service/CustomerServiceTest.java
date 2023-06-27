package com.carrentalservice.service;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.mapper.CustomerMapper;
import com.carrentalservice.mapper.CustomerMapperImpl;
import com.carrentalservice.repository.CustomerRepository;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Spy
    private CustomerMapper customerMapper = new CustomerMapperImpl();

    @Test
    void findCustomerByIdTest_success() {
        Customer customer = TestData.createCustomer();

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        assertDoesNotThrow(() -> customerService.findCustomerById(1L));
        CustomerDto actualCustomerDto = customerService.findCustomerById(1L);

        assertNotNull(actualCustomerDto);

        verify(customerMapper, times(2)).mapEntityToDto(any(Customer.class));
    }

    @Test
    void updateCustomerTest_success() {
        Customer customer = TestData.createCustomer();
        CustomerDto customerDto = TestData.createCustomerDto();

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        assertDoesNotThrow(() -> customerService.updateCustomer(customerDto));
        CustomerDto updatedCustomerDto = customerService.updateCustomer(customerDto);

        AssertionUtils.assertCustomer(customer, updatedCustomerDto);
    }

}
