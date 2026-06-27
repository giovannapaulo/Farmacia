package com.farmacia.notafiscal.controller;

import com.farmacia.notafiscal.dto.NotaFiscalDTO;
import com.farmacia.notafiscal.model.NotaFiscal;
import com.farmacia.notafiscal.service.NotaFiscalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notas")
@CrossOrigin("*")
public class NotaFiscalController {

    private final NotaFiscalService service;

    public NotaFiscalController(NotaFiscalService service) {
        this.service = service;
    }

    @PostMapping
    public NotaFiscal emitirNotaFiscal(@RequestBody NotaFiscalDTO dto) {
        return service.emitirNotaFiscal(dto);
    }

    @GetMapping
    public List<NotaFiscal> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Optional<NotaFiscal> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/cpf/{cpf}")
    public List<NotaFiscal> buscarPorCpf(@PathVariable String cpf) {
        return service.buscarPorCpf(cpf);
    }

    @GetMapping("/venda/{idVenda}")
    public List<NotaFiscal> buscarPorVenda(@PathVariable Long idVenda) {
        return service.buscarPorVenda(idVenda);
    }

    @GetMapping("/status/{status}")
    public List<NotaFiscal> buscarPorStatus(@PathVariable String status) {
        return service.buscarPorStatus(status);
    }

    @PutMapping("/{id}")
    public NotaFiscal atualizar(@PathVariable Long id,
                                @RequestBody NotaFiscalDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

}