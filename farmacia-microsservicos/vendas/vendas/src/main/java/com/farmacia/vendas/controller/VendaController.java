package com.farmacia.vendas.controller;

import com.farmacia.vendas.dto.VendaDTO;
import com.farmacia.vendas.model.Venda;
import com.farmacia.vendas.service.VendaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendas")
@CrossOrigin("*")
public class VendaController {

    private final VendaService service;

    public VendaController(VendaService service) {
        this.service = service;
    }

    @PostMapping
    public Venda realizarVenda(@RequestBody VendaDTO dto) {
        return service.realizarVenda(dto);
    }

    @GetMapping
    public List<Venda> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Optional<Venda> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

}