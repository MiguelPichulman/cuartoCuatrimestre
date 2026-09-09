package com.tutorial.usuariosenmemoriaerr.repository;

import com.tutorial.usuariosenmemoriaerr.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
