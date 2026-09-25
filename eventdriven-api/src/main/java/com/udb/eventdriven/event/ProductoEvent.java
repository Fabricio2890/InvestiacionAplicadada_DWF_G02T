package com.udb.eventdriven.event;

import com.udb.eventdriven.model.Producto;
import org.springframework.context.ApplicationEvent;


public class ProductoEvent extends ApplicationEvent {

    public enum TipoEvento { CREADO, ACTUALIZADO, ELIMINADO }

    private final Producto producto;
    private final TipoEvento tipo;

    public ProductoEvent(Object source, Producto producto, TipoEvento tipo) {
        super(source);
        this.producto = producto;
        this.tipo = tipo;
    }

    public Producto getProducto() { return producto; }
    public TipoEvento getTipo() { return tipo; }
}
