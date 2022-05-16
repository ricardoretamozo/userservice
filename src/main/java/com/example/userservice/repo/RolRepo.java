package com.example.userservice.repo;

import com.example.userservice.modelos.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepo extends JpaRepository<Rol, Long> {

    Rol findByNombre(String nombre);
}
