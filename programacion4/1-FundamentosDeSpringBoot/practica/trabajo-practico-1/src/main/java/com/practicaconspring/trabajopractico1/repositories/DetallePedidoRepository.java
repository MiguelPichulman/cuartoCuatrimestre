package com.practicaconspring.trabajopractico1.repositories;

import com.practicaconspring.trabajopractico1.entities.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido,Long> {
}
