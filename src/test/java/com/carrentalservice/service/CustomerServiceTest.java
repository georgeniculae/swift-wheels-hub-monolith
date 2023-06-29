package com.carrentalservice.service;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.CustomerMapper;
import com.carrentalservice.mapper.CustomerMapperImpl;
import com.carrentalservice.repository.CustomerRepository;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestData;
import com.carrentalservice.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    void findCustomerByIdTest_errorOnFindingById() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException notFoundException =
                assertThrows(NotFoundException.class, () -> customerService.findCustomerById(1L));

        assertNotNull(notFoundException);
        assertEquals("Customer with id 1 does not exist", notFoundException.getMessage());
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

    @Test
    void saveCustomerTest_success() {
        Customer customer = TestUtils.getResourceAsJson("/data/Customer.json", Customer.class);
        CustomerDto customerDto = TestUtils.getResourceAsJson("/data/CustomerDto.json", CustomerDto.class);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        assertDoesNotThrow(() -> customerService.saveCustomer(customerDto));
        CustomerDto savedCustomerDto = customerService.saveCustomer(customerDto);
        AssertionUtils.assertCustomer(customer, savedCustomerDto);
    }

    @Test
    void findAllCustomerTest_success() {
        Customer customer = TestUtils.getResourceAsJson("/data/Customer.json", Customer.class);

        when(customerRepository.findCustomersWithoutBaseUsers(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(List.of(customer));

        assertDoesNotThrow(() -> customerService.findAllCustomers());
    }

    @Test
    void getLoggedInCustomerDtoTest_success() {
        Customer customer = TestUtils.getResourceAsJson("/data/Customer.json", Customer.class);
        Authentication authentication = mock(Authentication.class);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(customerRepository.findByUsername(null)).thenReturn(Optional.of(customer));

        assertDoesNotThrow(() -> customerService.getLoggedInCustomerDto());
    }

    @Test
    void getLoggedInCustomerDtoTest_errorOnFindingByUsername() {
        Authentication authentication = mock(Authentication.class);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(customerRepository.findByUsername(null)).thenReturn(Optional.empty());

        NotFoundException notFoundException =
                assertThrows(NotFoundException.class, () -> customerService.getLoggedInCustomerDto());
        assertNotNull(notFoundException);
        assertEquals("Customer with username null does not exist", notFoundException.getMessage());
    }

    @Test
    void findCarByFilterTest_success() {
        Customer customer = TestUtils.getResourceAsJson("/data/Customer.json", Customer.class);

        when(customerRepository.findByFilter(anyString())).thenReturn(Optional.of(customer));

        assertDoesNotThrow(() -> customerService.findCustomerByFilter("Test"));
        CustomerDto customerDto = customerService.findCustomerByFilter("Test");

        AssertionUtils.assertCustomer(customer, customerDto);
    }

    @Test
    void findCarByFilterTest_errorOnFindingByFilter() {
        when(customerRepository.findByFilter(anyString())).thenReturn(Optional.empty());

        NotFoundException notFoundException =
                assertThrows(NotFoundException.class, () -> customerService.findCustomerByFilter("Test"));

        assertNotNull(notFoundException);
        assertEquals("Customer with filter: Test does not exist", notFoundException.getMessage());
    }

}
