package com.example.credit.controller;

import com.example.credit.dto.CreditoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/creditos")
public interface CreditoController {

    @GetMapping("/{numeroNfse}")
    ResponseEntity<List<CreditoResponseDto>> getByNumeroNfse(@PathVariable String numeroNfse);

    @GetMapping("/credito/{numeroCredito}")
    ResponseEntity<CreditoResponseDto> getCreditoByNumeroCredito(@PathVariable String numeroCredito);
}
