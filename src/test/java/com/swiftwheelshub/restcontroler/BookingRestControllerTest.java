package com.swiftwheelshub.restcontroler;

import com.swiftwheelshub.dto.BookingRequest;
import com.swiftwheelshub.dto.BookingResponse;
import com.swiftwheelshub.restcontroller.BookingRestController;
import com.swiftwheelshub.service.BookingService;
import com.swiftwheelshub.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = BookingRestController.class)
@AutoConfigureMockMvc
@EnableWebMvc
class BookingRestControllerTest {

    private static final String PATH = "/api/bookings";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void findAllBookingTest_success() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);

        when(bookingService.findAllBookings()).thenReturn(List.of(bookingResponse));

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void findAllBookingTest_unauthorized() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);

        when(bookingService.findAllBookings()).thenReturn(List.of(bookingResponse));

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void findBookingByIdTest_success() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);

        when(bookingService.findBookingById(anyLong())).thenReturn(bookingResponse);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser()
    void findBookingByIdTest_unauthorized() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void countBookingsTest_success() throws Exception {
        when(bookingService.countBookings()).thenReturn(1L);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/count")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void countBookingsTest_unauthorized() throws Exception {
        when(bookingService.countBookings()).thenReturn(1L);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/count")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void countByLoggedInUserTest_success() throws Exception {
        when(bookingService.countBookings()).thenReturn(1L);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/count-by-current-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void countByLoggedInUserTest_unauthorized() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/count-by-current-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void getCurrentDateTest_success() throws Exception {
        when(bookingService.countBookings()).thenReturn(1L);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/current-date")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void getCurrentDateTest_unauthorized() throws Exception {
        when(bookingService.countBookings()).thenReturn(1L);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/current-date")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void addBookingTest_success() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);

        String content = TestUtils.writeValueAsString(bookingResponse);

        when(bookingService.saveBooking(any(BookingRequest.class))).thenReturn(bookingResponse);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post(PATH + "/new")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void addBookingTest_unauthorized() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);

        String content = TestUtils.writeValueAsString(bookingResponse);

        when(bookingService.saveBooking(any(BookingRequest.class)))
                .thenReturn(bookingResponse);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void addBookingTest_forbidden() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingRequest.json", BookingResponse.class);

        String content = TestUtils.writeValueAsString(bookingResponse);

        when(bookingService.saveBooking(any(BookingRequest.class))).thenReturn(bookingResponse);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

//    @Test
//    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
//    void closeBookingTest_success() throws Exception {
//        BookingResponse bookingResponse =
//                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);
//        BookingClosingDetails bookingClosingDetails =
//                TestUtils.getResourceAsJson("/data/CarForUpdate.json", BookingClosingDetails.class);
//
//        String content = TestUtils.writeValueAsString(bookingClosingDetails);
//
//        when(bookingService.closeBooking(any(HttpServletRequest.class), any(BookingClosingDetails.class)))
//                .thenReturn(bookingResponse);
//
//        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post(PATH + "/close-booking")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(content))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse();
//
//        assertNotNull(response.getContentAsString());
//    }
//
//    @Test
//    @WithAnonymousUser
//    void closeBookingTest_unauthorized() throws Exception {
//        BookingResponse bookingResponse =
//                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);
//        BookingClosingDetails bookingClosingDetails =
//                TestUtils.getResourceAsJson("/data/BookingClosingDetails.json", BookingClosingDetails.class);
//
//        String content = TestUtils.writeValueAsString(bookingClosingDetails);
//
//        when(bookingService.closeBooking(any(HttpServletRequest.class), any(BookingClosingDetails.class)))
//                .thenReturn(bookingResponse);
//
//        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post(PATH + "/close-booking")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(content))
//                .andExpect(status().isUnauthorized())
//                .andReturn()
//                .getResponse();
//
//        assertNotNull(response.getContentAsString());
//    }
//
//    @Test
//    @WithAnonymousUser
//    void closeBookingTest_forbidden() throws Exception {
//        BookingResponse bookingResponse =
//                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);
//        BookingClosingDetails bookingClosingDetails =
//                TestUtils.getResourceAsJson("/data/BookingClosingDetails.json", BookingClosingDetails.class);
//
//        String content = TestUtils.writeValueAsString(bookingClosingDetails);
//
//        when(bookingService.closeBooking(any(HttpServletRequest.class), any(BookingClosingDetails.class)))
//                .thenReturn(bookingResponse);
//
//        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post(PATH + "/close-booking")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(content))
//                .andExpect(status().isForbidden())
//                .andReturn()
//                .getResponse();
//
//        assertNotNull(response.getContentAsString());
//    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void updateBookingTest_success() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);

        String content = TestUtils.writeValueAsString(bookingResponse);

        when(bookingService.updateBooking(anyLong(), any(BookingRequest.class)))
                .thenReturn(bookingResponse);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put(PATH + "/{id}", 1L)
                        .with(csrf())
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void updateBookingTest_unauthorized() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);

        String content = TestUtils.writeValueAsString(bookingResponse);

        when(bookingService.updateBooking(anyLong(), any(BookingRequest.class)))
                .thenReturn(bookingResponse);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put(PATH + "/edit/{id}", 1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void updateBookingTest_forbidden() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);

        String content = TestUtils.writeValueAsString(bookingResponse);

        when(bookingService.updateBooking(anyLong(), any(BookingRequest.class)))
                .thenReturn(bookingResponse);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put(PATH + "/edit/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void deleteBookingByUsernameTest_success() throws Exception {
        doNothing().when(bookingService).deleteBookingByCustomerUsername(anyString());

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "/{id}", 1L)
                        .with(csrf()))
                .andExpect(status().isNoContent())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void deleteBookingByUnauthorizedTest_success() throws Exception {
        doNothing().when(bookingService).deleteBookingByCustomerUsername(anyString());

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "/{id}", 1L)
                        .with(csrf()))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void deleteBookingByIdTest_forbidden() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "/{id}", 1L)
                        .with(user("admin").password("admin").roles("ADMIN")))
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

}
