package com.agenciacristal.crud.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    public List<Producto> obtenerProductos() {
        return this.productoRepository.findAll();
    }

    public ResponseEntity<Object> crearProducto(Producto producto) {
        Optional<Producto> existedProducts = productoRepository.findProductoByNombreProducto(producto.getNombreProducto());

        HashMap <String, Object > datos = new HashMap<>();

        if(existedProducts.isPresent() && producto.getId() == null) {
            datos.put("error", true);
            datos.put("message", "El producto ya existe.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Datos creados correctamente.");
        if(producto.getId() != null) {
            datos.put("message", "Producto actualizado correctamente.");
        }
        datos.put("error", false);
        datos.put("data", producto);

        productoRepository.save(producto);
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
                );
    }

    public ResponseEntity<Object> eliminarProducto(Long id) {
        boolean existeProducto = this.productoRepository.existsById(id);
        HashMap <String, Object > datos = new HashMap<>();
        if(!existeProducto) {
            datos.put("error", true);
            datos.put("message", "No existe el producto.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        this.productoRepository.deleteById(id);
        datos.put("error", false);
        datos.put("message", "Se ha eliminado el producto");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
