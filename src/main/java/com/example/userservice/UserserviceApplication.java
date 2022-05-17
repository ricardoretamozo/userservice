package com.example.userservice;

import com.example.userservice.Servicio.UsuarioServicio;
import com.example.userservice.modelos.Rol;
import com.example.userservice.modelos.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;


@SpringBootApplication
public class UserserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserserviceApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run(UsuarioServicio userService) {
        return args -> {
            userService.saveRol(new Rol(1, "ROLE_USER"));
            userService.saveRol(new Rol(2, "ROLE_MANAGER"));
            userService.saveRol(new Rol(3, "ROLE_ADMIN"));
            userService.saveRol(new Rol(4, "ROLE_SUPER_ADMIN"));
            userService.saveUsuario(new Usuario(1, "John Travolta", "john", "1234", new ArrayList<>()));
            userService.saveUsuario(new Usuario(2, "Will smith", "will", "1234", new ArrayList<>()));
            userService.saveUsuario(new Usuario(3, "Jim Carry", "jim", "1234", new ArrayList<>()));
            userService.saveUsuario(new Usuario(4, "Arnold Schwarzenegger", "arnold", "1234", new ArrayList<>()));
            userService.addRolToUsuario("john", "ROLE_USER");
            userService.addRolToUsuario("john", "ROLE_MANAGER");
            userService.addRolToUsuario("will", "ROLE_MANAGER");
            userService.addRolToUsuario("jim", "ROLE_ADMIN");
            userService.addRolToUsuario("arnold", "ROLE_SUPER_ADMIN");
            userService.addRolToUsuario("arnold", "ROLE_ADMIN");
            userService.addRolToUsuario("arnold", "ROLE_USER");
        };
    }
}
