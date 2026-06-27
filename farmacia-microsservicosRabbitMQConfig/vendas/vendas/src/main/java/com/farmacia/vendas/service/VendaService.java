package com.farmacia.vendas.service;

import com.farmacia.vendas.dto.*;
import com.farmacia.vendas.model.Venda;
import com.farmacia.vendas.repository.VendaRepository;
import com.farmacia.vendas.config.RabbitConfig; 
import org.springframework.amqp.rabbit.core.RabbitTemplate; 
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository repository;
    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate; 

    private static final String URL_PRODUTOS = "http://localhost:8081/produtos";
    private static final String URL_CLIENTES = "http://localhost:8083/clientes";
    private static final String URL_NOTAS = "http://localhost:8084/notas"; // Obsoletas com o uso do RabbitMQ
    private static final String URL_RECEITAS = "http://localhost:8085/receitas"; // Também obsoletas com o nosso uso do RabbitMQ

    public VendaService(VendaRepository repository,
                        RestTemplate restTemplate,
                        RabbitTemplate rabbitTemplate) { 
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Venda realizarVenda(VendaDTO dto) {

        ProdutoDTO produto = restTemplate.getForObject(
                URL_PRODUTOS + "/" + dto.getIdProduto(),
                ProdutoDTO.class
        );

        if (produto == null) {
            throw new RuntimeException("Produto não encontrado.");
        }

        if (produto.getEstoque() < dto.getQuantidade()) {
            throw new RuntimeException("Estoque insuficiente.");
        }

        ClienteDTO cliente = restTemplate.getForObject(
                URL_CLIENTES + "/cpf/" + dto.getCpfCliente(),
                ClienteDTO.class
        );

        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado.");
        }

        Venda venda = new Venda();

        venda.setIdProduto(produto.getId());
        venda.setQuantidade(dto.getQuantidade());
        venda.setCpfCliente(cliente.getCpf());

        venda.setValorUnitario(produto.getPreco());

        double valorTotal = produto.getPreco() * dto.getQuantidade();

        venda.setValorTotal(valorTotal);

        double descontoConvenio = 0.0;
        double descontoBonificacao = 0.0;
        double descontoIdoso = 0.0;

        if (Boolean.TRUE.equals(cliente.getConvenio())) {
            descontoConvenio = valorTotal * (cliente.getPercentualConvenio() / 100);
        }

        if (Boolean.TRUE.equals(cliente.getBonificacao())) {
            descontoBonificacao = valorTotal * (cliente.getPercentualBonificacao() / 100);
        }

        if (cliente.getIdade() >= 60) {
            descontoIdoso = valorTotal * 0.10;
        }

        venda.setDescontoConvenio(descontoConvenio);
        venda.setDescontoBonificacao(descontoBonificacao);
        venda.setDescontoIdoso(descontoIdoso);

        double valorFinal = valorTotal - descontoConvenio - descontoBonificacao - descontoIdoso;

        venda.setValorFinal(valorFinal);
        venda.setComissao(valorFinal * 0.05);

        produto.setEstoque(produto.getEstoque() - dto.getQuantidade());

        restTemplate.put(
                URL_PRODUTOS + "/" + produto.getId(),
                produto
        );

        Venda vendaSalva = repository.save(venda);

        if (Boolean.TRUE.equals(dto.getEmitirNota())) {
            NotaFiscalDTO nota = new NotaFiscalDTO();
            nota.setIdVenda(vendaSalva.getId());
            nota.setCpfCliente(cliente.getCpf());
            nota.setValorTotal(vendaSalva.getValorFinal());

            
            rabbitTemplate.convertAndSend(RabbitConfig.FILA_NOTAS, nota);
        }

        if (produto.getTipo() != null && produto.getTipo().equalsIgnoreCase("CONTROLADO")) {
            ReceitaDTO receita = new ReceitaDTO();
            receita.setIdVenda(vendaSalva.getId());
            receita.setNomePaciente(cliente.getNome());
            receita.setCpfPaciente(cliente.getCpf());
            receita.setMedicamento(produto.getNome());
            receita.setCrmMedico("CRM/SP 000000");

            
            rabbitTemplate.convertAndSend("fila-receitas", receita);
        }

        return vendaSalva;
    }

    public List<Venda> listar() {
        return repository.findAll();
    }

    public Optional<Venda> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void excluir(Long id) {
        Venda venda = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada."));
        repository.delete(venda);
    }
}