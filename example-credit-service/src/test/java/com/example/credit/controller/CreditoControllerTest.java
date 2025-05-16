package com.example.credit.controller;

import com.example.credit.entity.Credito;
import com.example.credit.repository.CreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
@AutoConfigureTestDatabase
class CreditoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CreditoRepository creditoRepository;

    @BeforeEach
    void setup() {
        Credito credito = new Credito();
        credito.setNumeroCredito("123456");
        credito.setNumeroNfse("7891011");
        credito.setDataConstituicao(LocalDate.of(2024, 2, 25));
        credito.setValorIssqn(new BigDecimal("1500.75"));
        credito.setTipoCredito("ISSQN");
        credito.setSimplesNacional(true);
        credito.setAliquota(new BigDecimal("5.0"));
        credito.setValorFaturado(new BigDecimal("30000.00"));
        credito.setValorDeducao(new BigDecimal("5000.00"));
        credito.setBaseCalculo(new BigDecimal("25000.00"));
        creditoRepository.save(credito);
    }

    @Test
    void shouldGetByNumeroNfseListByNumeroNfse() throws Exception {
        String numeroNfse = "7891011";

        mockMvc.perform(get("/api/creditos/" + numeroNfse))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].numeroNfse").value(numeroNfse));
    }

    @Test
    void shouldGetByNumeroNfseByNumeroCredito() throws Exception {
        String numeroCredito = "123456";

        mockMvc.perform(get("/api/creditos/credito/" + numeroCredito))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCredito").value(numeroCredito));
    }

    @Test
    void shouldReturn404WhenGetByNumeroNfseByNumeroCredito() throws Exception {
        String numeroCredito = "010101";
        mockMvc.perform(get("/api/creditos/credito/" + numeroCredito))
                .andExpect(status().isNotFound());
    }
}
