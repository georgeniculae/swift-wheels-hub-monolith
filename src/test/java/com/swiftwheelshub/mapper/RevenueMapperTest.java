//package com.swiftwheelshub.mapper;
//
//import com.swiftwheelshub.dto.RevenueRequest;
//import com.swiftwheelshub.dto.RevenueResponse;
//import com.swiftwheelshub.entity.Revenue;
//import com.swiftwheelshub.util.AssertionUtils;
//import com.swiftwheelshub.util.TestUtils;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@ExtendWith(MockitoExtension.class)
//class RevenueMapperTest {
//
//    private final RevenueMapper rentalOfficeMapper = new RevenueMapperImpl();
//
//    @Test
//    void mapEntityToDtoTest_success() {
//        Revenue revenue = TestUtils.getResourceAsJson("/data/Revenue.json", Revenue.class);
//
//        RevenueResponse revenueResponse = rentalOfficeMapper.mapEntityToDto(revenue);
//
//        assertNotNull(revenueResponse);
//        AssertionUtils.assertRevenueResponse(revenue, revenueResponse);
//    }
//
//    @Test
//    void mapDtoToEntityTest_success() {
//        RevenueRequest revenueRequest = TestUtils.getResourceAsJson("/data/RevenueRequest.json", RevenueRequest.class);
//
//        Revenue revenue = rentalOfficeMapper.mapDtoToEntity(revenueRequest);
//
//        assertNotNull(revenue);
//        AssertionUtils.assertRevenueRequest(revenue, revenueRequest);
//    }
//
//}
