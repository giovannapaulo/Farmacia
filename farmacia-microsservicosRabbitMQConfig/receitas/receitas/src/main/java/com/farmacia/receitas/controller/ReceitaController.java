package com.farmacia.receitas.controller;

import com.farmacia.receitas.dto.ReceitaDTO;
import com.farmacia.receitas.model.Receita;
import com.farmacia.receitas.service.ReceitaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receitas")
@CrossOrigin("*")
public class ReceitaController {

    private final ReceitaService service;

    public ReceitaController(ReceitaService service) {
        this.service = service;
    }

    @PostMapping
    public Receita registrarReceita(@RequestBody ReceitaDTO dto) {
        return service.registrarReceita(dto);
    }

    @GetMapping
    public List<Receita> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Optional<Receita> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/cpf/{cpf}")
    public List<Receita> buscarPorCpf(@PathVariable String cpf) {
        return service.buscarPorCpf(cpf);
    }

    @GetMapping("/venda/{idVenda}")
    public List<Receita> buscarPorVenda(@PathVariable Long idVenda) {
        return service.buscarPorVenda(idVenda);
    }

    @GetMapping("/status/{status}")
    public List<Receita> buscarPorStatus(@PathVariable String status) {
        return service.buscarPorStatus(status);
    }

    @PutMapping("/{id}")
    public Receita atualizar(@PathVariable Long id,
                             @RequestBody ReceitaDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

}