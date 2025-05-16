package com.example.credit.controller;

import com.example.credit.dto.CreditoResponseDto;
import com.example.credit.service.CreditoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CreditoControllerImpl implements  CreditoController {

    private CreditoService creditoService;

    @Override
    public ResponseEntity<List<CreditoResponseDto>> getByNumeroNfse(String numeroNfse) {
        return ResponseEntity.ok(creditoService.findByNumeroNfse(numeroNfse));
    }

    @Override
    public ResponseEntity<CreditoResponseDto> getCreditoByNumeroCredito(String numeroCredito) {
        return ResponseEntity.ok(creditoService.findByNumeroCredito(numeroCredito));
    }
}
