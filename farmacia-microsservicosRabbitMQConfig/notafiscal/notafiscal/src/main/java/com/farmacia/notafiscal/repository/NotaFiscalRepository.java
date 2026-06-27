package com.farmacia.notafiscal.repository;

import com.farmacia.notafiscal.model.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {

    List<NotaFiscal> findByCpfCliente(String cpfCliente);
    List<NotaFiscal> findByIdVenda(Long idVenda);
    List<NotaFiscal> findByStatus(String status);

}