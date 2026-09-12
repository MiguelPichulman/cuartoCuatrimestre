package com.practicaconspring.trabajopractico1.repositories;

import com.practicaconspring.trabajopractico1.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
