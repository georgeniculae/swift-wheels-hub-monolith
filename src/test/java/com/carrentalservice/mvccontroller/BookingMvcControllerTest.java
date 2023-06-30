package com.carrentalservice.mvccontroller;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.security.JwtAuthenticationFilter;
import com.carrentalservice.service.BookingService;
import com.carrentalservice.service.BranchService;
import com.carrentalservice.service.CarService;
import com.carrentalservice.service.EmployeeService;
import com.carrentalservice.util.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookingMvcController.class)
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

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void showBookingTest_success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/bookings")
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());
        String responseAsString = response.getContentAsString();
        assertNotNull(responseAsString);
    }

    @Test
    @WithMockUser(value = "admin", username = "admin", password = "admin", roles = "ADMIN")
    void showBookingTest_successWithMockUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/bookings")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());
        String responseAsString = response.getContentAsString();
        assertNotNull(responseAsString);
    }

    @Test
    void showRegistrationTest_success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/booking/registration")
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());
        String responseAsString = response.getContentAsString();
        assertNotNull(responseAsString);
    }

    @Test
    void addBookingFromIndexTest_success() throws Exception {
        BookingDto bookingDto = TestUtils.getResourceAsJson("/data/BookingDto.json", BookingDto.class);

        when(bookingService.saveBooking(any(BookingDto.class))).thenReturn(bookingDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/")
                        .with(csrf())
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookingDto)))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());
        String responseAsString = response.getContentAsString();
        assertNotNull(responseAsString);
    }

    @Test
    void addBookingFromIndexTest_unauthorized() throws Exception {
        BookingDto bookingDto = TestUtils.getResourceAsJson("/data/BookingDto.json", BookingDto.class);

        when(bookingService.saveBooking(any(BookingDto.class))).thenReturn(bookingDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/")
                        .with(csrf().useInvalidToken())
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookingDto)))
                .andExpect(status().isForbidden())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(403, response.getStatus());
        String responseAsString = response.getContentAsString();
        assertNotNull(responseAsString);
    }

    @Test
    void addBookingTest_success() throws Exception {
        BookingDto bookingDto = TestUtils.getResourceAsJson("/data/BookingDto.json", BookingDto.class);

        when(bookingService.saveBooking(any(BookingDto.class))).thenReturn(bookingDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/booking/add")
                        .with(csrf())
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookingDto)))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());
        String responseAsString = response.getContentAsString();
        assertNotNull(responseAsString);
    }

}
