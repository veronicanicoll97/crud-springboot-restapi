package com.agenciacristal.crud.producto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombreProducto;
    private Float precio;
    private LocalDate fechaCreacion;

    // Significa que antiguedadProducto es un campo calculado.
    @Transient
    private int antiguedadProducto;

    public Producto() {
    }

    public Producto(Long id, String nombreProducto, Float precio, LocalDate fechaCreacion) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.fechaCreacion = fechaCreacion;
    }

    public Producto(String nombreProducto, Float precio, LocalDate fechaCreacion) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() {
        return id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public Float getPrecio() {
        return precio;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public int getAntiguedadProducto() {
        return Period.between(this.fechaCreacion, LocalDate.now()).getYears();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setAntiguedadProducto(int antiguedadProducto) {
        this.antiguedadProducto = antiguedadProducto;
    }
}
