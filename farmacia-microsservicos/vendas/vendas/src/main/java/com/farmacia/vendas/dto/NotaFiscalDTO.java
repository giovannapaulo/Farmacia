package com.farmacia.vendas.dto;

import lombok.Data;

@Data
public class NotaFiscalDTO {

    private Long idVenda;
    private String cpfCliente;
    private Double valorTotal;

}