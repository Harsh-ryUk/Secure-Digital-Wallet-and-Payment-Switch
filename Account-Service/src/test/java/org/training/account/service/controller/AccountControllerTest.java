package org.training.account.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.training.account.service.model.dto.response.Response;
import org.training.account.service.service.AccountService;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deposit_shouldReturnOk_whenAuthenticated() throws Exception {
        String accountNumber = "123456";
        BigDecimal amount = BigDecimal.valueOf(1000);
        Response response = Response.builder().message("Deposit successful").responseCode("200").build();

        when(accountService.deposit(eq(accountNumber), eq(amount))).thenReturn(response);

        mockMvc.perform(post("/accounts/deposit")
                .param("accountNumber", accountNumber)
                .param("amount", amount.toString())
                .with(jwt()))
                .andExpect(status().isOk());
    }

    @Test
    void withdraw_shouldReturnOk_whenAuthenticated() throws Exception {
        String accountNumber = "123456";
        BigDecimal amount = BigDecimal.valueOf(500);
        Response response = Response.builder().message("Withdrawal successful").responseCode("200").build();

        when(accountService.withdraw(eq(accountNumber), eq(amount))).thenReturn(response);

        mockMvc.perform(post("/accounts/withdraw")
                .param("accountNumber", accountNumber)
                .param("amount", amount.toString())
                .with(jwt()))
                .andExpect(status().isOk());
    }

    @Test
    void deposit_shouldReturnUnauthorized_whenNotAuthenticated() throws Exception {
        mockMvc.perform(post("/accounts/deposit")
                .param("accountNumber", "123456")
                .param("amount", "1000"))
                .andExpect(status().isUnauthorized());
    }
}
