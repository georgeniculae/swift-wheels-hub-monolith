package com.carrentalservice.restcontroler;

import com.carrentalservice.mapper.BookingMapper;
import com.carrentalservice.restcontroller.BookingRestController;
import com.carrentalservice.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookingRestController.class)
class BookingRestControllerTest {

    public static final String PATH = "/api/booking";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookingService bookingService;

    @MockBean
    private BookingMapper bookingMapper;

    @Test
    void findBookingByIdTest_success() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(PATH + "/{id}", 1L)
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
    void findBookingByIdTest_successWithMockUser() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(PATH + "/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());
        String responseAsString = response.getContentAsString();
        assertNotNull(responseAsString);
    }

    @Test
    @WithAnonymousUser()
    void findBookingByIdTest_notAuthorize() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(PATH + "/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertEquals("Unauthorized", response.getErrorMessage());
        assertEquals(401, status);
    }
}
