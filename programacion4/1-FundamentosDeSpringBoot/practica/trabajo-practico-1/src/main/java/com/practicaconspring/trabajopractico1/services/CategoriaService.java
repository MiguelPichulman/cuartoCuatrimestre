package com.practicaconspring.trabajopractico1.services;

import com.practicaconspring.trabajopractico1.dtos.categoria.CategoriaCreate;
import com.practicaconspring.trabajopractico1.dtos.categoria.CategoriaDto;
import com.practicaconspring.trabajopractico1.entities.Categoria;
import com.practicaconspring.trabajopractico1.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaDto createCategoria(CategoriaCreate dto) {
        Categoria categoria = dto.ToEntity();
        Categoria categoriaGuardada = categoriaRepository.save(categoria);
        return CategoriaDto.toDto(categoriaGuardada);
    }
}