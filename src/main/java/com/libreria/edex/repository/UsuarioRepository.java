package com.libreria.edex.repository;

import com.libreria.edex.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    public Optional<Usuario> findByEmail(String email);
    
}
