package com.example.credit.enums;

import lombok.Getter;

@Getter
public enum SimNao {
    SIM("Sim"),
    NAO("Nao");

    private String descricao;

    SimNao(String descricao) {
        this.descricao = descricao;
    }

    public static SimNao valueOf(boolean value) {
        return value ? SIM : NAO;
    }
}
