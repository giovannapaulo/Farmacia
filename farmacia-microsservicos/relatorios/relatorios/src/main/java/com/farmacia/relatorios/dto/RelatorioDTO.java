package com.farmacia.relatorios.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RelatorioDTO {

    private String tipo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String descricao;

}