package com.udb.eventdriven.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Define el thread pool que ejecuta los listeners @Async.
 * Esto evita bloquear el hilo principal de la peticion HTTP
 * mientras se procesan los eventos (por ejemplo, "enviar notificacion").
 */
@Configuration
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("EventDriven-Async-");
        executor.initialize();
        return executor;
    }
}
