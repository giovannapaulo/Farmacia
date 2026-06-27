package com.farmacia.vendas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "venda")
@Data
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idProduto;
    private Integer quantidade;
    private Double valorUnitario;
    private Double valorTotal;
    private Double descontoConvenio;
    private Double descontoBonificacao;
    private Double descontoIdoso;
    private Double valorFinal;
    private Double comissao;
    private String cpfCliente;

}