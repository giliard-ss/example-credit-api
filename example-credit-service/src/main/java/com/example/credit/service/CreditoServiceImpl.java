package com.example.credit.service;

import com.example.credit.dto.CreditoResponseDto;
import com.example.credit.entity.Credito;
import com.example.credit.exception.NotFoundException;
import com.example.credit.mapper.CreditoMapper;
import com.example.credit.repository.CreditoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CreditoServiceImpl implements CreditoService {

    private CreditoRepository creditoRepository;
    private CreditoMapper creditoMapper;

    @Override
    public List<CreditoResponseDto> findByNumeroNfse(String numeroNfse) {
        return creditoRepository.findAllByNumeroNfse(numeroNfse).stream()
                .map(credito -> creditoMapper.toResponseDto(credito))
                .toList();
    }

    @Override
    public CreditoResponseDto findByNumeroCredito(String numeroCredito) {
        Credito credito = creditoRepository.findByNumeroCredito(numeroCredito)
                .orElseThrow(NotFoundException::new);

        return creditoMapper.toResponseDto(credito);
    }
}
