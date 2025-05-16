package com.example.credit.aspect;

import com.example.credit.controller.CreditoControllerImpl;
import com.example.credit.dto.CreditoResponseDto;
import com.example.credit.service.CreditoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class NotifyKafkaAspectTest {

    @Autowired
    private CreditoControllerImpl creditoController;

    @MockitoBean
    private CreditoService creditoService;

    @MockitoBean
    private KafkaTemplate kafkaTemplate;

    @Test
    void shouldInterceptApiMethodCall() {
        when(creditoService.findByNumeroCredito(anyString()))
                .thenReturn(mock(CreditoResponseDto.class));

        when(kafkaTemplate.send(anyString(), any()))
                .thenReturn(mock(CompletableFuture.class));

        creditoController.getCreditoByNumeroCredito("");

        verify(kafkaTemplate, times(1)).send(anyString(), any());
    }


}
