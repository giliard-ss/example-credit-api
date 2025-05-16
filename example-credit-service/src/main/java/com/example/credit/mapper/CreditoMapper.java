package com.example.credit.mapper;

import com.example.credit.dto.CreditoResponseDto;
import com.example.credit.entity.Credito;
import com.example.credit.enums.SimNao;
import org.springframework.stereotype.Component;

@Component
public class CreditoMapper {

    public CreditoResponseDto toResponseDto(Credito entity) {
        CreditoResponseDto dto = new CreditoResponseDto();
        dto.setNumeroNfse(entity.getNumeroNfse());
        dto.setAliquota(entity.getAliquota());
        dto.setNumeroCredito(entity.getNumeroCredito());
        dto.setTipoCredito(entity.getTipoCredito());
        dto.setSimplesNacional(SimNao.valueOf(entity.isSimplesNacional()).getDescricao());
        dto.setBaseCalculo(entity.getBaseCalculo());
        dto.setValorDeducao(entity.getValorDeducao());
        dto.setDataConstituicao(entity.getDataConstituicao());
        dto.setValorFaturado(entity.getValorFaturado());
        dto.setValorIssqn(entity.getValorIssqn());
        return dto;
    }

}
