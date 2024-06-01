package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.CustomerRequest;
import com.swiftwheelshub.dto.CustomerResponse;
import com.swiftwheelshub.entity.Customer;
import com.swiftwheelshub.util.AssertionUtils;
import com.swiftwheelshub.util.TestUtils;
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

        CustomerResponse customerResponse = customerMapper.mapEntityToDto(customer);

        assertNotNull(customerResponse);
        AssertionUtils.assertCustomerResponse(customer, customerResponse);
    }

    @Test
    void mapDtoToEntityTest_success() {
        CustomerRequest customerRequest = TestUtils.getResourceAsJson("/data/CustomerRequest.json", CustomerRequest.class);

        Customer customer = customerMapper.mapDtoToEntity(customerRequest);

        assertNotNull(customerRequest);
        AssertionUtils.assertCustomerRequest(customer, customerRequest);
    }

}
