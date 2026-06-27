package com.farmacia.vendas.dto;

import lombok.Data;

@Data
public class ClienteDTO {

    private Long id;
    private String nome;
    private String cpf;
    private Integer idade;
    private Boolean convenio;
    private Double percentualConvenio;
    private Boolean bonificacao;
    private Double percentualBonificacao;

}