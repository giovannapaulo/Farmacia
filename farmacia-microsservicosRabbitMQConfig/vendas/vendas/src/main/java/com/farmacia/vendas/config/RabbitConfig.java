package com.farmacia.vendas.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String FILA_NOTAS = "fila-notas";

    @Bean
    public Queue filaNotas() {
        return new Queue(FILA_NOTAS, true);
    }
}