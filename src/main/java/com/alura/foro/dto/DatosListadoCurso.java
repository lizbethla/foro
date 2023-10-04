package com.alura.foro.dto;

import com.alura.foro.model.Curso;

public record DatosListadoCurso(String nombre, String categoria) {
	public DatosListadoCurso(Curso curso) {
		this(curso.getNombre(), curso.getCategoria());
	}
}
