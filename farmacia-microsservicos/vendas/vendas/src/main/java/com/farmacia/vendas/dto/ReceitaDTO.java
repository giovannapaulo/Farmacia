package com.farmacia.vendas.dto;

import lombok.Data;

@Data
public class ReceitaDTO {

    private Long idVenda;
    private String nomePaciente;
    private String cpfPaciente;
    private String medicamento;
    private String crmMedico;

}
