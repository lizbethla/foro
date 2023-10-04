package com.alura.foro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.foro.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Page<Usuario> findByActivoTrue(Pageable paginacion);

}
