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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    void findAllBookingTest_success() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);

        when(bookingService.findAllBookings()).thenReturn(List.of(bookingResponse));

        MvcResult mvcResult = mockMvc.perform(get(PATH)
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    void findAllBookingTest_unauthorized() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);

        when(bookingService.findAllBookings()).thenReturn(List.of(bookingResponse));

        MvcResult mvcResult = mockMvc.perform(get(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getErrorMessage());
        assertNotNull(response.getContentAsString());
    }

    @Test
    void findBookingByIdTest_success() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);

        when(bookingService.findBookingById(anyLong())).thenReturn(bookingResponse);

        MvcResult mvcResult = mockMvc.perform(get(PATH + "/{id}", 1L)
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithMockUser(value = "admin", username = "admin", password = "admin", roles = "ADMIN")
    void findBookingByIdTest_successWithMockUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(PATH + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser()
    void findBookingByIdTest_unauthorized() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(PATH + "/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getErrorMessage());
        assertNotNull(response.getContentAsString());
    }

    @Test
    void countBookingsTest_success() throws Exception {
        when(bookingService.countBookings()).thenReturn(1L);

        MvcResult mvcResult = mockMvc.perform(get(PATH + "/count")
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    void countBookingsTest_unauthorized() throws Exception {
        when(bookingService.countBookings()).thenReturn(1L);

        MvcResult mvcResult = mockMvc.perform(get(PATH + "/count")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getErrorMessage());
        assertNotNull(response.getContentAsString());
    }

    @Test
    void countByLoggedInUserTest_success() throws Exception {
        when(bookingService.countBookings()).thenReturn(1L);

        MvcResult mvcResult = mockMvc.perform(get(PATH + "/count-by-current-user")
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    void getCurrentDateTest_success() throws Exception {
        when(bookingService.countBookings()).thenReturn(1L);

        MvcResult mvcResult = mockMvc.perform(get(PATH + "/current-date")
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    void countByLoggedInUserTest_unauthorized() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(PATH + "/count-by-current-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getErrorMessage());
        assertNotNull(response.getContentAsString());
    }

    @Test
    void getCurrentDateTest_unauthorized() throws Exception {
        when(bookingService.countBookings()).thenReturn(1L);

        MvcResult mvcResult = mockMvc.perform(get(PATH + "/current-date")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getErrorMessage());
        assertNotNull(response.getContentAsString());
    }

    @Test
    void addBookingTest_success() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);

        String content = TestUtils.writeValueAsString(bookingResponse);

        when(bookingService.saveBooking(any(BookingRequest.class))).thenReturn(bookingResponse);

        MvcResult mvcResult = mockMvc.perform(post(PATH)
                        .with(csrf())
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    void addBookingTest_unauthorized() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);

        String content = TestUtils.writeValueAsString(bookingResponse);

        when(bookingService.saveBooking(any(BookingRequest.class))).thenReturn(bookingResponse);

        MvcResult mvcResult = mockMvc.perform(post(PATH)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isUnauthorized())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getErrorMessage());
        assertNotNull(response.getContentAsString());
    }

    @Test
    void addBookingTest_forbidden() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingRequest.json", BookingResponse.class);

        String content = TestUtils.writeValueAsString(bookingResponse);

        when(bookingService.saveBooking(any(BookingRequest.class))).thenReturn(bookingResponse);

        MvcResult mvcResult = mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isForbidden())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getErrorMessage());
        assertNotNull(response.getContentAsString());
    }

    @Test
    void updateBookingTest_success() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);

        String content = TestUtils.writeValueAsString(bookingResponse);

        when(bookingService.updateBooking(anyLong(), any(BookingRequest.class)))
                .thenReturn(bookingResponse);

        MvcResult mvcResult = mockMvc.perform(put(PATH + "/{id}", 1L)
                        .with(csrf())
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    void updateBookingTest_unauthorized() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);

        String content = TestUtils.writeValueAsString(bookingResponse);

        when(bookingService.updateBooking(anyLong(), any(BookingRequest.class)))
                .thenReturn(bookingResponse);

        MvcResult mvcResult = mockMvc.perform(put(PATH + "/edit/{id}", 1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isUnauthorized())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getErrorMessage());
        assertNotNull(response.getContentAsString());
    }

    @Test
    void updateBookingTest_forbidden() throws Exception {
        BookingResponse bookingResponse =
                TestUtils.getResourceAsJson("/data/BookingResponse.json", BookingResponse.class);

        String content = TestUtils.writeValueAsString(bookingResponse);

        when(bookingService.updateBooking(anyLong(), any(BookingRequest.class))).thenReturn(bookingResponse);

        MvcResult mvcResult = mockMvc.perform(put(PATH + "/edit/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isForbidden())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getErrorMessage());
        assertNotNull(response.getContentAsString());
    }

    @Test
    void deleteBookingByIdTest_forbidden() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete(PATH + "/{id}", 1L)
                        .with(user("admin").password("admin").roles("ADMIN")))
                .andExpect(status().isForbidden())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getErrorMessage());
        assertNotNull(response.getContentAsString());
    }

}
