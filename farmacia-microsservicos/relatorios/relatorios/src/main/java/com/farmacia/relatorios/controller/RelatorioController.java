package com.farmacia.relatorios.controller;

import com.farmacia.relatorios.dto.RelatorioDTO;
import com.farmacia.relatorios.model.Relatorio;
import com.farmacia.relatorios.service.RelatorioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/relatorios")
@CrossOrigin("*")
public class RelatorioController {

    private final RelatorioService service;

    public RelatorioController(RelatorioService service) {
        this.service = service;
    }

    @PostMapping
    public Relatorio gerarRelatorio(@RequestBody RelatorioDTO dto) {
        return service.gerarRelatorio(dto);
    }

    @GetMapping
    public List<Relatorio> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Optional<Relatorio> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/tipo/{tipo}")
    public List<Relatorio> buscarPorTipo(@PathVariable String tipo) {
        return service.buscarPorTipo(tipo);
    }

    @PutMapping("/{id}")
    public Relatorio atualizar(@PathVariable Long id,
                               @RequestBody RelatorioDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

}