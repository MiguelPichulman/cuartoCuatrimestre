package com.practicaconspring.trabajopractico1.repositories;

import com.practicaconspring.trabajopractico1.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

}
