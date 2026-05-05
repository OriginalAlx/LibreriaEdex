package com.libreriaedex.service;

import com.libreria.edex.model.Rol;
import com.libreria.edex.repository.RolRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RolService {

    private RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }

}
