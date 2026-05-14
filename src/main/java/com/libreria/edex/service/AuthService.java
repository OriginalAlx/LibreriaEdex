package com.libreria.edex.service;

import com.libreria.edex.model.Rol;
import com.libreria.edex.model.Usuario;
import com.libreria.edex.repository.RolRepository;
import com.libreria.edex.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;
    
    public void registrarCliente(String email, String password) {
        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        Rol rolCliente = rolRepository.findByNombre("CLIENTE")
                .orElseThrow(() -> new RuntimeException("Rol CLIENTE no encontrado"));

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(email);

        nuevoUsuario.setPassword(password);

        nuevoUsuario.setRol(rolCliente);
        nuevoUsuario.setActivo(true);

        usuarioRepository.save(nuevoUsuario);
    }
}
