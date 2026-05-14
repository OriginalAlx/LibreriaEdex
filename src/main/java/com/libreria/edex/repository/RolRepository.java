package com.libreria.edex.repository;

import com.libreria.edex.model.Rol;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByNombre(String nombre);
}
