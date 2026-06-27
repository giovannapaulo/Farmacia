package com.farmacia.receitas.repository;

import com.farmacia.receitas.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    List<Receita> findByCpfPaciente(String cpfPaciente);
    List<Receita> findByIdVenda(Long idVenda);
    List<Receita> findByStatus(String status);
    List<Receita> findByMedicamento(String medicamento);

}