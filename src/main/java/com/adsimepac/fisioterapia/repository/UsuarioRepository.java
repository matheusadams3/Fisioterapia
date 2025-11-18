package com.adsimepac.fisioterapia.repository;

import com.adsimepac.fisioterapia.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email); // usado no login

    boolean existsByEmail(String email); // usado para evitar cadastro duplicado
}
