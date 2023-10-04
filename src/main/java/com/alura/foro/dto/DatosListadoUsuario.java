package com.alura.foro.dto;

import com.alura.foro.model.Usuario;

public record DatosListadoUsuario(String nombre, String email) {
	public DatosListadoUsuario(Usuario usuario) {
		this(usuario.getNombre(), usuario.getEmail());
	}

}
