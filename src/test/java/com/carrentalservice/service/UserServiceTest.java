package com.carrentalservice.service;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.entity.User;
import com.carrentalservice.mapper.CustomerMapper;
import com.carrentalservice.mapper.CustomerMapperImpl;
import com.carrentalservice.repository.UserRepository;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestUtils;
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
        CustomerDto customerDto = TestUtils.getResourceAsJson("/data/CustomerDto.json", CustomerDto.class);

        when(userRepository.save(any(User.class))).thenReturn(customer);

        assertDoesNotThrow(() -> userService.registerCustomer(customerDto));
        CustomerDto registerCustomerDto = userService.registerCustomer(customerDto);

        verify(userRepository, times(2)).save(argumentCaptor.capture());
        verify(customerMapper, times(2)).mapEntityToDto(any(Customer.class));
        verify(bCryptPasswordEncoder, times(2)).encode(any());

        AssertionUtils.assertCustomer(argumentCaptor.getValue(), registerCustomerDto);
    }

}
