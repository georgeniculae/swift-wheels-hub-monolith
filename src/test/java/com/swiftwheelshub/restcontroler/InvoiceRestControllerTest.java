package com.swiftwheelshub.restcontroler;

import com.swiftwheelshub.dto.InvoiceResponse;
import com.swiftwheelshub.restcontroller.InvoiceRestController;
import com.swiftwheelshub.service.InvoiceService;
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = InvoiceRestController.class)
@AutoConfigureMockMvc
@EnableWebMvc
public class InvoiceRestControllerTest {

    private static final String PATH = "/invoices";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceService invoiceService;

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void findAllInvoicesTest_success() throws Exception {
        InvoiceResponse invoiceResponse =
                TestUtils.getResourceAsJson("/data/InvoiceResponse.json", InvoiceResponse.class);

        when(invoiceService.findAllInvoices()).thenReturn(List.of(invoiceResponse));

        MockHttpServletResponse response = mockMvc.perform(get(PATH)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response);
    }

    @Test
    @WithAnonymousUser
    void findAllInvoicesTest_unauthorized() throws Exception {
        InvoiceResponse invoiceResponse =
                TestUtils.getResourceAsJson("/data/InvoiceResponse.json", InvoiceResponse.class);

        when(invoiceService.findAllInvoices()).thenReturn(List.of(invoiceResponse));

        mockMvc.perform(get(PATH)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void findAllActiveInvoicesTest_success() throws Exception {
        InvoiceResponse invoiceResponse =
                TestUtils.getResourceAsJson("/data/InvoiceResponse.json", InvoiceResponse.class);

        when(invoiceService.findAllActiveInvoices()).thenReturn(List.of(invoiceResponse));

        MockHttpServletResponse response = mockMvc.perform(get(PATH + "/active")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void findAllActiveInvoicesTest_unauthorized() throws Exception {
        InvoiceResponse invoiceResponse =
                TestUtils.getResourceAsJson("/data/InvoiceResponse.json", InvoiceResponse.class);

        when(invoiceService.findAllActiveInvoices()).thenReturn(List.of(invoiceResponse));

        mockMvc.perform(get(PATH + "/active")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void findInvoiceByIdTest_success() throws Exception {
        InvoiceResponse invoiceResponse =
                TestUtils.getResourceAsJson("/data/InvoiceResponse.json", InvoiceResponse.class);

        when(invoiceService.findInvoiceById(anyLong())).thenReturn(invoiceResponse);

        MockHttpServletResponse response = mockMvc.perform(get(PATH + "/{id}", 1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response);
    }

    @Test
    @WithAnonymousUser
    void findInvoiceByIdTest_unauthorized() throws Exception {
        InvoiceResponse invoiceResponse =
                TestUtils.getResourceAsJson("/data/InvoiceResponse.json", InvoiceResponse.class);

        when(invoiceService.findInvoiceById(anyLong())).thenReturn(invoiceResponse);

        mockMvc.perform(get(PATH + "/{id}", 1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void findAllInvoicesByCustomerIdTest_success() throws Exception {
        InvoiceResponse invoiceResponse =
                TestUtils.getResourceAsJson("/data/InvoiceResponse.json", InvoiceResponse.class);

        when(invoiceService.findAllInvoicesByCustomerId(anyLong())).thenReturn(List.of(invoiceResponse));

        MockHttpServletResponse response = mockMvc.perform(get(PATH + "/by-customer/{customerUsername}", "user")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response);
    }

    @Test
    @WithAnonymousUser
    void findAllInvoicesByCustomerIdTest_unauthorized() throws Exception {
        InvoiceResponse invoiceResponse =
                TestUtils.getResourceAsJson("/data/InvoiceResponse.json", InvoiceResponse.class);

        when(invoiceService.findAllInvoicesByCustomerId(anyLong())).thenReturn(List.of(invoiceResponse));

        mockMvc.perform(get(PATH + "/by-customer/{customerUsername}", "user")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void countInvoicesTest_success() throws Exception {
        when(invoiceService.countInvoices()).thenReturn(1L);

        MockHttpServletResponse response = mockMvc.perform(get(PATH + "/count")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response);
    }

    @Test
    @WithAnonymousUser
    void countInvoicesTest_unauthorized() throws Exception {
        when(invoiceService.countInvoices()).thenReturn(1L);

        mockMvc.perform(get(PATH + "/count")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void countActiveInvoicesTest_success() throws Exception {
        when(invoiceService.countAllActiveInvoices()).thenReturn(1L);

        MockHttpServletResponse response = mockMvc.perform(get(PATH + "/active-count")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response);
    }

    @Test
    @WithAnonymousUser
    void countActiveInvoicesTest_unauthorized() throws Exception {
        when(invoiceService.countAllActiveInvoices()).thenReturn(1L);

        mockMvc.perform(get(PATH + "/active-count")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

}
