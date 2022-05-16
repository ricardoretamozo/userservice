package com.example.userservice.Servicio;

import com.example.userservice.modelos.Rol;
import com.example.userservice.modelos.Usuario;

import java.util.List;

public interface UsuarioServicio {
    Usuario saveUsuario(Usuario usuario);
    Rol saveRol(Rol rol);
    void addRolToUsuario(String usuario, String rolNombre);
    Usuario getUsuario(String usuario);
    List<Usuario> getUsers();
}
