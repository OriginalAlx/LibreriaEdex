/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.libreria.edex.repository;

import com.libreria.edex.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alexp
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    
}
