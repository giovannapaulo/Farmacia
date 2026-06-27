package com.farmacia.vendas.dto;

import lombok.Data;

@Data
public class ProdutoDTO {

    private Long id;
    private String nome;
    private Double preco;
    private String tipo;
    private Integer estoque;

}