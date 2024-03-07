package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.CustomerRequest;
import com.swiftwheelshub.dto.CustomerResponse;
import com.swiftwheelshub.entity.Customer;
import com.swiftwheelshub.entity.User;
import com.swiftwheelshub.mapper.CustomerMapper;
import com.swiftwheelshub.mapper.CustomerMapperImpl;
import com.swiftwheelshub.repository.UserRepository;
import com.swiftwheelshub.util.AssertionUtils;
import com.swiftwheelshub.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Spy
    private CustomerMapper customerMapper = new CustomerMapperImpl();

    @Captor
    private ArgumentCaptor<Customer> argumentCaptor = ArgumentCaptor.forClass(Customer.class);

    @Test
    void registerCustomerTest_success() {
        Customer customer = TestUtils.getResourceAsJson("/data/Customer.json", Customer.class);
        CustomerRequest customerRequest = TestUtils.getResourceAsJson("/data/CustomerRequest.json", CustomerRequest.class);

        when(userRepository.save(any(User.class))).thenReturn(customer);

        assertDoesNotThrow(() -> userService.registerCustomer(customerRequest));
        CustomerResponse registerCustomerResponse = userService.registerCustomer(customerRequest);

        verify(userRepository, times(2)).save(argumentCaptor.capture());
        verify(customerMapper, times(2)).mapEntityToDto(any(Customer.class));
        verify(bCryptPasswordEncoder, times(2)).encode(any());

        AssertionUtils.assertCustomerResponse(argumentCaptor.getValue(), registerCustomerResponse);
    }

}
