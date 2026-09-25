package com.udb.eventdriven.listener;

import com.udb.eventdriven.event.ProductoEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ProductoEventListener {

    private static final Logger log = LoggerFactory.getLogger(ProductoEventListener.class);

    @Async("taskExecutor")
    @EventListener
    public void manejarEventoProducto(ProductoEvent event) {
        String hilo = Thread.currentThread().getName();
        switch (event.getTipo()) {
            case CREADO -> {
                log.info("[{}] Evento CREADO recibido -> Producto id={} nombre={}",
                        hilo, event.getProducto().getId(), event.getProducto().getNombre());
                simularEnvioNotificacion(event, "creacion");
            }
            case ACTUALIZADO -> {
                log.info("[{}] Evento ACTUALIZADO recibido -> Producto id={} nombre={}",
                        hilo, event.getProducto().getId(), event.getProducto().getNombre());
                simularEnvioNotificacion(event, "actualizacion");
            }
            case ELIMINADO -> {
                log.info("[{}] Evento ELIMINADO recibido -> Producto id={}",
                        hilo, event.getProducto().getId());
                simularEnvioNotificacion(event, "eliminacion");
            }
        }
    }

    private void simularEnvioNotificacion(ProductoEvent event, String accion) {
        try {
            // Simula una tarea "pesada" en segundo plano (ej. enviar correo,
            // llamar a un servicio externo, generar reporte, etc.)
            Thread.sleep(800);
            log.info("Notificacion de {} enviada para el producto '{}' (procesada en background, sin bloquear al cliente)",
                    accion, event.getProducto().getNombre());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
