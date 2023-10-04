package com.alura.foro.dto;

import com.alura.foro.model.Usuario;

public record DatosRespuestaUsuario(Long id, String nombre, String email) {
	
	public DatosRespuestaUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
	}

}
