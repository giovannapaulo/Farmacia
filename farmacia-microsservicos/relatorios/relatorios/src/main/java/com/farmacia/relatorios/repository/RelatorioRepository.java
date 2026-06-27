package com.farmacia.relatorios.repository;

import com.farmacia.relatorios.model.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {

    List<Relatorio> findByTipo(String tipo);
    List<Relatorio> findByDataGeracao(LocalDate dataGeracao);
    List<Relatorio> findByDataGeracaoBetween(LocalDate inicio, LocalDate fim);

}