package com.swiftwheelshub.restcontroler;

import com.swiftwheelshub.dto.RentalOfficeRequest;
import com.swiftwheelshub.dto.RentalOfficeResponse;
import com.swiftwheelshub.restcontroller.RentalOfficeRestController;
import com.swiftwheelshub.service.RentalOfficeService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RentalOfficeRestController.class)
@AutoConfigureMockMvc
@EnableWebMvc
class RentalOfficeRestControllerTest {

    private static final String PATH = "/api/rental-offices";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalOfficeService rentalOfficeService;

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void findAllRentalOfficesTest_success() throws Exception {
        RentalOfficeResponse rentalOfficeResponse =
                TestUtils.getResourceAsJson("/data/RentalOfficeResponse.json", RentalOfficeResponse.class);

        when(rentalOfficeService.findAllRentalOffices()).thenReturn(List.of(rentalOfficeResponse));

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void findAllRentalOfficesTest_unauthorized() throws Exception {
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
    void findRentalOfficeByIdTest_success() throws Exception {
        RentalOfficeResponse rentalOfficeRequest =
                TestUtils.getResourceAsJson("/data/RentalOfficeResponse.json", RentalOfficeResponse.class);

        when(rentalOfficeService.findRentalOfficeById(anyLong())).thenReturn(rentalOfficeRequest);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void findRentalOfficeByIdTest_unauthorized() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

//    @Test
//    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
//    void findRentalOfficesByFilterTest_success() throws Exception {
//        RentalOfficeResponse rentalOfficeResponse =
//                TestUtils.getResourceAsJson("/data/RentalOfficeResponse.json", RentalOfficeResponse.class);
//
//        when(rentalOfficeService.findRentalOfficeByFilter(anyString())).thenReturn(List.of(rentalOfficeResponse));
//
//        MockHttpServletResponse response = mockMvc.perform(get(PATH + "/filter/{filter}", "filter")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse();
//
//        assertNotNull(response.getContentAsString());
//    }
//
//    @Test
//    @WithAnonymousUser
//    void findRentalOfficesByNameTest_unauthorized() throws Exception {
//        MockHttpServletResponse response = mockMvc.perform(get(PATH + "/name/{name}", "name")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isUnauthorized())
//                .andReturn()
//                .getResponse();
//
//        assertNotNull(response.getContentAsString());
//    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void countRentalOfficesTest_success() throws Exception {
        when(rentalOfficeService.countRentalOffices()).thenReturn(1L);

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
    void countRentalOfficesTest_unauthorized() throws Exception {
        when(rentalOfficeService.countRentalOffices()).thenReturn(1L);

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
    void addRentalOfficeTest_success() throws Exception {
        RentalOfficeRequest rentalOfficeRequest =
                TestUtils.getResourceAsJson("/data/RentalOfficeRequest.json", RentalOfficeRequest.class);
        RentalOfficeResponse rentalOfficeResponse =
                TestUtils.getResourceAsJson("/data/RentalOfficeResponse.json", RentalOfficeResponse.class);

        String valueAsString = TestUtils.writeValueAsString(rentalOfficeRequest);

        when(rentalOfficeService.saveRentalOffice(any(RentalOfficeRequest.class))).thenReturn(rentalOfficeResponse);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(valueAsString))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void addRentalOfficeTest_unauthorized() throws Exception {
        RentalOfficeResponse rentalOfficeResponse =
                TestUtils.getResourceAsJson("/data/RentalOfficeRequest.json", RentalOfficeResponse.class);

        String valueAsString = TestUtils.writeValueAsString(rentalOfficeResponse);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(valueAsString))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void addRentalOfficeTest_forbidden() throws Exception {
        RentalOfficeResponse rentalOfficeResponse =
                TestUtils.getResourceAsJson("/data/RentalOfficeResponse.json", RentalOfficeResponse.class);

        String valueAsString = TestUtils.writeValueAsString(rentalOfficeResponse);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(valueAsString))
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void updateRentalOfficeTest_success() throws Exception {
        RentalOfficeRequest rentalOfficeRequest =
                TestUtils.getResourceAsJson("/data/RentalOfficeRequest.json", RentalOfficeRequest.class);
        RentalOfficeResponse rentalOfficeResponse =
                TestUtils.getResourceAsJson("/data/RentalOfficeResponse.json", RentalOfficeResponse.class);

        String valueAsString = TestUtils.writeValueAsString(rentalOfficeRequest);

        when(rentalOfficeService.saveRentalOffice(any(RentalOfficeRequest.class))).thenReturn(rentalOfficeResponse);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put(PATH + "/{id}", 1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(valueAsString))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void updateRentalOfficeTest_unauthorized() throws Exception {
        RentalOfficeResponse rentalOfficeRequest =
                TestUtils.getResourceAsJson("/data/RentalOfficeResponse.json", RentalOfficeResponse.class);

        String valueAsString = TestUtils.writeValueAsString(rentalOfficeRequest);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put(PATH + "/{id}", 1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(valueAsString))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void updateRentalOfficeTest_forbidden() throws Exception {
        RentalOfficeResponse rentalOfficeResponse =
                TestUtils.getResourceAsJson("/data/RentalOfficeResponse.json", RentalOfficeResponse.class);

        String valueAsString = TestUtils.writeValueAsString(rentalOfficeResponse);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put(PATH + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(valueAsString))
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void deleteRentalOfficeByIdTest_success() throws Exception {
        doNothing().when(rentalOfficeService).deleteRentalOfficeById(anyLong());

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "/{id}", 1L)
                        .with(csrf())
                        .with(user("admin").password("admin").roles("ADMIN")))
                .andExpect(status().isNoContent())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void deleteRentalOfficeByIdTest_unauthorized() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "/{id}", 1L)
                        .with(csrf()))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void deleteRentalOfficeByIdTest_forbidden() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "/{id}", 1L)
                        .with(user("admin").password("admin").roles("ADMIN")))
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

}
