package com.practicaconspring.trabajopractico1.repositories;

import com.practicaconspring.trabajopractico1.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
