package com.alura.foro.dto;

import com.alura.foro.model.Curso;

public record DatosRespuestaCurso(Long id, String nombre, String categoria) {
	public DatosRespuestaCurso(Curso curso) {
		this(curso.getId(), curso.getNombre(), curso.getCategoria());
	}

}
