package com.example.credit.service;


import com.example.credit.dto.CreditoResponseDto;
import com.example.credit.entity.Credito;
import com.example.credit.mapper.CreditoMapper;
import com.example.credit.repository.CreditoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class CreditoServiceTest {

    @Mock
    private CreditoRepository creditoRepository;

    @InjectMocks
    private CreditoServiceImpl creditoService;

    @Spy
    private CreditoMapper creditoMapper;

    @Test
    void shouldReturnDtoListFromEntity() {
        Credito credito = createCreditoDefault();

        Mockito.when(creditoRepository.findAllByNumeroNfse("7891011"))
                .thenReturn(List.of(credito));

        List<CreditoResponseDto> result = creditoService.findByNumeroNfse("7891011");

        Assertions.assertEquals(1, result.size());
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(credito.getNumeroNfse(), result.get(0).getNumeroNfse());
    }


    private Credito createCreditoDefault() {
        Credito credito = new Credito();
        credito.setNumeroCredito("123456");
        credito.setNumeroNfse("7891011");
        credito.setDataConstituicao(LocalDate.of(2024,2,25));
        credito.setValorIssqn(new BigDecimal("1500.75"));
        credito.setTipoCredito("ISSQN");
        credito.setSimplesNacional(true);
        credito.setAliquota(new BigDecimal("5.0"));
        credito.setValorFaturado(new BigDecimal("30000.00"));
        credito.setValorDeducao(new BigDecimal("5000.00"));
        credito.setBaseCalculo(new BigDecimal("25000.00"));

        return credito;
    }
}
