package com.swiftwheelshub.mvccontroller;

import com.swiftwheelshub.dto.BookingResponse;
import com.swiftwheelshub.restcontroller.BookingRestController;
import com.swiftwheelshub.service.BookingService;
import com.swiftwheelshub.service.BranchService;
import com.swiftwheelshub.service.CarService;
import com.swiftwheelshub.service.EmployeeService;
import com.swiftwheelshub.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = BookingRestController.class)
@AutoConfigureMockMvc
@EnableWebMvc
class BookingMvcControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @MockBean
    private BranchService branchService;

    @MockBean
    private CarService carService;

    @MockBean
    private EmployeeService employeeService;

    @Test
    @WithMockUser(username = "admin", roles = "admin")
    void showBookingTest_success() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);

        when(bookingService.findAllBookings()).thenReturn(List.of(bookingResponse));
        when(bookingService.countBookings()).thenReturn(1L);
        when(bookingService.getSumOfAllBookingAmount()).thenReturn(BigDecimal.valueOf(500));

        MvcResult mvcResult = this.mockMvc.perform(get("/bookings"))
                .andDo(print())
                .andExpect(view().name("index"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
    }

}
