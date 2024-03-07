package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.CustomerRequest;
import com.swiftwheelshub.dto.CustomerResponse;
import com.swiftwheelshub.entity.Customer;
import com.swiftwheelshub.exception.SwiftWheelsHubNotFoundException;
import com.swiftwheelshub.mapper.CustomerMapper;
import com.swiftwheelshub.mapper.CustomerMapperImpl;
import com.swiftwheelshub.repository.CustomerRepository;
import com.swiftwheelshub.util.AssertionUtils;
import com.swiftwheelshub.util.TestUtils;
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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        Customer customer = TestUtils.getResourceAsJson("/data/Customer.json", Customer.class);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        CustomerResponse actualCustomerResponse = customerService.findCustomerById(1L);

        assertNotNull(actualCustomerResponse);

        verify(customerMapper, times(2)).mapEntityToDto(any(Customer.class));
    }

    @Test
    void findCustomerByIdTest_errorOnFindingById() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        SwiftWheelsHubNotFoundException swiftWheelsHubNotFoundException =
                assertThrows(SwiftWheelsHubNotFoundException.class, () -> customerService.findCustomerById(1L));

        assertNotNull(swiftWheelsHubNotFoundException);
        assertEquals("Customer with id 1 does not exist", swiftWheelsHubNotFoundException.getMessage());
    }

    @Test
    void updateCustomerTest_success() {
        Customer customer = TestUtils.getResourceAsJson("/data/Customer.json", Customer.class);
        CustomerRequest customerRequest = TestUtils.getResourceAsJson("/data/CustomerRequest.json", CustomerRequest.class);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerResponse updatedCustomerResponse = customerService.updateCustomer(1L, customerRequest);

        AssertionUtils.assertCustomerResponse(customer, updatedCustomerResponse);
    }

    @Test
    void saveCustomerTest_success() {
        Customer customer = TestUtils.getResourceAsJson("/data/Customer.json", Customer.class);
        CustomerRequest customerRequest = TestUtils.getResourceAsJson("/data/CustomerRequest.json", CustomerRequest.class);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerResponse savedCustomerResponse = customerService.saveCustomer(customerRequest);
        AssertionUtils.assertCustomerResponse(customer, savedCustomerResponse);
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

        assertDoesNotThrow(() -> customerService.findLoggedInCustomer());
    }

    @Test
    void getLoggedInCustomerDtoTest_errorOnFindingByUsername() {
        Authentication authentication = mock(Authentication.class);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(customerRepository.findByUsername(null)).thenReturn(Optional.empty());

        SwiftWheelsHubNotFoundException swiftWheelsHubNotFoundException =
                assertThrows(SwiftWheelsHubNotFoundException.class, () -> customerService.findLoggedInCustomer());

        assertNotNull(swiftWheelsHubNotFoundException);
        assertEquals("Customer with username null does not exist", swiftWheelsHubNotFoundException.getMessage());
    }

    @Test
    void findCarByFilterTest_success() {
        Customer customer = TestUtils.getResourceAsJson("/data/Customer.json", Customer.class);

        when(customerRepository.findByFilter(anyString())).thenReturn(Optional.of(customer));

        CustomerResponse customerResponse = customerService.findCustomerByFilter("Test");

        AssertionUtils.assertCustomerResponse(customer, customerResponse);
    }

    @Test
    void findCarByFilterTest_errorOnFindingByFilter() {
        when(customerRepository.findByFilter(anyString())).thenReturn(Optional.empty());

        SwiftWheelsHubNotFoundException swiftWheelsHubNotFoundException =
                assertThrows(SwiftWheelsHubNotFoundException.class, () -> customerService.findCustomerByFilter("Test"));

        assertNotNull(swiftWheelsHubNotFoundException);
        assertEquals("Customer with filter: Test does not exist", swiftWheelsHubNotFoundException.getMessage());
    }

}
