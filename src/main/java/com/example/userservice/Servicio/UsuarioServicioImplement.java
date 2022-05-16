package com.example.userservice.Servicio;

import com.example.userservice.modelos.Rol;
import com.example.userservice.modelos.Usuario;
import com.example.userservice.repo.RolRepo;
import com.example.userservice.repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UsuarioServicioImplement implements UsuarioServicio{

    private final UsuarioRepo usuarioRepo;
    private final RolRepo rolRepo;

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        log.info("Se creo un nuevo usuario");
        return usuarioRepo.save(usuario);
    }

    @Override
    public Rol saveRol(Rol rol) {
        return rolRepo.save(rol);
    }

    @Override
    public void addRolToUsuario(String usuario, String rolNombre) {
        Usuario user = usuarioRepo.findByUsuario(usuario);
        Rol rol = rolRepo.findByNombre(rolNombre);
        user.getRoles().add(rol);
    }

    @Override
    public Usuario getUsuario(String usuario) {
        return usuarioRepo.findByUsuario(usuario);
    }

    @Override
    public List<Usuario> getUsers() {
        return usuarioRepo.findAll();
    }
}
