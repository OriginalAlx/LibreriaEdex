/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.libreria.edex.repository;

import com.libreriaedex.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alexp
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    public Optional<Usuario> findByEmail(String email);
    
}
