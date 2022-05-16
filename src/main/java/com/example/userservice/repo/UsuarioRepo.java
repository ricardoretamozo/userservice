package com.example.userservice.repo;

import com.example.userservice.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepo extends JpaRepository<Usuario , Long> {

    Usuario findByUsuario(String usuario);

}
