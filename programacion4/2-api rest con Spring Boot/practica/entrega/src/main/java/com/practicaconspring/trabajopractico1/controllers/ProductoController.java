package com.practicaconspring.trabajopractico1.controllers;

import com.practicaconspring.trabajopractico1.dtos.producto.ProductoCreate;
import com.practicaconspring.trabajopractico1.dtos.producto.ProductoDto;
import com.practicaconspring.trabajopractico1.services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoDto> crearProducto(@RequestBody ProductoCreate dto) {
        ProductoDto nuevoProducto = productoService.createProducto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDto>> listarProductos() {
        return ResponseEntity.ok(productoService.listarProductos());
    }
}