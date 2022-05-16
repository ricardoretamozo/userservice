package com.example.userservice.controller;

import com.example.userservice.Servicio.UsuarioServicio;
import com.example.userservice.modelos.Rol;
import com.example.userservice.modelos.Usuario;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServicio usuarioServicio;
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>>getUsuario(){
        return ResponseEntity.ok().body(usuarioServicio.getUsers());
    }

    @PostMapping("/usuario/save")
    public ResponseEntity<Usuario>saveUser(@RequestBody Usuario user){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/usuario/save").toUriString());
        return ResponseEntity.created (uri).body(usuarioServicio.saveUsuario(user));
    }
    @PostMapping("/rol/save")
    public ResponseEntity<Rol>saveRole(@RequestBody Rol rol) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/rol/save").toUriString());
        return ResponseEntity.created(uri).body(usuarioServicio.saveRol(rol));
    }
    @PostMapping("/rol/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form) {
        usuarioServicio.addRolToUsuario(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
}

@Data
class RoleToUserForm {
        private String username;
        private String roleName;
}
