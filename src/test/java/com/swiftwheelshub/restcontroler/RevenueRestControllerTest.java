package com.swiftwheelshub.restcontroler;

import com.swiftwheelshub.dto.RevenueResponse;
import com.swiftwheelshub.restcontroller.RevenueRestController;
import com.swiftwheelshub.service.RevenueService;
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

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RevenueRestController.class)
@AutoConfigureMockMvc
@EnableWebMvc
public class RevenueRestControllerTest {

    private static final String PATH = "/api/revenues";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RevenueService revenueService;

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void findAllRevenuesTest_success() throws Exception {
        RevenueResponse revenueResponse =
                TestUtils.getResourceAsJson("/data/RevenueResponse.json", RevenueResponse.class);

        when(revenueService.findAllRevenues()).thenReturn(List.of(revenueResponse));

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
    void findAllRevenuesTest_unauthorized() throws Exception {
        RevenueResponse revenueResponse =
                TestUtils.getResourceAsJson("/data/RevenueResponse.json", RevenueResponse.class);

        when(revenueService.findAllRevenues()).thenReturn(List.of(revenueResponse));

        mockMvc.perform(get(PATH)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void findAllInvoicesTest_success() throws Exception {
        when(revenueService.getTotalAmount()).thenReturn(BigDecimal.valueOf(550));

        MockHttpServletResponse response = mockMvc.perform(get(PATH + "/total")
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
        when(revenueService.getTotalAmount()).thenReturn(BigDecimal.valueOf(550.0));

        mockMvc.perform(get(PATH + "/total")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

//    @Test
//    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
//    void findRevenuesByDateTest_success() throws Exception {
//        RevenueResponse revenueResponse =
//                TestUtils.getResourceAsJson("/data/RevenueResponse.json", RevenueResponse.class);
//
//        when(revenueService.findRevenuesByDate(any(LocalDate.class))).thenReturn(List.of(revenueResponse));
//
//        MockHttpServletResponse response = mockMvc.perform(get(PATH + "/{date}", "2050-02-20")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse();
//
//        assertNotNull(response);
//    }
//
//    @Test
//    @WithAnonymousUser
//    void findRevenuesByDateTest_unauthorized() throws Exception {
//        RevenueResponse revenueResponse =
//                TestUtils.getResourceAsJson("/data/RevenueResponse.json", RevenueResponse.class);
//
//        when(revenueService.findRevenuesByDate(any(LocalDate.class))).thenReturn(List.of(revenueResponse));
//
//        mockMvc.perform(get(PATH + "/{date}", "2050-02-20")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isUnauthorized())
//                .andReturn()
//                .getResponse();
//    }

}
