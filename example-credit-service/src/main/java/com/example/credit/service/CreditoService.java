package com.example.credit.service;

import com.example.credit.dto.CreditoResponseDto;

import java.util.List;

public interface CreditoService {

    List<CreditoResponseDto> findByNumeroNfse(String numeroNfse);

    CreditoResponseDto findByNumeroCredito(String numeroCredito);
}
