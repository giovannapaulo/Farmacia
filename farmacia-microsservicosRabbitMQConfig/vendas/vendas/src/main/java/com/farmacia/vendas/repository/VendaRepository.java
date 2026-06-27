package com.farmacia.vendas.repository;

import com.farmacia.vendas.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VendaRepository extends JpaRepository<Venda, Long> {

}