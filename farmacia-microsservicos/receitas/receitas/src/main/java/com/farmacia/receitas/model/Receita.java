package com.farmacia.receitas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "receita")
@Data
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idVenda;
    private String nomePaciente;
    private String cpfPaciente;
    private String medicamento;
    private String crmMedico;
    private LocalDate dataReceita;
    private String status;

}