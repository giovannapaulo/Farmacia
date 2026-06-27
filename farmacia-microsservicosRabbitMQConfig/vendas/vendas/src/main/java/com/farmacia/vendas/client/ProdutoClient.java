package com.farmacia.vendas.client;

import com.farmacia.vendas.dto.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servico-produtos", url = "http://localhost:8081")
public interface ProdutoClient {
    @GetMapping("/produtos/{id}")
    ProdutoDTO buscarProdutoPorId(@PathVariable Long id);
}
