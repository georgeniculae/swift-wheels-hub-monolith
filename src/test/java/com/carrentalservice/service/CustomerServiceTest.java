package com.carrentalservice.service;

import com.carrentalservice.entity.Customer;
import com.carrentalservice.repository.CustomerRepository;
import com.carrentalservice.util.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void findCustomerByIdTest_success() {
        Customer customer = TestData.createCustomer();

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        assertDoesNotThrow(() -> customerService.findCustomerById(1L));
        Customer actualCustomer = customerService.findCustomerById(1L);

        assertNotNull(actualCustomer);
    }

}
