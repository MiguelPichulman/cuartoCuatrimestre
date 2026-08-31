package com.practicaconspring.trabajopractico1.services;

import com.practicaconspring.trabajopractico1.dtos.producto.ProductoCreate;
import com.practicaconspring.trabajopractico1.dtos.producto.ProductoDto;
import com.practicaconspring.trabajopractico1.entities.Categoria;
import com.practicaconspring.trabajopractico1.entities.Producto;
import com.practicaconspring.trabajopractico1.repositories.CategoriaRepository;
import com.practicaconspring.trabajopractico1.repositories.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public ProductoDto createProducto(ProductoCreate dto) {
        Categoria categoria = categoriaRepository.findById(dto.idCategoria()).orElseThrow(()
                -> new RuntimeException("Error. La categoria con ID" + dto.idCategoria() + " no existe"));
        Producto producto = dto.ToEntity(categoria);
        Producto prodcutoGuardado = productoRepository.save(producto);

        return ProductoDto.toDto(prodcutoGuardado);
    }

}
