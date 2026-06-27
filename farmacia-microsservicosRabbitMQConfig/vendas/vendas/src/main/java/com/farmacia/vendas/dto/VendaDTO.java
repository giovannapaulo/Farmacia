package com.farmacia.vendas.dto;

import lombok.Data;

@Data
public class VendaDTO {

    private Long idProduto;
    private Integer quantidade;
    private String cpfCliente;
    private Boolean emitirNota;

}