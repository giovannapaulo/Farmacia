package com.farmacia.clientes.dto;

import lombok.Data;

@Data
public class ClienteDTO {

    private String nome;
    private String cpf;
    private Integer idade;
    private Boolean convenio;
    private Double percentualConvenio;
    private Boolean bonificacao;
    private Double percentualBonificacao;

}