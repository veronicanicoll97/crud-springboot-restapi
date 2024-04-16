package com.agenciacristal.crud.producto;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Tag(name = "Productos", description = "Recursos API sobre productos.")
@RestController
@RequestMapping(path = "api/v1/productos")
public class ProductoController {
    // El path de la variable va a la ruta principal dentro de GetMapping.
    private final ProductoService productoService;

    // Inyección de dependencias.
    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoService.obtenerProductos();
    }

    @PostMapping
    public ResponseEntity<Object> registrarProducto(@RequestBody Producto producto) {
        return this.productoService.crearProducto(producto);
    }

    @PutMapping
    public ResponseEntity<Object> actualizarProducto(@RequestBody Producto producto) {
        return this.productoService.crearProducto(producto);
    }

    @DeleteMapping(path = "{productId}")
    public ResponseEntity<Object> eliminarProducto(@PathVariable("productId") Long id) {
        return this.productoService.eliminarProducto(id);
    }
}
