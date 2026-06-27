package com.farmacia.produtos.dto;

import lombok.Data;

@Data
public class ProdutoDTO {
    private String nome;
    private Double preco;
    private String tipo;
    private Integer estoque;
}