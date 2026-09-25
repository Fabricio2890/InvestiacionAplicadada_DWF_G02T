package com.udb.eventdriven.service;

import com.udb.eventdriven.event.ProductoEvent;
import com.udb.eventdriven.exception.ResourceNotFoundException;
import com.udb.eventdriven.model.Producto;
import com.udb.eventdriven.repository.ProductoRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductoService {

    private final ProductoRepository repository;
    private final ApplicationEventPublisher publisher;

    public ProductoService(ProductoRepository repository, ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public List<Producto> listarTodos() {
        return repository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
    }

    public Producto crear(Producto producto) {
        Producto guardado = repository.save(producto);
        publisher.publishEvent(new ProductoEvent(this, guardado, ProductoEvent.TipoEvento.CREADO));
        return guardado;
    }

    public Producto actualizar(Long id, Producto datos) {
        Producto existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setPrecio(datos.getPrecio());
        existente.setStock(datos.getStock());
        Producto actualizado = repository.save(existente);
        publisher.publishEvent(new ProductoEvent(this, actualizado, ProductoEvent.TipoEvento.ACTUALIZADO));
        return actualizado;
    }

    public void eliminar(Long id) {
        Producto existente = obtenerPorId(id);
        repository.deleteById(id);
        publisher.publishEvent(new ProductoEvent(this, existente, ProductoEvent.TipoEvento.ELIMINADO));
    }
}
