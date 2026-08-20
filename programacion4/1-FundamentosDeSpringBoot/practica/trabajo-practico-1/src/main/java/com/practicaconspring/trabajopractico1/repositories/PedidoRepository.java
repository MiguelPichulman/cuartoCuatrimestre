package com.practicaconspring.trabajopractico1.repositories;

import com.practicaconspring.trabajopractico1.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {
}
