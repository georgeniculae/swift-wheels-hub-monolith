package com.swiftwheelshub.restcontroler;

import com.swiftwheelshub.dto.BranchRequest;
import com.swiftwheelshub.dto.BranchResponse;
import com.swiftwheelshub.restcontroller.BranchRestController;
import com.swiftwheelshub.service.BranchService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = BranchRestController.class)
@AutoConfigureMockMvc
@EnableWebMvc
class BranchRestControllerTest {

    private static final String PATH = "/api/branches";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchService branchService;

    @Test
    @WithMockUser(value = "admin", username = "admin", password = "admin", roles = "ADMIN")
    void findAllBranchesTest_success() throws Exception {
        BranchResponse branchResponse =
                TestUtils.getResourceAsJson("/data/BranchResponse.json", BranchResponse.class);

        when(branchService.findAllBranches()).thenReturn(List.of(branchResponse));

        MockHttpServletResponse response = mockMvc.perform(get(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void findAllBranchesTest_unauthorized() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithMockUser(value = "admin", username = "admin", password = "admin", roles = "ADMIN")
    void findBranchByIdTest_successWithMockUser() throws Exception {
        BranchResponse branchResponse =
                TestUtils.getResourceAsJson("/data/BranchResponse.json", BranchResponse.class);

        when(branchService.findBranchById(anyLong())).thenReturn(branchResponse);

        MockHttpServletResponse response = mockMvc.perform(get(PATH + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser()
    void findBranchByIdTest_unauthorized() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get(PATH + "/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

//    @Test
//    @WithMockUser(value = "admin", username = "admin", password = "admin", roles = "ADMIN")
//    void findBranchesByFilterTest_success() throws Exception {
//        BranchResponse branchResponse =
//                TestUtils.getResourceAsJson("/data/BranchResponse.json", BranchResponse.class);
//
//        when(branchService.findBranchesByFilter(anyString())).thenReturn(List.of(branchResponse));
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

//    @Test
//    @WithAnonymousUser
//    void findBranchesByFilterTest_unauthorized() throws Exception {
//        BranchResponse branchResponse =
//                TestUtils.getResourceAsJson("/data/BranchResponse.json", BranchResponse.class);
//
//        when(branchService.findBranchesByFilter(anyString())).thenReturn(List.of(branchResponse));
//
//        MockHttpServletResponse response = mockMvc.perform(get(PATH + "/filter/{filter}", "filter")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isUnauthorized())
//                .andReturn()
//                .getResponse();
//
//        assertNotNull(response.getContentAsString());
//    }

    @Test
    @WithMockUser(value = "admin", username = "admin", password = "admin", roles = "ADMIN")
    void countBranchesTest_success() throws Exception {
        when(branchService.countBranches()).thenReturn(1L);

        MockHttpServletResponse response = mockMvc.perform(get(PATH + "/count")
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void countBranchesTest_unauthorized() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get(PATH + "/count")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithMockUser(value = "admin", username = "admin", password = "admin", roles = "ADMIN")
    void addBranchTest_success() throws Exception {
        BranchResponse branchResponse =
                TestUtils.getResourceAsJson("/data/BranchResponse.json", BranchResponse.class);

        String content = TestUtils.writeValueAsString(branchResponse);

        when(branchService.saveBranch(any(BranchRequest.class))).thenReturn(branchResponse);

        MockHttpServletResponse response = mockMvc.perform(post(PATH)
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
    void addBranchTest_unauthorized() throws Exception {
        BranchResponse branchResponse =
                TestUtils.getResourceAsJson("/data/BranchResponse.json", BranchResponse.class);

        String content = TestUtils.writeValueAsString(branchResponse);

        MockHttpServletResponse response = mockMvc.perform(post(PATH)
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
    @WithMockUser(value = "admin", username = "admin", password = "admin", roles = "ADMIN")
    void addBranchTest_forbidden() throws Exception {
        BranchRequest branchRequest = TestUtils.getResourceAsJson("/data/BranchRequest.json", BranchRequest.class);
        String content = TestUtils.writeValueAsString(branchRequest);

        MockHttpServletResponse response = mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithMockUser(value = "admin", username = "admin", password = "admin", roles = "ADMIN")
    void updateBranchTest_success() throws Exception {
        BranchResponse branchRequest =
                TestUtils.getResourceAsJson("/data/BranchResponse.json", BranchResponse.class);

        String content = TestUtils.writeValueAsString(branchRequest);

        when(branchService.updateBranch(anyLong(), any(BranchRequest.class))).thenReturn(branchRequest);

        MockHttpServletResponse response = mockMvc.perform(put(PATH + "/{id}", 1L)
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
    void updateBranchTest_forbidden() throws Exception {
        BranchRequest branchRequest = TestUtils.getResourceAsJson("/data/BranchRequest.json", BranchRequest.class);
        String content = TestUtils.writeValueAsString(branchRequest);

        MockHttpServletResponse response = mockMvc.perform(put(PATH + "/{id}", 1L)
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithMockUser(value = "admin", username = "admin", password = "admin", roles = "ADMIN")
    void deleteBranchByIdTest_success() throws Exception {
        doNothing().when(branchService).deleteBranchById(anyLong());

        MockHttpServletResponse response = mockMvc.perform(delete(PATH + "/{id}", 1L)
                        .with(csrf()))
                .andExpect(status().isNoContent())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void deleteBranchByIdTest_forbidden() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(delete(PATH + "/{id}", 1L))
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse();

        assertNotNull(response.getContentAsString());
    }

}
