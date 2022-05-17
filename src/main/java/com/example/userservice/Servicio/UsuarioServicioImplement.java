package com.example.userservice.Servicio;

import com.example.userservice.modelos.Rol;
import com.example.userservice.modelos.Usuario;
import com.example.userservice.repo.RolRepo;
import com.example.userservice.repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UsuarioServicioImplement implements UsuarioServicio, UserDetailsService {

    private final UsuarioRepo usuarioRepo;
    private final RolRepo rolRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        log.info("Se creo un nuevo usuario");
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByUsuario(username);
        if (usuario == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else {
            log.info("User found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        usuario.getRoles().forEach(rol -> authorities.add(new SimpleGrantedAuthority(rol.getNombre())));
        return new org.springframework.security.core.userdetails.User(usuario.getUsuario(), usuario.getContrasena(),authorities);
    }
}
