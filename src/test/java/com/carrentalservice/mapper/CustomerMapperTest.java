package com.carrentalservice.mapper;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.entity.Customer;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CustomerMapperTest {

    private final CustomerMapper customerMapper = new CustomerMapperImpl();

    @Test
    void mapEntityToDtoTest_success() {
        Customer customer = TestUtils.getResourceAsJson("/data/Customer.json", Customer.class);

        CustomerDto customerDto = customerMapper.mapEntityToDto(customer);

        assertNotNull(customerDto);
        AssertionUtils.assertCustomer(customer, customerDto);
    }

    @Test
    void mapDtoToEntityTest_success() {
        CustomerDto customerDto = TestUtils.getResourceAsJson("/data/CustomerDto.json", CustomerDto.class);

        Customer customer = customerMapper.mapDtoToEntity(customerDto);

        assertNotNull(customerDto);
        AssertionUtils.assertCustomer(customer, customerDto);
    }

}
