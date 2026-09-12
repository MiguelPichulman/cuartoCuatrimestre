package com.practicaconspring.trabajopractico1.controllers;

import com.practicaconspring.trabajopractico1.dtos.categoria.CategoriaCreate;
import com.practicaconspring.trabajopractico1.dtos.categoria.CategoriaDto;
import com.practicaconspring.trabajopractico1.dtos.categoria.CategoriaEdit;
import com.practicaconspring.trabajopractico1.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    // POST: Para crear las 3 categorías solicitadas
    @PostMapping
    public ResponseEntity<CategoriaDto> crearCategoria(@RequestBody CategoriaCreate dto) {
        CategoriaDto nuevaCategoria = categoriaService.createCategoria(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

    // PUT: Para actualizar 1 categoría (Requisito 6)
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> actualizarCategoria(@PathVariable Long id, @RequestBody CategoriaEdit dto) {
        CategoriaDto categoriaActualizada = categoriaService.updateCategoria(id, dto);
        return ResponseEntity.ok(categoriaActualizada);
    }
}